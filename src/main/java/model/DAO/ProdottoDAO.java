package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.jdbc.pool.DataSource;

import model.DTO.Prodotto;

public class ProdottoDAO implements InterfaceDAO<Prodotto>{

	@Override
	public void doSave(Prodotto elemet) {
		if(elemet == null) {
			throw new NullPointerException();
		}
		Context init;
		try{
			init = new InitialContext();
			Context ext = (Context) init.lookup("java:comp/env");
			BasicDataSource ds = (BasicDataSource) ext.lookup("jdbc/chips4cheap");
			Connection c = ds.getConnection();
			PreparedStatement pre = c.prepareStatement("Insert into Prodotto(Prezzo  , NomeModello , Descrizione , Quantità , image , tipo) values(?,?,?,?,?,?)");
			pre.setDouble(1, elemet.getPrezzo());
			pre.setString(2, elemet.getNomeModello());
			pre.setString(3, elemet.getDescrizione());
			pre.setInt(4,elemet.getQuantità());
			pre.setString(5, elemet.getImagine());
			pre.setString(6,elemet.getTipo());
			pre.executeUpdate();
			pre.close();
			c.close();
		}catch(NamingException c) {
			c.printStackTrace();
		}catch(SQLException c){
			c.printStackTrace();
		}	
	}

	
	@Override
	public void doDelete(Prodotto element) {
		if(element == null) {
			throw new NullPointerException();
		}
		
		Context init;
		try{
			init = new InitialContext();
			Context ext = (Context) init.lookup("java:comp/env");
			BasicDataSource ds = (BasicDataSource) ext.lookup("jdbc/chips4cheap");
			Connection co = (Connection) ds.getConnection();
			PreparedStatement pr = co.prepareStatement("Delete From Prodotto where NomeModello = ?");
			pr.setString(1, element.getNomeModello());
			pr.executeUpdate();
			pr.close();
			co.close();
		}catch(NamingException n){
			n.printStackTrace();
		}catch(SQLException s){
			s.printStackTrace();
		}
	}

	@Override
	public Prodotto doSearchElement(Object o) {
		if(o == null) {
			throw new NullPointerException();
		}
		if(!(o instanceof String)){
			throw new RuntimeException();
		}
		String nomeModello = (String) o;
		Context init;
		try {
			init = new InitialContext();
			Context exl = (Context) init.lookup("java:comp/env");
			BasicDataSource ds = (BasicDataSource) exl.lookup("jdbc/chips4cheap");
			Connection con = ds.getConnection();
			PreparedStatement p = con.prepareStatement("Select * From Prodotto where NomeModello = ?");
			p.setString(1,nomeModello);
			ResultSet result = p.executeQuery();
			if(result.next()){
				Prodotto p1 = new Prodotto(result.getString("Produttore"),result.getString("NomeModello"),result.getDouble("Prezzo"),result.getString("Descrizione"),result.getString("Tipo"),result.getInt("Quantità"),result.getString("Image"));
				result.close();
				p.close();
				con.close();
				return p1;
			}
			result.close();
			p.close();
			con.close();
			return null;
		}catch(SQLException c){
			c.printStackTrace();
		}catch(NamingException n){
			n.printStackTrace();
		}
		return null;
	}

	@Override
	public void doUpdate(Prodotto element) {
		if(element == null) {
			throw new NullPointerException();
		}
		Context init;
		try {
			init = new InitialContext();
			Context exl = (Context) init.lookup("java:comp/env");
			BasicDataSource ds = (BasicDataSource) exl.lookup("jdbc/chips4cheap");
			Connection con = ds.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("Update Prodotto Set NomeModello = ? , Produttore = ? , Prezzo = ? , Descrizione = ? , Tipo = ? , Quantità = ? , Image = ?");
			preparedStatement.setString(1,element.getNomeModello());
			preparedStatement.setString(2, element.getnCAutore());
			preparedStatement.setDouble(3, element.getPrezzo());
			preparedStatement.setString(4,element.getDescrizione());
			preparedStatement.setString(5, element.getTipo());
			preparedStatement.setInt(6,element.getQuantità());
			preparedStatement.setString(7,element.getImagine());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		}catch(SQLException c){
			c.printStackTrace();
		}catch(NamingException n){
			n.printStackTrace();
		}
	}

}
