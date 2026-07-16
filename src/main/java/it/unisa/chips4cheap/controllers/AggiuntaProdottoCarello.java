package it.unisa.chips4cheap.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.ProdottoDAO;
import it.unisa.chips4cheap.model.DTO.Prodotto;

/**
 * Servlet implementation class AggiuntaProdottoCarello
 */
@WebServlet("/AggiuntaProdottoCarello")
public class AggiuntaProdottoCarello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiuntaProdottoCarello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeModello = request.getParameter("NomeModello");
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ProdottoDAO prodot = new ProdottoDAO(ds);
		Prodotto p = prodot.doSearchElement(nomeModello);
		HttpSession http =	request.getSession();
		ArrayList<Prodotto> pro = (ArrayList<Prodotto>) http.getAttribute("carello");
		if(pro == null){
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			prodotti.add(p);
			http.setAttribute("carello", prodotti);
			System.out.println(prodotti);
		}else{
			boolean cista = false;
			for(Prodotto pe: pro){
				if(pe.getNomeModello().equals(p.getNomeModello())){
					cista = true;
				}
			}
			if(!cista){
				pro.add(p);
				http.setAttribute("carello", pro);
			}
		}
		request.setAttribute("NomeModello", p.getNomeModello());
		request.setAttribute("Descrizione", p.getDescrizione());
		request.setAttribute("Image", p.getImagine());
		request.setAttribute("Prezzo", p.getPrezzo());
		request.setAttribute("PrezzoScontato",p.getPrezzoScontato());
		request.setAttribute("Sconto", p.getSconto());
		request.setAttribute("Tipo", p.getTipo());
		System.out.println(pro);
		RequestDispatcher r = request.getRequestDispatcher("WEB-INF\\views\\Catalogo.jsp");
		r.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
