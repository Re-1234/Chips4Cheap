package it.unisa.chips4cheap.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DTO.*;

public class RicevutaFiscaleDAO implements InterfaceDAO<RicevutaFiscale>{
	
	private javax.sql.DataSource ds; 
	
	public RicevutaFiscaleDAO(DataSource ds){
		this.ds = ds;
	}
	
	@Override
	public int doSave(RicevutaFiscale elemet){
			try {
				Connection connection = ds.getConnection();
				PreparedStatement pre = connection.prepareStatement("Insert into RicevutaFiscale(email,metodoPagamento,DataEmissione) Values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
				pre.setString(1,elemet.getEmail());
				pre.setString(2,elemet.getMetodoPagamento());
				pre.setDate(3,Date.valueOf(elemet.getLocalDate()));
				pre.executeUpdate();
				ResultSet r = pre.getGeneratedKeys();
				int id = r.getInt("IDRicevutaFiscale");
				r.close();
				pre.close();
				connection.close();
				return id;
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return -1;
	}
	
	@Override
	public void doDelete(RicevutaFiscale element){
		throw new NonSupportatoException();
	}
	
	public RicevutaFiscale doSearchElement(Object o){
		if(!(o instanceof Integer) || o == null) {
			throw new RuntimeException("Mandato un oggetto che non è un intero");
		}
		
		Integer intero = (Integer) o;
			try(Connection connection = ds.getConnection()){
				PreparedStatement preparedStatement = connection.prepareStatement("Select * From RicevutaFiscale Where IdRicevutaFiscale = ?");
				preparedStatement.setInt(1,intero);
				ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
						RicevutaFiscale ricevuta1 = new RicevutaFiscale(resultSet.getInt("IDRicevutaFiscale"),resultSet.getString("email"),resultSet.getString("metodoPagamento"),resultSet.getDate("DataEmissione").toLocalDate(),resultSet.getString("via"),resultSet.getString("Cap"),resultSet.getInt("NumeroCivico"));
						resultSet.close();
						return ricevuta1;
				}else{
					resultSet.close();
					return null;
				}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		
		return new RicevutaFiscale();
	}
	
	public ArrayList<RicevutaFiscale> doSearchByEmail(String email){
		try(Connection con = ds.getConnection()){
			PreparedStatement p = con.prepareStatement("Select * From RicevutaFiscale where email = ?");
			p.setString(1, email);
			ResultSet r = p.executeQuery();
			ArrayList<RicevutaFiscale> ricevute = new ArrayList<>();
			while(r.next()){
				ricevute.add(new RicevutaFiscale(r.getInt("IDRicevutaFiscale"),r.getString("email"),r.getString("metodoPagamento"),r.getDate("DataEmissione").toLocalDate(),r.getString("via"),r.getString("Cap"),r.getInt("NumeroCivico")));
			}
			return ricevute;
		}catch(SQLException s){
			s.printStackTrace();
		}
		return null;
	}
		
	public void doUpdate(RicevutaFiscale element){
		throw new NonSupportatoException();
	}
}
