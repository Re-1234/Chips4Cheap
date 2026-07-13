package it.unisa.chips4cheap.controllers.Catalogo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.logging.LogManager;

import javax.sql.DataSource;

import com.mysql.cj.log.Log;

import it.unisa.chips4cheap.model.DAO.ProdottoDAO;
import it.unisa.chips4cheap.model.DTO.Prodotto;

/**
 * Servlet implementation class prodotti
 */
@WebServlet("/RicercaProdotti")
public class RicercaProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RicercaProdotti() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource da = (DataSource) getServletContext().getAttribute("DataSource");
		String nomeModello = request.getParameter("nomeModello");
		String produttore = request.getParameter("produttore");
		String tipo = request.getParameter("tipo");
		String minPrezzo = request.getParameter("prezzoMin");
		String maxPrezzo = request.getParameter("prezzoMax");
		
		System.out.println("nomeModello : " + nomeModello);
		System.out.println("produttore : " + produttore);
		System.out.println("tipo : " + tipo);
		System.out.println("minPrezzo : " + minPrezzo);
		System.out.println("maxPrezzo : " + maxPrezzo);
		
		int minprezz = Integer.valueOf(minPrezzo);
		int maxprezz = Integer.valueOf(maxPrezzo);
		
		ProdottoDAO prodotto = new ProdottoDAO(da);
		
		ArrayList<Prodotto> prodotti =	prodotto.doFilter(nomeModello, produttore, tipo,minprezz,maxprezz);
		
		request.setAttribute("prodotti",prodotti);
		
		RequestDispatcher re = request.getRequestDispatcher("WEB-INF\\views\\Catalogo.jsp");
		re.forward(request, response);
	}

}
