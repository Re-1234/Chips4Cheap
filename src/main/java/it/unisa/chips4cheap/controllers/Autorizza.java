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

        String errore = null;

        if (email == null || email.trim().isEmpty() ||
            password == null || password.isEmpty()) {
            
            errore = "Tutti i campi sono obbligatori.";
            
        } else if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
            errore = "Il formato dell'email inserito non è valido.";
        }

        AccountDAO dao = new AccountDAO();
        Account account = null;

        if (errore == null) {
            try {
                account = dao.doRetrieve(email.trim());
                
                if (account == null) {
                    errore = "Email o Password errati. Riprova.";
                } else {
                    String hashedPassword = hashPasswordSHA512(password);
                    if (!hashedPassword.equals(account.getPassword())) {
                        errore = "Email o Password errati. Riprova.";
                        account = null;
                    }
                }
            } catch (Exception e) {
                errore = "Si è verificato un errore durante l'autenticazione. Riprova più tardi.";
                e.printStackTrace();
            }
        }

        if (errore != null) {
            request.setAttribute("erroreServer", errore);
            request.getRequestDispatcher("/Login").forward(request, response);
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("account", account);
            session.setAttribute("role", "user"); 
            
            request.getRequestDispatcher("/common/AreaPersonale").forward(request, response);
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