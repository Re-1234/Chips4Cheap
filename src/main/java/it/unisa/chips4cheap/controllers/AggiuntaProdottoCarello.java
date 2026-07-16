package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.ProdottoDAO;
import it.unisa.chips4cheap.model.DTO.Prodotto;

/**
 * Servlet implementation class AggiuntaProdottoCarello
 */
@WebServlet("/AggiuntaProdottoCarello")
public class AggiuntaProdottoCarello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiuntaProdottoCarello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ProdottoDAO prodot = new ProdottoDAO(ds);
		Prodotto p = prodot.doSearchElement();
		HttpSession http =	request.getSession();
		http.setAttribute("Prodotto", );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
