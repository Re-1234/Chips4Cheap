package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import it.unisa.chips4cheap.model.DAO.RicevutaFiscaleDAO;
import it.unisa.chips4cheap.model.DTO.RicevutaFiscale;

/**
 * Servlet implementation class CercaRicevute
 */
@WebServlet("/admin/CercaRicevute")
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
		
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
		    sb.append(line);
		}
		
		JSONObject jsonRequest = new JSONObject(sb.toString());
		
		String emailCliente = jsonRequest.optString("emailCliente", ""); //  da null invece di esplodere come con getString, cosi non filtriamo per quello;
		String jSONdataInizio = jsonRequest.optString("dataInizio", "");
		String jSONdataFine = jsonRequest.optString("dataFine", "");
		
		LocalDate dataInizio = null;
		LocalDate dataFine = null;
		
		if(!jSONdataInizio.equals("")) {	// potenzialmente serve controlli per errori
			dataInizio = LocalDate.parse(jSONdataInizio);
		}
		if(!jSONdataFine.equals("")) {
			dataFine = LocalDate.parse(jSONdataFine);
		}
		
		JSONArray jsonArrayResponse = new JSONArray();
		
		try {
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			
			RicevutaFiscaleDAO ricevutaDAO = new RicevutaFiscaleDAO(ds);
			
			ArrayList<RicevutaFiscale> listaRicevute = ricevutaDAO.doFilter(emailCliente, "", dataInizio, dataFine); // stringa vuota perchè la jsp non filtra al momento per metodo pagamento
			
			if (listaRicevute != null) {
				for (RicevutaFiscale r : listaRicevute) {
					JSONObject jsonRicevuta = new JSONObject();
					
					jsonRicevuta.put("IDRicevutaFiscale", r.getIdRicevutaFiscale());
					jsonRicevuta.put("emailUtente", r.getEmail()); 
					
					jsonRicevuta.put("dataEmissione", r.getLocalDate());
					
					jsonRicevuta.put("metodoPagamento", r.getMetodoPagamento());
					jsonRicevuta.put("via", r.getVia());
					
					jsonArrayResponse.put(jsonRicevuta);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		PrintWriter out = response.getWriter();
		out.print(jsonArrayResponse.toString());
		// out.flush();
	}

}
