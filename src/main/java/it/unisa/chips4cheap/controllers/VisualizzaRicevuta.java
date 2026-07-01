package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.ProdottoRicevutaDAO;
import it.unisa.chips4cheap.model.DAO.RicevutaFiscaleDAO;
import it.unisa.chips4cheap.model.DTO.Account;
import it.unisa.chips4cheap.model.DTO.ProdottoRicevuta;
import it.unisa.chips4cheap.model.DTO.RicevutaFiscale;

/**
 * Servlet implementation class VisualizzaRicevuta
 */
@WebServlet("/common/VisualizzaRicevuta")
public class VisualizzaRicevuta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaRicevuta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        Account accountLoggato = (Account) session.getAttribute("account");

        String idParam = request.getParameter("id");

        // reindirizzia allo storico delle ricevute se id non è presente o valido
        if (idParam == null || idParam.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/common/RicevuteAccount");
            return;
        }
        
            
        int idRicevuta = Integer.parseInt(idParam.trim());
             
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        
        RicevutaFiscaleDAO daoRicevuta = new RicevutaFiscaleDAO(ds);
        RicevutaFiscale ricevuta = daoRicevuta.doSearchElement(idRicevuta);
            
        ProdottoRicevutaDAO daoProdottoRicevuta = new ProdottoRicevutaDAO(ds);
        ArrayList<ProdottoRicevuta> lista = daoProdottoRicevuta.doSearchElement(idRicevuta);
            
            /* aggiunta pagina errore, non mi serve questo?
            if (ricevuta == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ricevuta non trovata.");
                return;
            }
            */
            
        // è tua la ricevuta?
        if (!ricevuta.getEmail().equals(accountLoggato.getEmail())) {
            response.sendError(404); // dovrebbe funzionare?
            return;
        }

        request.setAttribute("ricevuta", ricevuta);
        request.setAttribute("listaProdotti", lista);
        request.getRequestDispatcher("/WEB-INF/views/common/visualizzaRicevuta.jsp").forward(request, response);
       }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
