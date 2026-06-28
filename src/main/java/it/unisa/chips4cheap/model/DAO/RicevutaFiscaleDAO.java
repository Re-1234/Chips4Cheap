package it.unisa.chips4cheap.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DTO.*;

public class RicevutaFiscaleDAO implements InterfaceDAO<RicevutaFiscale>{
	
	private javax.sql.DataSource ds; 
	
	public RicevutaFiscaleDAO(DataSource ds){
		this.ds = ds;
	}
	
	@Override
	public void doSave(RicevutaFiscale elemet){
			try {
				Connection connection = ds.getConnection();
				PreparedStatement pre = connection.prepareStatement("Insert into RicevutaFiscale(email,metodoPagamento,DataEmissione) Values (?,?,?)");
				pre.setString(1,elemet.getEmail());
				pre.setString(2,elemet.getMetodoPagamento());
				pre.setDate(3,Date.valueOf(elemet.getLocalDate()));
				pre.executeUpdate();
				ProdottoRicevutaDAO pro = new ProdottoRicevutaDAO(ds);
					
				for(ProdottoRicevuta prodottoRicevuta : elemet.getprodottiRicevuta()){
					pro.doSave(prodottoRicevuta);
				}
				
				pre.close();
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
	}
	
	@Override
	public void doDelete(RicevutaFiscale element){
		throw new NonSupportatoException();
	}
	
	public RicevutaFiscale doSearchElement(Object o){
		if(!(o instanceof Integer) || o == null) {
			throw new RuntimeException("Mandato un oggetto che non è stringa");
		}
		
		
		Integer intero = (Integer) o;
			try(Connection connection = ds.getConnection()){
				PreparedStatement preparedStatement = connection.prepareStatement("Select * From RicevutaFiscale Where IdRicevutaFiscale = ?");
				preparedStatement.setInt(1,intero);
				ResultSet resultSet = preparedStatement.executeQuery();
				ArrayList<ProdottoRicevuta> prodottiRicevuta = new ArrayList<>();
				if(resultSet.next()){
					try(PreparedStatement per = connection.prepareStatement("Select * From ProdottoRicevuta where IDRicevutaFiscale = ?")){
						per.setInt(1 , resultSet.getInt("IDRicevutaFiscale"));
						ResultSet result = per.executeQuery();
						while(result.next()){
							prodottiRicevuta.add(new ProdottoRicevuta(
									result.getString("Produttore"),
									result.getInt("IDRicevutaFiscale"),
									result.getString("email"),								
									result.getString("Nomemodello"),
									result.getDouble("Prezzo"),
									result.getString("tipo"),
									result.getInt("Quantità"),
									result.getString("image")
									));	
						}
						
						result.close();
						RicevutaFiscale ricevuta1 = new RicevutaFiscale(resultSet.getInt("IDRicevutaFiscale"),resultSet.getString("email"),prodottiRicevuta,resultSet.getString("metodoPagamento"),resultSet.getDate("DataEmissione").toLocalDate());
						resultSet.close();
						return ricevuta1;
					}
				}else{
					resultSet.close();
					return null;
				}
				
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		
		return new RicevutaFiscale();
	}
		
	public void doUpdate(RicevutaFiscale element){
		throw new NonSupportatoException();
	}
}
