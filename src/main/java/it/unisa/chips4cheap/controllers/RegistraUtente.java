package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

        String errore = null;

        if (email == null || email.trim().isEmpty() ||
            username == null || username.trim().isEmpty() ||
            password == null || password.isEmpty() ||
            via == null || via.trim().isEmpty() ||
            cap == null || cap.trim().isEmpty() ||
            numeroCivico == null || numeroCivico.trim().isEmpty()) {
            
            errore = "Tutti i campi contrassegnati con l'asterisco sono obbligatori.";
            
        } else if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
            errore = "Il formato dell'email inserito non è valido.";
            
        } else if (!cap.matches("^\\d+$")) {
            errore = "Il CAP deve contenere solo numeri.";
            
        } else if (!numeroCivico.matches("^\\d+$")) {
            errore = "Il numero civico deve essere un valore numerico.";
        }

        AccountDAO dao = new AccountDAO();

        if (errore == null) {
            try {
                Account utenteEsistente = dao.doSearchElement(email.trim());
                
                if (utenteEsistente != null) {
                    errore = "Esiste già un account registrato con questa email.";
                }
            } catch (Exception e) {
                errore = "Si è verificato un errore durante la verifica dell'account. Riprova più tardi.";
                e.printStackTrace();
            }
        }

        if (errore != null) {
            request.setAttribute("erroreServer", errore);
            request.getRequestDispatcher("/WEB-INF/views/registrazione.jsp").forward(request, response);
        } else {
            try {
                Account nuovoAccount = new Account();
                nuovoAccount.setEmail(email.trim());
                nuovoAccount.setUsername(username.trim());
                
                String hashedPassword = hashPasswordSHA512(password);
                nuovoAccount.setPassword(hashedPassword);
                
                nuovoAccount.setVia(via.trim());
                nuovoAccount.setCap(cap.trim());
                nuovoAccount.setNumeroCivico(Integer.parseInt(numeroCivico.trim()));

                dao.doSave(nuovoAccount);

                response.sendRedirect(request.getContextPath() + "/Login");
                
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("erroreServer", "Errore imprevisto durante la registrazione. Riprova.");
                request.getRequestDispatcher("/Registrazione").forward(request, response);
            }
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