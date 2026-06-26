package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * Servlet implementation class RicevuteAccount
 */
@WebServlet("/common/RicevuteAccount")
public class RicevuteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicevuteAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        Account accountLoggato = (Account) session.getAttribute("account");

        try {
            RicevutaFiscaleDAO dao = new RicevutaFiscaleDAO();
            List<RicevutaFiscale> listaRicevute = dao.doRetrieveByEmail(accountLoggato.getEmail());
            
            request.setAttribute("ricevute", listaRicevute);
            request.getRequestDispatcher("/WEB-INF/common/ricevuteAccount.jsp").forward(request, response);
            
        } catch (Exception e) {
           // Riguarda come usare le pagine di errore nel deployment descriptor
            throw new ServletException("Errore nel recupero delle ricevute fiscali", e);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
