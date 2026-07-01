package it.unisa.chips4cheap.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import it.unisa.chips4cheap.model.DTO.Annuncio;

public class AnnuncioDAO implements InterfaceDAO<Annuncio>{
	private DataSource ds;
	
	public AnnuncioDAO(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void doSave(Annuncio elemet) {
		try(Connection c = ds.getConnection()){
			PreparedStatement p = c.prepareStatement("Insert into Annuncio(Titolo,Data_Pubblicazione,Descrizione) values (?,?,?)");
			p.setString(1, elemet.getTitolo());
			p.setDate(2,Date.valueOf(elemet.getDataPublicazione()));
			p.setString(3, elemet.getText());
			
			p.executeUpdate();
			
			p.close();
		}catch(SQLException s) {
			s.printStackTrace();
		}
		
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
				 new Annuncio(
							s.getInt("IDAnnuncio"),
							s.getString("Titolo"),
							s.getDate("Data_Pubblicazione").toLocalDate(),
							s.getString("Descrizione")
						);
			}
			return 			
		}catch(SQLException c){
			c.printStackTrace();
		}
		return null;
	}

	@Override
	public void doUpdate(Annuncio element) {
		// TODO Auto-generated method stub
		
	}

	
}
