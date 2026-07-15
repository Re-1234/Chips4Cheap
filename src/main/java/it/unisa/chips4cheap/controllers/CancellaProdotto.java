package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.ProdottoDAO;
import it.unisa.chips4cheap.model.DTO.Prodotto;

/**
 * Servlet implementation class CancellaProdotto
 */
@WebServlet("/admin/CancellaProdotto")
public class CancellaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeModello = request.getParameter("nomeModello");
		
		if (nomeModello != null && !nomeModello.trim().isEmpty()) { 
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			ProdottoDAO prodottoDAO = new ProdottoDAO(ds);
			Prodotto prodottoDaCancellare = prodottoDAO.doSearchElement(nomeModello);
			
			if (prodottoDaCancellare != null) {
				// Includi ControlloImmagini per rimuovere l'mmagine, DEVE ESSERE doPOST per questo! e fallo prima di cancellare il prodotto
				request.getRequestDispatcher("/ControlloImmagini?action=delete").include(request, response);
				prodottoDAO.doDelete(prodottoDaCancellare);
			}
		}
		request.getRequestDispatcher("/Catalogo").forward(request, response);
	}

}
