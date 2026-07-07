package it.unisa.chips4cheap.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DTO.*;

public class ProdottoRicevutaDAO implements InterfaceDAO<ProdottoRicevuta>{
	
	private DataSource ds;
	
	public ProdottoRicevutaDAO(DataSource ds){
		this.ds = ds;
	}
	
	@Override
	public int doSave(ProdottoRicevuta elemet) {
		if(elemet == null) {
			throw new NullPointerException();
		}
		
		try(Connection c = ds.getConnection()){
			PreparedStatement pre = c.prepareStatement("Insert into ProdottoRicevuta(Prezzo , Produttore , IDRicevutaFiscale , email , NomeModello , Quantità , image , tipo) values (?,?,?,?,?,?,?,?)"); 
			pre.setDouble(1,elemet.getPrezzo());
			pre.setString(2, elemet.getnCAutore());
			pre.setInt(3, elemet.getIDRicevutaFiscale());
			pre.setString(4,elemet.getEmail());
			pre.setString(5, elemet.getNomeModello());
			pre.setInt(6, elemet.getQuantità());
			pre.setString(7,elemet.getImagine());
			pre.setString(8,elemet.getTipo());
			
			int d = pre.executeUpdate();
			pre.close();
			c.close();
			return d;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void doDelete(ProdottoRicevuta element) {
		throw new NonSupportatoException();
	}

	@Override
	public ProdottoRicevuta doSearchElement(Object o) {
		throw new NonSupportatoException();
	}
	
	public ArrayList<ProdottoRicevuta> doSearchElementByIDRic(int idRicevutaFiscale){
		
		try(Connection c = ds.getConnection()){
			PreparedStatement p = c.prepareStatement("Select * From ProdottoRicevuta where IDRicevutaFiscale = ?");
			p.setInt(1 , idRicevutaFiscale);
			ResultSet s = p.executeQuery();
			ArrayList<ProdottoRicevuta> prodotti = new ArrayList<>();
			
			while(s.next()){
				prodotti.add(new ProdottoRicevuta(s.getString("Produttore"),s.getInt("IDRicevutaFiscale"),s.getString("email"),s.getString("NomeModello"),s.getDouble("Prezzo"),s.getString("Tipo"),s.getInt("Quantità"),s.getString("image")));
			}
			
			return prodotti;
		}catch(SQLException sq){
			sq.printStackTrace();
		}
		
		return null;
	}
	
	
	public ProdottoRicevuta doSearchElement(String nomeModello , int idRicevutaFiscale){
		if(nomeModello == null) {
			throw new RuntimeException("nomeModello è uguale a null");
		}
		try(Connection c = ds.getConnection()){
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
