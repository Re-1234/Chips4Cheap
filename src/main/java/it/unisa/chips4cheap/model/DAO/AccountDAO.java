package it.unisa.chips4cheap.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.jdbc.pool.DataSource;

import it.unisa.chips4cheap.model.DTO.*;

public class AccountDAO implements InterfaceDAO<Account>{

	@Override
	public void doSave(Account elemet){
		if(elemet == null) {
			throw new NullPointerException();
		}
		Context initCtx;
		try{
			initCtx = new InitialContext();
			Context  envCtx = (Context)initCtx.lookup("java:comp/env");
			BasicDataSource	ds = (BasicDataSource)envCtx.lookup("jdbc/chips4cheap");
			try(Connection co = ds.getConnection()){
				PreparedStatement preparedStatement = co.prepareStatement("Insert into Account1(email,username,password1,Via,Cap,NumeroCivico,Amministratore) values (?,?,?,?,?,?,?)");
				preparedStatement.setString(1,elemet.getEmail());
				preparedStatement.setString(2,elemet.getUsername());
				preparedStatement.setString(3, elemet.getPassword());
				preparedStatement.setString(4,elemet.getVia());
				preparedStatement.setString(5,elemet.getCap());
				preparedStatement.setInt(6,elemet.getNumeroCivico());
				preparedStatement.setBoolean(7,elemet.isAmministratore());
				preparedStatement.executeUpdate();
			
				
				RicevutaFiscaleDAO ricevutaFiscaleDAO = new RicevutaFiscaleDAO();
				
				for(RicevutaFiscale ricevutaFiscale:  elemet.getRicevuteFiscali()){
					ricevutaFiscaleDAO.doSave(ricevutaFiscale);
				}
				preparedStatement.close();
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch(SQLException sqlExe){
			sqlExe.printStackTrace();
		}
		
	}

	@Override
	public void doDelete(Account element){
		if(element == null) {
			throw new NullPointerException();
		}
		
		Context initCtx;
		try{
			initCtx = new InitialContext();
			Context  envCtx = (Context)initCtx.lookup("java:comp/env");
			BasicDataSource ds = (BasicDataSource)envCtx.lookup("jdbc/chips4cheap");
			Connection c = ds.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement("Delete From Account1 where email = ?");
			preparedStatement.setString(1,element.getEmail());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			c.close();
		}catch(NamingException c){
			c.printStackTrace();
		}catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	@Override
	public Account doSearchElement(Object o){
		if(!(o instanceof String) || o == null){
			throw new RuntimeException();
		}
		String email = (String) o;
		Context initCtx;
		try{
			initCtx = new InitialContext();
			Context  envCtx = (Context)initCtx.lookup("java:comp/env");
		    BasicDataSource	ds = (BasicDataSource)envCtx.lookup("jdbc/chips4cheap");
			try(Connection co = ds.getConnection()){
				PreparedStatement preparedStatement = co.prepareStatement("Select * From Account1 where email = ?");
				preparedStatement.setString(1,email);
				ResultSet resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()){
					Account account = new Account(resultSet.getString("username"),resultSet.getString("Password1"),resultSet.getString("Via"),resultSet.getString("Cap"),resultSet.getInt("NumeroCivico"),resultSet.getString("email"),resultSet.getBoolean("Amministratore"),null);
					ArrayList<RicevutaFiscale> ricevuteFiscali = new ArrayList<>();
					PreparedStatement p = co.prepareStatement("Select * From RicevutaFiscale where email = ?");
					p.setString(1, account.getEmail());
					ResultSet r =p.executeQuery();
					RicevutaFiscaleDAO r1 = new RicevutaFiscaleDAO();	
					while(r.next()){
						ricevuteFiscali.add(
									r1.doSearchElement(r.getInt("IDRicevutaFiscale"))
								);
					}
							
					account.setRicevuteFiscali(ricevuteFiscali);
					resultSet.close();
					preparedStatement.close();
					return account;
				}
				resultSet.close();
				preparedStatement.close();
			}
			 return null;
		}catch(NamingException namingExcep){
			namingExcep.printStackTrace();
		}catch(SQLException sqlExecption){
			sqlExecption.printStackTrace();
		}
		return null;
	}

	@Override
	public void doUpdate(Account element) {
		if(element == null) {
			throw new NullPointerException();
		}
		Context initCtx;
		try{
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			BasicDataSource	ds = (BasicDataSource)envCtx.lookup("jdbc/chips4cheap");
				try(Connection conn = ds.getConnection()){
				PreparedStatement pre = conn.prepareStatement("Update Account1 Set username = ? , Password1 = ? , Via = ? , Cap = ? , NumeroCivico = ? , Amministratore = ? where email = ?");
				pre.setString(1,element.getUsername());
				pre.setString(2,element.getPassword());
				pre.setString(3,element.getVia());
				pre.setString(4, element.getCap());
				pre.setInt(5, element.getNumeroCivico());
				pre.setBoolean(6,element.isAmministratore());
				pre.setString(7,element.getEmail());
				
				pre.executeUpdate();
				pre.close();
				conn.close();
			}
		}catch (SQLException e){
				e.printStackTrace();
		}catch (NamingException e) {
			    e.printStackTrace();
		}
		
		
	}
	
}
