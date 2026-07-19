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
 * Servlet implementation class ModificaProdottoForm
 */
@WebServlet("/admin/ModificaProdottoForm")
public class ModificaProdottoForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaProdottoForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codice = request.getParameter("codice");
		
		if (codice != null && !codice.trim().isEmpty()) {
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			ProdottoDAO prodottoDAO = new ProdottoDAO(ds);
			Prodotto prodotto = prodottoDAO.doSearchElement(codice);
			request.setAttribute("prodotto", prodotto);
		}
		
		request.getRequestDispatcher("/admin/modificaProdotto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}