package it.unisa.chips4cheap.controllers;

import java.io.*;
import java.util.UUID;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.ProdottoDAO;
import it.unisa.chips4cheap.model.DTO.Prodotto;

@WebServlet("/admin/ControlloImmagini")
@MultipartConfig(
    maxFileSize = 5 * 1024 * 1024,      // max 5 MB per file
    maxRequestSize = 10 * 1024 * 1024,  // max 10 MB per richiesta
    fileSizeThreshold = 2 * 1024 * 1024 // 2 MB di soglia per la memoria
)
public class ControlloImmagini extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final String UPLOAD_DIR = "images" + File.separator + "productImages";

    private ProdottoDAO prodottoDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        if (ds == null) {
            throw new ServletException("DataSource non disponibile nel contesto applicativo.");
        }
        prodottoDAO = new ProdottoDAO(ds);
        
        // Creazione automatica della cartella sul server se non esiste
        String uploadPath = getServletContext().getRealPath(File.separator + UPLOAD_DIR);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    /*		--------HO TOLTO I GET, POSSO USARE <IMG> PER VISUALIZZARE IMMAGINI------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null && action.equalsIgnoreCase("show")){
            String nomeModello = request.getParameter("nomeModello");
            
            Prodotto prodotto = prodottoDAO.doSearchElement(nomeModello);
            
            if (prodotto != null && prodotto.getImagine() != null) {
                String absolutePath = getServletContext().getRealPath("/" + prodotto.getImagine());
                
                String mimeType = getServletContext().getMimeType(absolutePath);
                if (mimeType == null) {
                    mimeType = "image/jpeg"; 
                }
                
                response.setContentType(mimeType);
                
                File fileImmagine = new File(absolutePath);
                if (fileImmagine.exists()) {
                    try (InputStream is = new FileInputStream(fileImmagine)) {
                        OutputStream os = response.getOutputStream();
                        is.transferTo(os); 
                    } catch (IOException ioe) {
                        System.err.println("Error:" + ioe.getMessage());
                    }
                }
            }
        }
    }
    */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String nomeModello = request.getParameter("nomeModello");
        
        if ("upload".equalsIgnoreCase(action)) {
            Part part = request.getPart("imagine"); 
            if (part != null) {
                String originalFileName = part.getSubmittedFileName();
                if (originalFileName != null && !originalFileName.isEmpty() && part.getSize() > 0) {
                    
                    String uniqueFileName = buildUniqueFileName(part);
                    String uploadPath = getServletContext().getRealPath(File.separator + UPLOAD_DIR + File.separator + uniqueFileName);
                    
                    Prodotto prodotto = prodottoDAO.doSearchElement(nomeModello);
                    if (prodotto != null) {
                        // Rimuovo la vecchia immagine per evitare spazzatura
                    	if(!prodotto.getImagine().equalsIgnoreCase("images/productImages/default.svg")) {
                    		eliminaFileFisico(prodotto.getImagine());	// UPDATE: elimino soltanto se non è il placeholder
                    	}
                        part.write(uploadPath);
                        prodotto.setImagine("images/productImages/" + uniqueFileName); // non posso usare upload-dir, la jsp impazzisce se ha \ come separatore, tipo windows
                        prodottoDAO.doUpdate(prodotto);
                        System.out.println("Nuovo file salvato: " + uploadPath); // debugging
                    }
                }
            }
            
        } else if ("delete".equalsIgnoreCase(action)) {
            Prodotto prodotto = prodottoDAO.doSearchElement(nomeModello);
            if (prodotto != null) {
                eliminaFileFisico(prodotto.getImagine());
            }
        }
    }

    private String buildUniqueFileName(Part part) {
        String originalName = part.getSubmittedFileName();
        String extension;
        if (originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        } else {
            extension = "";
        }
        return UUID.randomUUID() + extension;
    }

    private void eliminaFileFisico(String relativePath) {
        if (relativePath != null && !relativePath.isEmpty()) {
            String absolutePath = getServletContext().getRealPath("/" + relativePath);	// perchè non va con File.Separator?
            if (absolutePath != null) {
                File file = new File(absolutePath);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }
}