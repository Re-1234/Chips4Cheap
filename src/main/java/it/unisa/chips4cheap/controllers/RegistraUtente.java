package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.AccountDAO;
import it.unisa.chips4cheap.model.DTO.Account;

import java.nio.charset.StandardCharsets;

@WebServlet("/RegistraUtente")
public class RegistraUtente extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public RegistraUtente() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/Registrazione");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("Password1");
        String via = request.getParameter("Via");
        String cap = request.getParameter("Cap");
        String numeroCivico = request.getParameter("NumeroCivico");

        if (email == null || email.trim().isEmpty() ||
            username == null || username.trim().isEmpty() ||
            password == null || password.isEmpty() ||
            via == null || via.trim().isEmpty() ||
            cap == null || cap.trim().isEmpty() ||
            numeroCivico == null || numeroCivico.trim().isEmpty()) {
            
            request.setAttribute("erroreServer", "Tutti i campi sono obbligatori.");
            request.getRequestDispatcher("/WEB-INF/views/registrazione.jsp").forward(request, response);
            return;
        }

        if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
            request.setAttribute("erroreServer", "Il formato dell'email inserito non è valido.");
            request.getRequestDispatcher("/WEB-INF/views/registrazione.jsp").forward(request, response);
            return;
        }

        if (!cap.trim().matches("^\\d{5}$")) {
            request.setAttribute("erroreServer", "Il CAP deve essere composto da esattamente 5 cifre numeriche.");
            request.getRequestDispatcher("/WEB-INF/views/registrazione.jsp").forward(request, response);
            return;
        }

        if (!numeroCivico.trim().matches("^\\d+$")) {
            request.setAttribute("erroreServer", "Il numero civico deve essere un valore numerico.");
            request.getRequestDispatcher("/WEB-INF/views/registrazione.jsp").forward(request, response);
            return;
        }

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        AccountDAO dao = new AccountDAO(ds);

            if (dao.doSearchElement(email.trim()) != null) {
                request.setAttribute("erroreServer", "Questa email è già associata a un account registrato.");
                request.getRequestDispatcher("/WEB-INF/views/registrazione.jsp").forward(request, response);
                return;
            }

            Account nuovoAccount = new Account();
            nuovoAccount.setEmail(email.trim());
            nuovoAccount.setUsername(username.trim());
            nuovoAccount.setPassword(hashPasswordSHA512(password));
            nuovoAccount.setVia(via.trim());
            nuovoAccount.setCap(cap.trim());
            nuovoAccount.setNumeroCivico(Integer.parseInt(numeroCivico.trim()));

            dao.doSave(nuovoAccount);
            
            response.sendRedirect(request.getContextPath() + "/Login");
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