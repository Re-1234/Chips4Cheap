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

@WebServlet("/admin/ModificaProdotto")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // Uguali a ControlloImmagini
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class ModificaProdotto extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Nome della cartella di destinazione come da tue indicazioni
    private static final String UPLOAD_DIR = "images" + File.separator + "productImages";

    public ModificaProdotto() {
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

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDAO prodottoDAO = new ProdottoDAO(ds);
        
        // 2. Recupero e aggiornamento dei campi testuali del prodotto
        Prodotto prodotto = prodottoDAO.doSearchElement(nomeModello);
        
        if (prodotto != null) {
            prodotto.setPrezzo(prezzo);
            prodotto.setDescrizione(descrizione);
            prodotto.setTipo(tipo);
            prodotto.setQuantità(quantita);
            prodotto.setSconto(sconto);
            
            prodottoDAO.doUpdate(prodotto);
            
         // Outsorced il controllo delle immagini
            request.getRequestDispatcher("/ControlloImmagini?action=upload").include(request, response); // già c'è in richiesta nomeModello
            response.sendRedirect(request.getContextPath() + "/Prodotto?nomeModello=" + nomeModello); // USA IL CONTROLLER EUGENIO
        } else {
            // Se non esiste torna al catalogo
            response.sendRedirect(request.getContextPath() + "/Catalogo");
        }
    }
}