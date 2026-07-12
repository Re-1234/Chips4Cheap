package it.unisa.chips4cheap.controllers.Catalogo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		String nomeModello = request.getParameter("nomeModello");
		String produttore = request.getParameter("produttore");
		String tipo = request.getParameter("tipo");
		
	}

}
