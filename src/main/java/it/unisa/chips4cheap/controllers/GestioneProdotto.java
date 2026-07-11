package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.ProdottoDAO;
import it.unisa.chips4cheap.model.DTO.Prodotto;

@WebServlet("/admin/GestioneProdotto")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // Uguali a ControlloImmagini
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class GestioneProdotto extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final String UPLOAD_DIR = "images" + File.separator + "productImages";

    public GestioneProdotto() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/Catalogo");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeModello = request.getParameter("nomeModello");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        String descrizione = request.getParameter("descrizione");
        String tipo = request.getParameter("tipo");
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        int sconto = Integer.parseInt(request.getParameter("sconto"));
        String action = request.getParameter("action"); // hey cosi faccio sia update che save di un prodotto

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDAO prodottoDAO = new ProdottoDAO(ds);
        
        if ("add".equalsIgnoreCase(action)) {

        	Prodotto nuovoProdotto = new Prodotto();
            nuovoProdotto.setNomeModello(nomeModello);
            nuovoProdotto.setPrezzo(prezzo);
            nuovoProdotto.setDescrizione(descrizione);
            nuovoProdotto.setTipo(tipo);
            nuovoProdotto.setQuantità(quantita);
            nuovoProdotto.setSconto(sconto);
            nuovoProdotto.setImagine("images/productImages/default.svg"); // Placeholder
            
            prodottoDAO.doSave(nuovoProdotto);
            
        } else if ("edit".equalsIgnoreCase(action)) {

        	Prodotto prodottoEsistente = prodottoDAO.doSearchElement(nomeModello);
            
            if (prodottoEsistente != null) { // non permettiamo di modificare il nome?
                prodottoEsistente.setPrezzo(prezzo);
                prodottoEsistente.setDescrizione(descrizione);
                prodottoEsistente.setTipo(tipo);
                prodottoEsistente.setQuantità(quantita);
                prodottoEsistente.setSconto(sconto);
                
                prodottoDAO.doUpdate(prodottoEsistente);
            } else {
                response.sendRedirect(request.getContextPath() + "/Catalogo");
                return;
            }
        }
            
         // Outsorced il controllo delle immagini
            request.getRequestDispatcher("/ControlloImmagini?action=upload").include(request, response); // già c'è in richiesta nomeModello
            response.sendRedirect(request.getContextPath() + "/Prodotto?nomeModello=" + nomeModello); // USA IL CONTROLLER EUGENIO
    }
}