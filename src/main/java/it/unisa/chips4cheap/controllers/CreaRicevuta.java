package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.ProdottoRicevutaDAO;
import it.unisa.chips4cheap.model.DAO.RicevutaFiscaleDAO;
import it.unisa.chips4cheap.model.DTO.Account;
import it.unisa.chips4cheap.model.DTO.Prodotto;
import it.unisa.chips4cheap.model.DTO.ProdottoRicevuta;
import it.unisa.chips4cheap.model.DTO.RicevutaFiscale;

/**
 * Servlet implementation class CreaRicevuta
 */
@WebServlet("/common/CreaRicevuta")
public class CreaRicevuta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaRicevuta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
        
        Account accountLoggato = (Account) session.getAttribute("account");

        
        ArrayList<Prodotto> carrello = (ArrayList<Prodotto>) session.getAttribute("carrello");
        if (carrello == null || carrello.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/Carrello"); // questo bel carrello esiste?
            return;
        }

        String via = request.getParameter("Via");
        String cap = request.getParameter("Cap");
        String numeroCivicoStr = request.getParameter("NumeroCivico");
        String metodoPagamento = request.getParameter("metodoPagamento");

        String errore = null;

        if (via == null || via.trim().isEmpty() || via.trim().length() > 50) {
            errore = "L'indirizzo inserito non è valido o supera i 50 caratteri.";
        } else if (cap == null || !cap.trim().matches("^\\d{5}$")) {
            errore = "Il CAP deve essere composto esattamente da 5 cifre numeriche.";
        } else if (numeroCivicoStr == null || !numeroCivicoStr.trim().matches("^\\d+$")) {
            errore = "Il numero civico deve essere un numero intero valido.";
        } else if (metodoPagamento == null || metodoPagamento.trim().isEmpty()) {
            errore = "Il metodo di pagamento selezionato non è valido.";
        }

        if (errore != null) {
            request.setAttribute("erroreServer", errore);
            request.getRequestDispatcher("/WEB-INF/views/pagamento.jsp").forward(request, response);
        } else {
            try {
                RicevutaFiscale ricevuta = new RicevutaFiscale(
                    0, 
                    accountLoggato.getEmail(), 
                    metodoPagamento.trim(),  // non dovrebbe essere necessario il trim ma non si sa mai
                    LocalDate.now(),
                    via.trim(),
                    cap.trim(),
                    Integer.parseInt(numeroCivicoStr.trim())
                );

                DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
                RicevutaFiscaleDAO ricevutaDAO = new RicevutaFiscaleDAO(ds);
                
                int idRicevutaGenerato = ricevutaDAO.doSave(ricevuta);
                RicevutaFiscale ricevutaEffettiva = ricevutaDAO.doSearchElement(idRicevutaGenerato); // l'aggiungo alla request per mostrarlo dopo

                ProdottoRicevutaDAO prodRicevutaDAO = new ProdottoRicevutaDAO(ds);
                ArrayList<ProdottoRicevuta> prodottiSalvati = new ArrayList<>(); // creo l'arrayList per mostrare i Prodotti dopo
                for (Prodotto p : carrello) {
                    ProdottoRicevuta prodRicevuta = new ProdottoRicevuta(
                        p.getnCAutore(),
                        idRicevutaGenerato,
                        accountLoggato.getEmail(),
                        p.getNomeModello(),
                        p.getPrezzo(),
                        p.getTipo(),
                        p.getQuantità(),
                        p.getImagine()
                    );
                    
                    prodRicevutaDAO.doSave(prodRicevuta);
                    prodottiSalvati.add(prodRicevuta);
                }

                session.removeAttribute("carrello"); // a questo punto buttalo nel Monte Fato, in futuro lo ricreiamo da zero se ci serve

                request.setAttribute("ricevuta", ricevutaEffettiva);
                request.setAttribute("prodottiRicevuta", prodottiSalvati);

                request.getRequestDispatcher("/WEB-INF/views/common/visualizzaRicevuta.jsp").forward(request, response); 

            } catch (Exception e) {		// potevo rimuovere il try-catch?
                e.printStackTrace();
                throw new ServletException("Errore critico durante la finalizzazione dell'ordine nel database.", e);
            }
        }
	}

}
