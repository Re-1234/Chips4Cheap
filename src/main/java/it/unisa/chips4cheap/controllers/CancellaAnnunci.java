package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.json.JSONObject;

import it.unisa.chips4cheap.model.DAO.AnnuncioDAO;
import it.unisa.chips4cheap.model.DTO.Annuncio;

/**
 * Servlet implementation class CancellaAnnunci
 */
@WebServlet("/admin/CancellaAnnunci")
public class CancellaAnnunci extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaAnnunci() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/cancellaAnnunci.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    JSONObject jsonResponse = new JSONObject();
	    
	    int idAnnuncio = Integer.parseInt(request.getParameter("idAnnuncio").trim());
	        
	    DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	    AnnuncioDAO dao = new AnnuncioDAO(ds);
	        
	    Annuncio annuncioDaEliminare = new Annuncio(); // devo creare un pupazzo per cancellare per via del doDelete che accetta soltanto il DTO
	    annuncioDaEliminare.setIdAnnuncio(idAnnuncio);
	        
	    dao.doDelete(annuncioDaEliminare);
	       
	    getServletContext().setAttribute("tuttiAnnunci", dao.doRetrieveByAll());  
	    jsonResponse.put("successo", true);
	    
	    response.getWriter().write(jsonResponse.toString());
	}

}
