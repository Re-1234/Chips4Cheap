package it.unisa.chips4cheap.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.ProdottoDAO;
import it.unisa.chips4cheap.model.DTO.Prodotto;

/**
 * Servlet implementation class AggiuntaProdottoCarello
 */

@WebServlet("/AggiuntaProdottoCarello")
public class AggiuntaProdottoCarello extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AggiuntaProdottoCarello() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("sono dentro");
		String nomeModello = request.getParameter("NomeModello");
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ProdottoDAO prodot = new ProdottoDAO(ds);
		Prodotto p = prodot.doSearchElement(nomeModello);
		System.out.println("ciao");
		HttpSession http = request.getSession();
		ArrayList<Prodotto> pro = (ArrayList<Prodotto>) http.getAttribute("carello");
		
		if (pro == null) {
			pro = new ArrayList<>();
			http.setAttribute("carello", pro);
		}

		boolean cista = false;
		for (Prodotto pe : pro) {
			if (pe.getNomeModello().equals(p.getNomeModello())) {
				cista = true;
				break;
			}
		}
		if (!cista) {
			pro.add(p);
		}
		System.out.println(pro);
		response.sendRedirect(request.getContextPath() + "/Catalogo");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}