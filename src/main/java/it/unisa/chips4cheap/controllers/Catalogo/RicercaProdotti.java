package it.unisa.chips4cheap.controllers.Catalogo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.ProdottoDAO;
import it.unisa.chips4cheap.model.DTO.Prodotto;

@WebServlet("/RicercaProdotti")
public class RicercaProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RicercaProdotti() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource da = (DataSource) getServletContext().getAttribute("DataSource");

		String nomeModello = request.getParameter("nomeModello");
		String produttore = request.getParameter("produttore");
		String tipo = request.getParameter("tipo");
		String minPrezzo = request.getParameter("prezzoMin");
		String maxPrezzo = request.getParameter("prezzoMax");

		int minprezz = (minPrezzo != null && !minPrezzo.isBlank()) ? Integer.parseInt(minPrezzo) : 0;
		int maxprezz = (maxPrezzo != null && !maxPrezzo.isBlank()) ? Integer.parseInt(maxPrezzo) : Integer.MAX_VALUE;

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String json;

		try {
			ProdottoDAO prodottoDAO = new ProdottoDAO(da);
			ArrayList<Prodotto> prodotti = prodottoDAO.doFilter(nomeModello, produttore, tipo, minprezz, maxprezz);
			json = toJson(prodotti);
		} catch (Exception e) {
			e.printStackTrace();
			json = "[]"; // in caso di errore, il client riceve comunque un JSON valido (array vuoto)
		}

		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}
	
	
	private String toJson(ArrayList<Prodotto> prodotti) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		for (int i = 0; i < prodotti.size(); i++) {
			Prodotto p = prodotti.get(i);

			sb.append("{");
			sb.append("\"nomeModello\":\"").append(escapeJson(p.getNomeModello())).append("\",");
			sb.append("\"produttore\":\"").append(escapeJson(p.getnCAutore())).append("\",");
			sb.append("\"tipo\":\"").append(escapeJson(p.getTipo())).append("\",");
			sb.append("\"prezzo\":").append(p.getPrezzo()).append(",");
			sb.append("\"sconto\":").append(p.getSconto()).append(",");
			sb.append("\"descrizione\":\"").append(escapeJson(p.getDescrizione())).append("\",");
			sb.append("\"imagine\":\"").append(escapeJson(p.getImagine())).append("\"");
			sb.append("}");

			if (i < prodotti.size() - 1) {
				sb.append(",");
			}
		}

		sb.append("]");
		return sb.toString();
	}

	private String escapeJson(String value) {
		if (value == null) {
			return "";
		}
		return value
				.replace("\\", "\\\\")
				.replace("\"", "\\\"")
				.replace("\n", "\\n")
				.replace("\r", "\\r")
				.replace("\t", "\\t");
	}
}