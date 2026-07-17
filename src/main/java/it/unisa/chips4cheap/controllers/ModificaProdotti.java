package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.ProdottoDAO;
import it.unisa.chips4cheap.model.DTO.Prodotto;

/**
 * Servlet implementation class ModificaProdotto
 */
@WebServlet("/admin/ModificaProdotti")
public class ModificaProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaProdotti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataSource d = (DataSource) getServletContext().getAttribute("DataSource");
		ProdottoDAO prodottoDAO = new ProdottoDAO(d);
		ArrayList<Prodotto> prodotti = prodottoDAO.doRetrieveByAll();
		request.setAttribute("prodotti", prodotti);
		request.getRequestDispatcher("/WEB-INF/views/admin/ModificaProdotti.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
