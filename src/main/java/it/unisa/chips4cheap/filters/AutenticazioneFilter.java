package it.unisa.chips4cheap.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// Il filtro intercetterà TUTTE le richieste del sito per controllare quali proteggere
@WebFilter("/*") 
public class AutenticazioneFilter extends HttpFilter {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        String path = request.getServletPath();
        
        if (!path.startsWith("/admin/") && !path.startsWith("/common/")) {
            chain.doFilter(request, response);
            return; 
        }
        
        // Controllo dell'account in sessione
        HttpSession session = request.getSession(false);
        Account account = (session != null) ? (Account) session.getAttribute("account") : null;
        
        boolean autorizzato = false;
        
        if (account != null) {
            if (path.startsWith("/admin/")) {
                autorizzato = account.isAmministratore();
            } else if (path.startsWith("/common/")) {
                autorizzato = true; 
            }
        }
        
        if (autorizzato) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/Login");
        }
    }
}