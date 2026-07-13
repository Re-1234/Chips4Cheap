package it.unisa.chips4cheap.controllers.Catalogo;

import jakarta.servlet.RequestDispatcher;
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
 * Servlet implementation class prodotti
 */
@WebServlet("/prodotti")
public class prodotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public prodotti() {
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
		String minPrezzo = request.getParameter("prezzoMinSlider");
		String maxPrezzo = request.getParameter("prezzoMaxSlider");
		
		
		int minprezz = Integer.valueOf(minPrezzo);
		int maxprezz = Integer.valueOf(maxPrezzo);
		
		ProdottoDAO prodotto = new ProdottoDAO(da);
		
		ArrayList<Prodotto> prodotti =	prodotto.doFilter(nomeModello, produttore, tipo,minprezz,maxprezz);
		
		
		
		
	}

}
