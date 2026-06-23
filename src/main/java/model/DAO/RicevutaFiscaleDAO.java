package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.jdbc.pool.DataSource;

import model.DTO.Account;
import model.DTO.ProdottoRicevuta;
import model.DTO.RicevutaFiscale;

public class RicevutaFiscaleDAO implements InterfaceDAO<RicevutaFiscale>{
	@Override
	public void doSave(RicevutaFiscale elemet){
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/chips4cheap");
			
			try{
				Connection connection = ds.getConnection();
				PreparedStatement pre = connection.prepareStatement("Insert into RicevutaFiscale(email,metodoPagamento,DataEmissione) Values (?,?,?)");
				pre.setString(1,elemet.getEmail());
				pre.setString(2,elemet.getMetodoPagamento());
				pre.setDate(3,Date.valueOf(elemet.getLocalDate()));
				pre.executeUpdate();
				
				pre.close();
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}catch(NamingException n){
			n.printStackTrace();
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
		
		DataSource datasource = new DataSource();
		Integer intero = (Integer) o;
		
		try(Connection connection = datasource.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement("Select * From RicevutaFiscale Where IdRicevutaFiscale = ?");
			preparedStatement.setInt(1,intero);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<ProdottoRicevuta> prodottiRicevuta = new ArrayList<>();
			
			try(PreparedStatement per = connection.prepareStatement("Select * From ProdottoRicevuta where IDRicevutaFiscale = ?")){
				per.setInt(1 , resultSet.getInt("IDRicevutaFiscale"));
				ResultSet result = per.executeQuery();
				while(result.next()){
					prodottiRicevuta.add(new ProdottoRicevuta(
							result.getString("nCAutore"),
							result.getString("Nomemodello"),
							result.getDouble("Prezzo"),
							result.getString("tipo"),
							result.getInt("Quantità"),
							result.getString("image")
							));	
				}
				
				result.close();
			}
			Account account = new Account();
			try(PreparedStatement preparedStatemen = connection.prepareStatement("Select * From Account1 where email = ?")){
				preparedStatemen.setString(1,resultSet.getString("email"));;
				ResultSet resultS = preparedStatement.executeQuery();
				if(resultS.next()){
					account.setAmministratore(resultS.getBoolean("Amministratore"));
					account.setCap(resultS.getInt("Cap"));
					account.setVia(resultS.getString("Via"));
					account.setUsername(resultS.getString("username"));
					account.setPassword(resultS.getString("Password1"));
					account.setVia(resultS.getString("Via"));
					account.setEmail(resultS.getString("email"));
					account.setNumeroCivico(resultS.getInt("NumeroCivico"));
				}
			}
			
			RicevutaFiscale ricevuta = new RicevutaFiscale(resultSet.getInt("IDRicevutaFiscale"),prodottiRicevuta,account,resultSet.getString("metodoPagamento"),resultSet.getDate("DataEmissione").toLocalDate());
			ArrayList<RicevutaFiscale> ricevuteFiacli = new ArrayList<>();
			ricevuteFiacli.add(ricevuta);
			account.setRicevuteFiscali(ricevuteFiacli);
			System.out.println(account);
			RicevutaFiscale ricevuta1 = new RicevutaFiscale(resultSet.getInt("IDRicevutaFiscale"),prodottiRicevuta,account,resultSet.getString("metodoPagamento"),resultSet.getDate("DataEmissione").toLocalDate());
			
			return ricevuta1;
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return new RicevutaFiscale();
	}
	
	public ArrayList<RicevutaFiscale> doSearchElements(Object o){
			return null;
	}
	
	public void doUpdate(RicevutaFiscale element){
		
	}
}
