package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.AnnuncioDAO;
import it.unisa.chips4cheap.model.DTO.Annuncio;

/**
 * Servlet implementation class CreaAnnuncio
 */
@WebServlet("/admin/CreaAnnuncio")
public class CreaAnnuncio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaAnnuncio() {
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
				String titolo = request.getParameter("titolo");
				String descrizione = request.getParameter("descrizione");
				
				if (titolo != null && !titolo.trim().isEmpty() && descrizione != null && !descrizione.trim().isEmpty()) {
					
					Annuncio nuovoAnnuncio = new Annuncio();
					nuovoAnnuncio.setTitolo(titolo);
					nuovoAnnuncio.setText(descrizione);
					nuovoAnnuncio.setDataPubblicazione(LocalDate.now()); // Data odierna
					
					DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
					AnnuncioDAO dao = new AnnuncioDAO(ds);
					dao.doSave(nuovoAnnuncio);
					
					ArrayList<Annuncio> listaAggiornata = dao.doRetrieveAll(); // posso permettermi di fare il do retrieve all ogni volta, pochi annunci e 1 solo admin
					getServletContext().setAttribute("tuttiAnnunci", listaAggiornata);
					
					// lo butto nella sua area Personale
					response.sendRedirect(request.getContextPath() + "/common/AreaPersonale");
					
				} else {
					request.setAttribute("erroreServer", "Titolo e descrizione sono obbligatori!");
					request.getRequestDispatcher("/WEB-INF/views/admin/mandaAnnuncio.jsp").forward(request, response);
				}
	}

}
