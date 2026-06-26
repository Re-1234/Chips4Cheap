package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.jdbc.pool.DataSource;

import model.DTO.ProdottoRicevuta;

public class ProdottoRicevutaDAO implements InterfaceDAO<ProdottoRicevuta>{

	@Override
	public void doSave(ProdottoRicevuta elemet) {
		if(elemet == null) {
			throw new NullPointerException();
		}
		Context init;
		try{
			init = new InitialContext();
			Context ext = (Context) init.lookup("java:comp/env");
			DataSource ds = (DataSource) ext.lookup("jdbc/chips4cheap");
			Connection c = ds.getConnection();
			PreparedStatement pre = c.prepareStatement("Insert into ProdottoRicevuta(Prezzo , IDRicevutaFiscale , email , NomeModello , Quantità , image , tipo) values (?,?,?,?,?,?,?)"); 
			pre.setDouble(1,elemet.getPrezzo());
			pre.setInt(2, elemet.getIDRicevutaFiscale());
			pre.setString(3,elemet.getEmail());
			pre.setString(4, elemet.getNomeModello());
			pre.setInt(5, elemet.getQuantità());
			pre.setString(6,elemet.getImagine());
			pre.setString(7,elemet.getTipo());
			pre.executeUpdate();
			pre.close();
			c.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(NamingException n){
			n.printStackTrace();
		}
		
	}

	@Override
	public void doDelete(ProdottoRicevuta element) {
		throw new NonSupportatoException();
	}

	@Override
	public ProdottoRicevuta doSearchElement(Object o) {
		throw new NonSupportatoException();
	}
	
	
	public ProdottoRicevuta doSearchElement(String nomeModello , int idRicevutaFiscale){
		if(nomeModello == null) {
			throw new RuntimeException("nomeModello è uguale a null");
		}
		
		Context init;
		try {
			init = new InitialContext();
			Context expt = (Context) init.lookup("java:comp/env");
			DataSource ds = (DataSource) expt.lookup("jdbc/chips4cheap");	
			Connection c = ds.getConnection();
			try(PreparedStatement p = c.prepareStatement("Select * From ProdottoRicevuta Where NomeModello = ? and IDRicevutaFiscale = ?")){
				p.setString(1, nomeModello);
				p.setInt(2 , idRicevutaFiscale);
				ResultSet resultSet = p.executeQuery();
				
				if(resultSet.next()){
					
					ProdottoRicevuta p1 = new ProdottoRicevuta(resultSet.getString("Produttore"),resultSet.getInt("IDRicevutaFiscale"),resultSet.getString("email"),resultSet.getString("NomeModello"),resultSet.getDouble("Prezzo"),resultSet.getString("tipo"),resultSet.getInt("Quantità"),resultSet.getString("image")); 
					resultSet.close();
					c.close();
					return p1;
				}
				
				return null; 
			}
		}catch(NamingException e){
			e.printStackTrace();
		}catch(SQLException s){
			s.printStackTrace();
		}
		
		return null;
	}
	

	@Override
	public void doUpdate(ProdottoRicevuta element) {
		throw new NonSupportatoException();
	}

}
