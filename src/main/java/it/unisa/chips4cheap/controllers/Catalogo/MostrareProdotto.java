package it.unisa.chips4cheap.controllers.Catalogo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.ProdottoDAO;
import it.unisa.chips4cheap.model.DTO.Prodotto;

/**
 * Servlet implementation class MostrareProdotto
 */
@WebServlet("/MostrareProdotto")
public class MostrareProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public MostrareProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
		String nomeModello = request.getParameter("id");
		ProdottoDAO prodottoDAO = new ProdottoDAO(dataSource);
		Prodotto p = prodottoDAO.doSearchElement(nomeModello);
		
		request.setAttribute("NomeModello", p.getNomeModello());
		request.setAttribute("Descrizione", p.getDescrizione());
		request.setAttribute("Image", p.getImagine());
		request.setAttribute("Prezzo", p.getPrezzo());
		request.setAttribute("PrezzoScontato",p.getPrezzoScontato());
		request.getRequestDispatcher("WEB-INF\\views\\Prodotto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
