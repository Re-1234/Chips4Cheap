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

@WebServlet("/common/ModificaAccount")
public class ModificaAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public ModificaAccount() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/common/modificaAccount.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
    	Account accountLoggato = (Account) session.getAttribute("account");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String vecchiaPassword = request.getParameter("vecchiaPassword");
        String nuovaPassword = request.getParameter("password");
        String via = request.getParameter("Via");
        String cap = request.getParameter("Cap");
        String numeroCivico = request.getParameter("NumeroCivico");

        if (username == null || username.trim().isEmpty() ||
            vecchiaPassword == null || vecchiaPassword.isEmpty() ||
            nuovaPassword == null || nuovaPassword.isEmpty() ||
            via == null || via.trim().isEmpty() ||
            cap == null || !cap.trim().matches("^\\d{5}$") ||
            numeroCivico == null || !numeroCivico.trim().matches("^\\d+$")) {
            
            request.setAttribute("erroreServer", "I dati inseriti non sono validi o sono incompleti.");
            request.getRequestDispatcher("/WEB-INF/common/modificaAccount.jsp").forward(request, response);
            return;
        }

        String hashedVecchia = hashPasswordSHA512(vecchiaPassword);
        if (!hashedVecchia.equals(accountLoggato.getPassword())) {
            request.setAttribute("erroreServer", "La vecchia password inserita non è corretta.");
            request.getRequestDispatcher("/WEB-INF/common/modificaAccount.jsp").forward(request, response);
            return;
        }

            accountLoggato.setUsername(username.trim());
            accountLoggato.setPassword(hashPasswordSHA512(nuovaPassword));
            accountLoggato.setVia(via.trim());
            accountLoggato.setCap(cap.trim());
            accountLoggato.setNumeroCivico(Integer.parseInt(numeroCivico.trim()));

            DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
            AccountDAO dao = new AccountDAO(ds);
            dao.doUpdate(accountLoggato);

            session.setAttribute("account", accountLoggato);
            response.sendRedirect(request.getContextPath() + "/common/AreaPersonale");
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
            throw new RuntimeException(e);
        }
    }
}