package model.DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DTO.Account;
import model.DTO.Prodotto;
import model.DTO.ProdottoRicevuta;
import model.DTO.RicevutaFiscale;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Servlet implementation class TestDAO
 */
@WebServlet("/TestDAO")
public class TestDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public TestDAO() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		AccountDAO account = new AccountDAO();
		
		ProdottoRicevuta prodotto1 = new ProdottoRicevuta(
			    "AUT001",                      // nCAutore
			    1,                              // iDRicevutaFiscale — DEVE coincidere con quello della RicevutaFiscale
			    "mario.rossi@email.it",         // email — DEVE coincidere con quella della RicevutaFiscale
			    "Modello Alpha",                // nomeModello
			    19.99,                          // prezzo
			    "Chips",                        // tipo
			    2,                               // quantità
			    "alpha.jpg"                     // image
			);

			ProdottoRicevuta prodotto2 = new ProdottoRicevuta(
			    "AUT002",                      // nCAutore
			    1,                              // iDRicevutaFiscale — stesso ID della ricevuta
			    "mario.rossi@email.it",         // stessa email
			    "Modello Beta",
			    14.50,
			    "Chips",
			    1,
			    "beta.jpg"
			);

			ArrayList<ProdottoRicevuta> listaProdotti = new ArrayList<>();
			listaProdotti.add(prodotto1);
			listaProdotti.add(prodotto2);
			
		// 2. Crea la ricevuta fiscale — ATTENZIONE all'email!
		RicevutaFiscale ricevuta = new RicevutaFiscale(
		    1,                          
		    "mario.rossi@email.it",     // email — DEVE coincidere con quella dell'Account
		    listaProdotti,
		    "Carta di credito",
		    LocalDate.now()
		);
		// Se non hai modificato il costruttore, usa invece:
		// RicevutaFiscale ricevuta = new RicevutaFiscale();
		// ricevuta.setEmail("mario.rossi@email.it");

		ArrayList<RicevutaFiscale> listaRicevute = new ArrayList<>();
		listaRicevute.add(ricevuta);

		// 3. Crea l'Account
		Account account1 = new Account(
		    "mariorossi92",          // username
		    "passwordSegreta123",    // password
		    "Via Roma",               // via
		    "80100",                  // cap
		    15,                        // numeroCivico
		    "mario.rossi@email.it",   // email — STESSA email della ricevuta!
		    false,                     // amministratore
		    listaRicevute
		);
		
		RicevutaFiscaleDAO ricevutaFiscale = new RicevutaFiscaleDAO();
		//ricevutaFiscale.doSave();
		ProdottoDAO prodotto = new ProdottoDAO();
		
		ProdottoRicevutaDAO prodottoRicevuta = new ProdottoRicevutaDAO();
		account.doSave(account1);
		System.out.println(prodottoRicevuta.doSearchElement("Modello Alpha",1));
		System.out.println(ricevutaFiscale.doSearchElement(1));
		
		Prodotto prodotto3 = new Prodotto(
			    "AUT001",                                  // nCAutore
			    "Modello Alpha",                            // nomeModello
			    19.99,                                       // prezzo
			    "Patatine artigianali al rosmarino",        // descrizione
			    "Chips",                                     // tipo
			    50,                                           // quantità
			    "alpha.jpg"                                  // imagine
			);
		
		prodotto.doSave(prodotto3);
		
		System.out.println(prodotto.doSearchElement("Modello Alpha"));
		
		prodotto3.setDescrizione("ciao");
		
		prodotto.doUpdate(prodotto3);
		
		System.out.println(prodotto.doSearchElement("Modello Alpha"));
		
		prodotto.doDelete(prodotto3);
		
		System.out.println(account.doSearchElement("mario.rossi@email.it"));
		
		account1.setCap("98235");
		account.doUpdate(account1);
		System.out.println(account.doSearchElement("mario.rossi@email.it"));
		account.doDelete(account1);
		
		
		
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
