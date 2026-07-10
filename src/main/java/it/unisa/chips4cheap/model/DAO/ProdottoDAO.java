package it.unisa.chips4cheap.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.chips4cheap.model.DTO.*;

public class ProdottoDAO implements InterfaceDAO<Prodotto>{
	
	private DataSource ds;
	
	public ProdottoDAO(DataSource ds){
		this.ds = ds;
	}
	
	@Override
	public int doSave(Prodotto elemet) {
		if(elemet == null) {
			throw new NullPointerException();
		}
		try{
			Connection c = ds.getConnection();
			PreparedStatement pre = c.prepareStatement("Insert into Prodotto(Prezzo ,Produttore , NomeModello , Descrizione , Quantità , image , tipo) values(?,?,?,?,?,?,?)");
			pre.setDouble(1, elemet.getPrezzo());
			pre.setString(2, elemet.getnCAutore());
			pre.setString(3, elemet.getNomeModello());
			pre.setString(4, elemet.getDescrizione());
			pre.setInt(5,elemet.getQuantità());
			pre.setString(6, elemet.getImagine());
			pre.setString(7,elemet.getTipo());
			int y = pre.executeUpdate();
			pre.close();
			c.close();
			return y;
		}catch(SQLException c){
			c.printStackTrace();
		}	
		return -1;
	}

	
	@Override
	public void doDelete(Prodotto element) {
		if(element == null) {
			throw new NullPointerException();
		}
		
		try{
			Connection co = (Connection) ds.getConnection();
			PreparedStatement pr = co.prepareStatement("Delete From Prodotto where NomeModello = ?");
			pr.setString(1, element.getNomeModello());
			pr.executeUpdate();
			pr.close();
			co.close();
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
		try {
			Connection con = ds.getConnection();
			PreparedStatement p = con.prepareStatement("Select * From Prodotto where NomeModello = ?");
			p.setString(1,nomeModello);
			ResultSet result = p.executeQuery();
			if(result.next()){
				Prodotto p1 = new Prodotto(result.getString("Produttore"),result.getString("NomeModello"),result.getDouble("Prezzo"),result.getString("Descrizione"),result.getString("Tipo"),result.getInt("Quantità"),result.getInt("Sconto"),result.getString("Image"));
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
		}
		return null;
	}

	@Override
	public void doUpdate(Prodotto element) {
		if(element == null) {
			throw new NullPointerException();
		}
		try {
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
		}
	}

	@Override
	public ArrayList<Prodotto> doRetryByAll() {
		try(Connection c = ds.getConnection()){
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			PreparedStatement p = c.prepareStatement("Select * From Prodotto");
			ResultSet r = p.executeQuery();
			while(r.next()) {
				prodotti.add(new Prodotto(r.getString("Produttore"),r.getString("NomeModello"),r.getDouble("Prezzo"),r.getString("Descrizione"),r.getString("Tipo"),r.getInt(""),r.getInt(0),r.getString(0)));
			}
			return prodotti;
		}catch(SQLException c){
			c.printStackTrace();
		}
		return null;
	}

}
