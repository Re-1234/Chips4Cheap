package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.AccountDAO;
import it.unisa.chips4cheap.model.DTO.Account;

import java.nio.charset.StandardCharsets;

@WebServlet("/Autorizza")
public class Autorizza extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public Autorizza() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/Login");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.trim().isEmpty() || password == null || password.isEmpty()) { // permettiamo password con spazi vuoti iniziali e finali?
            request.setAttribute("erroreServer", "Tutti i campi sono obbligatori.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        } 
        
        if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
            request.setAttribute("erroreServer", "Il formato dell'email inserito non è valido.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        }

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        AccountDAO dao = new AccountDAO(ds);
        Account account = dao.doSearchElement(email.trim());
            
            
        if (account != null && hashPasswordSHA512(password).equals(account.getPassword())) {
        	HttpSession session = request.getSession(true);
            session.setAttribute("account", account);
            request.getRequestDispatcher("/common/AreaPersonale").forward(request, response);
        } else {
             request.setAttribute("erroreServer", "Email o Password errati. Riprova.");
             request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
            
    }

    private String hashPasswordSHA512(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Errore di sistema: algoritmo SHA-512 non trovato.", e);
        }
    }
}