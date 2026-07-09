package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import it.unisa.chips4cheap.model.DAO.RicevutaFiscaleDAO;
import it.unisa.chips4cheap.model.DTO.RicevutaFiscale;

/**
 * Servlet implementation class CercaRicevute
 */
@WebServlet("/CercaRicevute")
public class CercaRicevute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CercaRicevute() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/cercaRicevute.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		// 1. Lettura del JSON in ingresso
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
		    sb.append(line);
		}
		
		JSONObject jsonRequest = new JSONObject(sb.toString());
		
		String emailCliente = jsonRequest.optString("emailCliente", "");
		String dataInizio = jsonRequest.optString("dataInizio", "");
		String dataFine = jsonRequest.optString("dataFine", "");
		
		JSONArray jsonArrayResponse = new JSONArray();
		
		try {
			// 2. Recupero il DataSource dal contesto applicativo
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			
			// 3. Istanzio il DAO passandogli il DataSource
			RicevutaFiscaleDAO ricevutaDAO = new RicevutaFiscaleDAO(ds);
			
			// 4. Chiamo un metodo di ricerca (vedi il punto 2 sotto per l'implementazione nel DAO)
			ArrayList<RicevutaFiscale> listaRicevute = ricevutaDAO.doSearchByFilters(emailCliente, dataInizio, dataFine);
			
			// 5. Mappatura DTO -> JSON
			if (listaRicevute != null) {
				for (RicevutaFiscale r : listaRicevute) {
					JSONObject jsonRicevuta = new JSONObject();
					
					// Uso i getter corretti del tuo DTO RicevutaFiscale.java
					jsonRicevuta.put("IDRicevutaFiscale", r.getIdRicevutaFiscale());
					jsonRicevuta.put("emailUtente", r.getEmail()); 
					
					// Assicurati che la data sia convertita in stringa (es. "2023-10-25")
					jsonRicevuta.put("dataEmissione", r.getLocalDate() != null ? r.getLocalDate().toString() : "");
					
					jsonRicevuta.put("metodoPagamento", r.getMetodoPagamento());
					jsonRicevuta.put("via", r.getVia());
					
					jsonArrayResponse.put(jsonRicevuta);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 6. Invio la risposta JSON al JavaScript
		PrintWriter out = response.getWriter();
		out.print(jsonArrayResponse.toString());
		out.flush();
	}

}
