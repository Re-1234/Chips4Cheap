package it.unisa.chips4cheap.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import it.unisa.chips4cheap.model.DTO.Annuncio;

public class AnnuncioDAO implements InterfaceDAO<Annuncio>{
	private DataSource ds;
	
	public AnnuncioDAO(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public int doSave(Annuncio elemet){
		try(Connection c = ds.getConnection()){
			PreparedStatement p = c.prepareStatement("Insert into Annuncio(Titolo,Data_Pubblicazione,Descrizione) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
			p.setString(1, elemet.getTitolo());
			p.setDate(2,Date.valueOf(elemet.getDataPublicazione()));
			p.setString(3, elemet.getText());
			
			p.executeUpdate();
			
			ResultSet r = p.getGeneratedKeys();
			
			int res = r.getInt("IDAnnuncio");
			
			r.close();
			p.close();
			return res;
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return -1;
	}

	@Override
	public void doDelete(Annuncio element) {
		try(Connection c = ds.getConnection()){
			PreparedStatement p = c.prepareStatement("Delete From Annuncio where IDAnnuncio = ?");
			p.setInt(1,element.getIdAnnuncio());
			p.executeUpdate();
			p.close();
			c.close();
		}catch(SQLException s){
			s.printStackTrace();
		}
	}

	@Override
	public Annuncio doSearchElement(Object o) {
		if(o instanceof Integer){
			throw new RuntimeException();
		}
		
		Integer f = (Integer) o;
		
		try(Connection c = ds.getConnection()){
			PreparedStatement p = c.prepareStatement("Select * From Annuncio where IDAnnuncio = ?");
			p.setInt(1,f);
			ResultSet s = p.executeQuery();
			if(s.next()){
				return new Annuncio(
							s.getInt("IDAnnuncio"),
							s.getString("Titolo"),
							s.getDate("Data_Pubblicazione").toLocalDate(),
							s.getString("Descrizione")
						);
			}
			return null; 	
		}catch(SQLException c){
			c.printStackTrace();
		}
		return null;
	}

	@Override
	public void doUpdate(Annuncio element) {
		try(Connection c = ds.getConnection()){
			PreparedStatement p = c.prepareStatement("Update Annoucio Set Titolo = ? , Data_Pubblicazione = ? , Descrizione = ? where IDAnnuncio = ?");
			p.setString(1, element.getTitolo());
			p.setDate(2,Date.valueOf(element.getDataPublicazione()));
			p.setString(3, element.getText());
			p.setInt(4, element.getIdAnnuncio());
			p.executeUpdate();
		}catch(SQLException s){
			s.printStackTrace();
		}
	}	
}
