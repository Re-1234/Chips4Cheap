package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import it.unisa.chips4cheap.model.DAO.RicevutaFiscaleDAO;
import it.unisa.chips4cheap.model.DTO.Account;
import it.unisa.chips4cheap.model.DTO.RicevutaFiscale;
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
        
        RicevutaFiscaleDAO dao = new RicevutaFiscaleDAO();
        ArrayList<RicevutaFiscale> listaRicevute = dao.doRetrieveByEmail(accountLoggato.getEmail());
            
        request.setAttribute("ricevute", listaRicevute);
        request.getRequestDispatcher("/WEB-INF/views/common/ricevuteAccount.jsp").forward(request, response);           
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
