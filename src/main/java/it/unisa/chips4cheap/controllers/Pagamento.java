package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import it.unisa.chips4cheap.model.DTO.Prodotto;

/**
 * Servlet implementation class Pagamento
 */
@WebServlet("/common/Pagamento")
public class Pagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pagamento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
        
		ArrayList<Prodotto> carrello = null;
        if (session != null) {
        	carrello = (ArrayList<Prodotto>) session.getAttribute("carrello");
        }

        if (carrello == null || carrello.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/Carrello"); // se l'individuo conosciuto anche come utente va al checkout senza niente 
            return;
        }
        
		request.getRequestDispatcher("/WEB-INF/views/common/pagamento.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
