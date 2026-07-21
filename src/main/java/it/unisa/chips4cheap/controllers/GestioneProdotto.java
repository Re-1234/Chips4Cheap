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
    	String action = request.getParameter("action"); 
        String nomeModello = request.getParameter("nomeModello");
        String descrizione = request.getParameter("descrizione");
        String tipo = request.getParameter("tipo");
        
        String prezzoStr = request.getParameter("prezzo");
        String quantitaStr = request.getParameter("quantita");
        String scontoStr = request.getParameter("sconto");

        String errore = null;

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDAO prodottoDAO = new ProdottoDAO(ds);
        
        // solito bloccomonolitico di rejex
        if (action == null || (!action.equalsIgnoreCase("add") && !action.equalsIgnoreCase("edit"))) {
            errore = "Azione di sistema non valida.";
        } else if (nomeModello == null || nomeModello.trim().isEmpty() || nomeModello.length() > 50) {
            errore = "Il Nome Modello non può essere vuoto o superare i 50 caratteri.";
        } else if (descrizione == null || descrizione.trim().isEmpty()) {
            errore = "La Descrizione non può essere vuota.";
        } else if (tipo == null || tipo.trim().isEmpty() || tipo.length() > 50) {
            errore = "Il Tipo (Categoria) non può essere vuoto o superare i 50 caratteri.";
        } else if (prezzoStr == null || !prezzoStr.matches("^\\d+(\\.\\d+)?$")) { // numero positivo . numero positivo senno ti ammazzo
            errore = "Il prezzo inserito non è valido. Usa solo numeri positivi.";
        } else if (quantitaStr == null || !quantitaStr.matches("^\\d+$")) {
            errore = "La quantità in magazzino deve essere un numero intero positivo o zero.";
        } else if (scontoStr == null || !scontoStr.matches("^([0-9]|[1-9][0-9]|100)$")) { // o 1 da 0 a 9 o 2 digits una da 1 a 9 l'altra da 0 a 9 senno ti ammazzo di nuovo
            errore = "Lo sconto deve essere una percentuale intera compresa tra 0 e 100.";
        }

        if (errore == null) { // HAI CARICATO UN IMMAGINE?
            Part filePart = request.getPart("imagine");
            
            if (filePart != null && filePart.getSize() > 0) {
                String contentType = filePart.getContentType();
                
                // Se il tipo non rientra tra quelli permessi, blocchiamo l'inserimento
                if (contentType == null || 
                    (!contentType.equalsIgnoreCase("image/png") && 
                     !contentType.equalsIgnoreCase("image/jpeg") && 
                     !contentType.equalsIgnoreCase("image/jpg") && 
                     !contentType.equalsIgnoreCase("image/webp"))) {
                    errore = "Formato immagine non valido. Sono ammessi solo file PNG, JPEG, JPG e WEBP.";
                }
            }
        }

        // Esiste già e stiamo provando ad aggiungere?
        if (errore == null && "add".equalsIgnoreCase(action)) {
            Prodotto prodottoEsistente = prodottoDAO.doSearchElement(nomeModello);
            if (prodottoEsistente != null) {
                errore = "Esiste già un prodotto nel catalogo con il codice '" + nomeModello + "'.";
            }
        }

        // manda errore se ce
        if (errore != null) {
            request.setAttribute("erroreServer", errore);
            
            if ("add".equalsIgnoreCase(action)) {
                request.getRequestDispatcher("/admin/aggiungiProdotto.jsp").forward(request, response);
            } else {
                Prodotto prodottoDaModificare = prodottoDAO.doSearchElement(nomeModello);
                request.setAttribute("prodotto", prodottoDaModificare);
                request.getRequestDispatcher("/admin/modificaProdotto.jsp").forward(request, response);
            }
            return; 
        }

        double prezzo = Double.parseDouble(prezzoStr);
        int quantita = Integer.parseInt(quantitaStr);
        int sconto = Integer.parseInt(scontoStr);
        
        // NOTA non sto facendo trimming o modifiche ai altri campi
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
            request.getRequestDispatcher("/admin/ControlloImmagini?action=upload").include(request, response); // già c'è in richiesta nomeModello
            response.sendRedirect(request.getContextPath() + "/Prodotto?nomeModello=" + nomeModello); // USA IL CONTROLLER EUGENIO
    }
}