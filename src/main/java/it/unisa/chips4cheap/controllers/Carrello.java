package it.unisa.chips4cheap.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;

import it.unisa.chips4cheap.model.DTO.Prodotto;

/**
 * Servlet implementation class Carello
 */
@WebServlet("/Carrello")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/carrello.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    StringBuilder sb = new StringBuilder();
	    String linea;
	    try (java.io.BufferedReader reader = request.getReader()) {
	        while ((linea = reader.readLine()) != null) {
	            sb.append(linea);
	        }
	    }

	    JSONObject jsonRequest = new JSONObject(sb.toString());
	    String modello = jsonRequest.getString("modello");
	    String azione = jsonRequest.getString("azione");

	    HttpSession session = request.getSession();	  
	    ArrayList<Prodotto> carrello = (ArrayList<Prodotto>) session.getAttribute("carrello");

	    if (carrello == null) {
	        throw new IllegalStateException("Il carrello non è presente nella sessione utente."); // dovrebbe essere impossibbile che il carrello non sia inzializzato in sessione anche se vuoto
	    }

	    boolean rimosso = false;
	    int nuovaQuantita = 0;
	    double nuovoSubtotale = 0.0;

	    Prodotto prodottoTarget = null;
	    for (Prodotto p : carrello) {
	        if (p.getNomeModello().equalsIgnoreCase(modello)) { // ignoreCase è una buona idea? la chiave è case sensitive
	            prodottoTarget = p;
	            break;
	        }
	    }

	    if (prodottoTarget != null) {
	        if ("rimuovi".equalsIgnoreCase(azione)) {
	            carrello.remove(prodottoTarget);
	            rimosso = true;
	        } 
	        else if ("aggiungi".equalsIgnoreCase(azione)) {
	            int q = prodottoTarget.getQuantità() + 1;
	            prodottoTarget.setQuantità(q);
	            nuovaQuantita = q;
	            nuovoSubtotale = prodottoTarget.getPrezzo() * q;
	        } 
	        else if ("sottrai".equalsIgnoreCase(azione)) {
	            int q = prodottoTarget.getQuantità() - 1;
	            if (q <= 0) {
	                carrello.remove(prodottoTarget);
	                rimosso = true;
	            } else {
	                prodottoTarget.setQuantità(q);
	                nuovaQuantita = q;
	                nuovoSubtotale = prodottoTarget.getPrezzo() * q;
	            }
	        }
	    }

	    double nuovoTotale = 0.0;
	    for (Prodotto p : carrello) {
	        nuovoTotale += p.getPrezzo() * p.getQuantità();
	    }

	    boolean carrelloVuoto = carrello.isEmpty();

	    JSONObject jsonResponse = new JSONObject();
	    jsonResponse.put("rimosso", rimosso);
	    jsonResponse.put("nuovaQuantita", nuovaQuantita);
	    jsonResponse.put("nuovoSubtotale", nuovoSubtotale);
	    jsonResponse.put("nuovoTotale", nuovoTotale);
	    jsonResponse.put("carrelloVuoto", carrelloVuoto);

	    response.getWriter().write(jsonResponse.toString());
	}

}
