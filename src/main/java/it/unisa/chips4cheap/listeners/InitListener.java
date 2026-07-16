package it.unisa.chips4cheap.listeners;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DAO.AnnuncioDAO;
import it.unisa.chips4cheap.model.DTO.Annuncio;

@WebListener
public class InitListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce){
		ServletContext context = sce.getServletContext();
		DataSource ds = null;
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/Chips4CheapDB");
		}catch(NamingException e){
			System.out.println("Error:" + e.getMessage());
		}
		context.setAttribute("DataSource", ds);
		
		if (ds != null) {
			AnnuncioDAO annuncioDAO = new AnnuncioDAO(ds);
			ArrayList<Annuncio> listaAnnunci = annuncioDAO.doRetrieveByAll();
			
			// per l'aside lo metto nel contesto globale
			context.setAttribute("tuttiAnnunci", listaAnnunci);
		} else {
			System.out.println("Impossibile caricare gli annunci: DataSource nullo.");
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}