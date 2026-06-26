package it.unisa.chips4cheap.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import it.unisa.chips4cheap.model.DTO.Account;

// Il filtro intercetterà TUTTE le richieste del sito per controllare quali proteggere
@WebFilter("/*") 
public class AutenticazioneFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // Cast degli oggetti generici a quelli HTTP necessari per gestire URL e Sessioni
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getServletPath();
        
        // Se l'URL non richiede protezione, lascia passare subito
        if (!path.startsWith("/admin/") && !path.startsWith("/common/")) {
            chain.doFilter(request, response);
            return; 
        }
        
        // Controllo dell'account in sessione
        HttpSession session = httpRequest.getSession(false);
        Account account = (session != null) ? (Account) session.getAttribute("account") : null;
        
        boolean autorizzato = false;
        
        if (account != null) {
            if (path.startsWith("/admin/")) {
                // Controllo tramite il metodo del tuo Bean Account
                autorizzato = account.isAmministratore(); 
            } else if (path.startsWith("/common/")) {
                autorizzato = true; 
            }
        }
        
        if (autorizzato) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
        }
    }
}