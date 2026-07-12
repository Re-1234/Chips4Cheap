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
			PreparedStatement pre = c.prepareStatement("Insert into Prodotto(Prezzo , Produttore , NomeModello , Descrizione , Sconto , Quantità , image , Tipo , MimeType ) values(?,?,?,?,?,?,?,?,?)");
			pre.setDouble(1, elemet.getPrezzo());
			pre.setString(2, elemet.getnCAutore());
			pre.setString(3, elemet.getNomeModello());
			pre.setString(4, elemet.getDescrizione());
			pre.setInt(5,elemet.getSconto());
			pre.setInt(6,elemet.getQuantità());
			pre.setString(7, elemet.getImagine());
			pre.setString(8,elemet.getTipo());
			pre.setString(9, elemet.getMimeType());
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
				Prodotto p1 = new Prodotto(result.getString("Produttore"),result.getString("NomeModello"),result.getDouble("Prezzo"),result.getString("Descrizione"),result.getString("Tipo"),result.getInt("Quantità"),result.getInt("Sconto"),result.getString("Image"),result.getString("MimeType"));
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
			PreparedStatement preparedStatement = con.prepareStatement("Update Prodotto Set NomeModello = ? , Produttore = ? , Prezzo = ? , Descrizione = ? , Sconto = ? , Tipo = ? , Quantità = ? , Image = ?,MimeType = ?");
			preparedStatement.setString(1,element.getNomeModello());
			preparedStatement.setString(2, element.getnCAutore());
			preparedStatement.setDouble(3, element.getPrezzo());
			preparedStatement.setString(4,element.getDescrizione());
			preparedStatement.setInt(5, element.getSconto());
			preparedStatement.setString(6, element.getTipo());
			preparedStatement.setInt(7,element.getQuantità());
			preparedStatement.setString(8,element.getImagine());
			preparedStatement.setString(9,element.getMimeType());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		}catch(SQLException c){
			c.printStackTrace();
		}
	}

	public ArrayList<Prodotto> doFilter(String nomeModello,String produttore,String tipo,int prezzoMin ,int prezzoMax){
		
		String s = "Select * From Prodotto";
		boolean [] bArray  = new boolean [3];
		
		bArray[0] = nomeModello != null && !nomeModello.equals("");
		bArray[1] = produttore != null && !produttore.equals("");
		bArray[2] = tipo != null && !tipo.equals("");
		int elemet = 0;
		
		if(bArray[0]){
			elemet += 1;
		}
		
		if(bArray[1]){
			elemet += 1;
		}
			
		if(bArray[2]){
			elemet += 1;
		}
		
		if(elemet != 0){
			s += "where ";
			for(int i = 0;i < 3;i++) {
				if(bArray[i] && i == 0){
					if(elemet != 1){
						--elemet;
						s += "NomeModello = ? and";
					}else{
						s+= "NomeModello = ?";
					}
				}
				if(bArray[i] && i == 1){
					if(elemet != 1){
						s += "Produttore = ? and";
					}else {
						s += "Produttore = ?";
					}
				}
				if(bArray[i] && i == 2) {
					if(elemet != 1){
						s += "Tipo = ? and";
					}else{
						s += "Tipo = ?";
					}
				}
			}
			if(prezzoMin <= prezzoMax){
				s+= "Prezzo >= ? and Prezzo <= ?";
			}else {
				throw new RuntimeException("Il Prezzo di minimo è maggiore del Prezzo di massimo");
			}	
		}else{
			if(prezzoMin <= prezzoMax){
				s+= "Prezzo >= ? and Prezzo <= ?";
			}else {
				throw new RuntimeException("Il Prezzo di minimo è maggiore del Prezzo di massimo");
			}
		}
		
		
		int j = 1;
		try(Connection c = ds.getConnection()){
			ArrayList<Prodotto> w = new ArrayList<>();
			PreparedStatement p = c.prepareStatement(s);
			if(bArray[0]){
				p.setString(j, nomeModello);
				j++;
			}
			
			if(bArray[1]){
				p.setString(j,produttore);
				j++;
			}
			
			if(bArray[2]){
				p.setString(j,tipo);
				j++;
			}
			
			p.setInt(j, prezzoMin);
			j++;
			p.setInt(j, prezzoMax);
			ResultSet r = p.executeQuery();
			while(r.next()){
				w.add(new Prodotto(r.getString("Produttore"),r.getString("NomeModello"),r.getDouble("Prezzo"),r.getString("Descrizione"),r.getString("Tipo"),r.getInt("Quantità"),r.getInt("Sconto"),r.getString("Image"),r.getString("MimeType")));
			}
			return w;
		}catch(SQLException r){
			r.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ArrayList<Prodotto> doRetrieveByAll() {
		try(Connection c = ds.getConnection()){
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			PreparedStatement p = c.prepareStatement("Select * From Prodotto");
			ResultSet r = p.executeQuery();
			while(r.next()) {
				prodotti.add(new Prodotto(r.getString("Produttore"),r.getString("NomeModello"),r.getDouble("Prezzo"),r.getString("Descrizione"),r.getString("Tipo"),r.getInt("Quantità"),r.getInt("Sconto"),r.getString("Image"),r.getString("MimeType")));
			}
			return prodotti;
		}catch(SQLException c){
			c.printStackTrace();
		}
		return null;
	}

}
