package it.unisa.chips4cheap.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.chips4cheap.controllers.RicevuteAccount;
import it.unisa.chips4cheap.model.DTO.*;

public class RicevutaFiscaleDAO implements InterfaceDAO<RicevutaFiscale>{
	
	private javax.sql.DataSource ds; 
	
	public RicevutaFiscaleDAO(DataSource ds){
		this.ds = ds;
	}
	
	@Override
	public int doSave(RicevutaFiscale elemet){
			try(Connection connection = ds.getConnection()){
				PreparedStatement pre = connection.prepareStatement("Insert into RicevutaFiscale(email,metodoPagamento,DataEmissione,NumeroCivico,via,Cap) Values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				pre.setString(1,elemet.getEmail());
				pre.setString(2,elemet.getMetodoPagamento());
				pre.setDate(3,Date.valueOf(elemet.getLocalDate()));
				pre.setInt(4,elemet.getNumeroCivico());
				pre.setString(5,elemet.getVia());
				pre.setString(6,elemet.getCap());
				pre.executeUpdate();
				ResultSet r = pre.getGeneratedKeys();
				int id = 0;
				if(r.next()){
					id = r.getInt("IDRicevutaFiscale");
				}
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
	
	
	public ArrayList<RicevutaFiscale> doFilter(String email , String metodoPagamento , LocalDate dataInizio ,LocalDate dataFine){
		
		String s = "Select * From RicevutaFiscale";
		
		boolean [] barray = new boolean[4];
			barray[0] = email != null && email.equals("");
			barray[1] = metodoPagamento != null && !metodoPagamento.equals("");
			barray[2] = dataInizio != null;
			barray[3] = dataFine != null;
				
			int elemet = 0;
			if(barray[0]){
				elemet +=1;
			}
			if(barray[1]){
				elemet +=1;
			}
			if(barray[2] && barray[3]){
				if(dataInizio.getYear() > dataFine.getYear()){
					throw new DateTimeException("l'anno della data di inizio suppera l'anno della data di fine");
				}else {
					if(dataInizio.getYear() == dataFine.getYear()){
						if(dataInizio.getMonthValue() > dataFine.getMonthValue()){
							throw new DateTimeException("il mese della data di inizio suppera quella della data di fine");
						}else {
							if(dataInizio.getMonthValue() == dataFine.getMonthValue()){
								if(dataInizio.getDayOfMonth() <= dataFine.getDayOfMonth()){
									elemet += 2;
								}else{
									throw new DateTimeException("il giorno della data di inizio suppera quella della data di fine");
								}
							}else{
								elemet += 2;
							}
						}
					}else{
						elemet += 2;
					}
				}
			}else{
				if(barray[2]){
					elemet +=1;
				}
				if(barray[3]) {
					elemet +=1;
				}
			}
		if(elemet != 0) {
			s += "where ";
			for(int i = 0;i < 4;i++){
				if(barray[i] && i == 0){
					if(elemet != 1){
						--elemet;
						s += "email = ? and";
					}else {
						s += "email = ?";
					}
				}
				if(barray[i] && i == 1){
					if(elemet != 1){
						--elemet;
						s += "metodoPagamento = ? and";
					}else {
						s += "metodoPagamento = ?";
					}
				}
				if(barray[i] && i == 2) {
					if(elemet != 1){
						--elemet;
						s += "DataEmissione >= ? and";
					}else {
						s += "DataEmissione >= ?";
					}
				}
				if(barray[i] && i == 3){
					if(elemet != 1){
						--elemet;
						s += "DataEmissione <= ? and";
					}else {
						s += "DataEmissione <= ?";
					}
				}
			}
		}
		int j = 1;
		try(Connection c = ds.getConnection()){
			ArrayList<RicevutaFiscale> ri = new ArrayList<>();
			PreparedStatement p = c.prepareStatement(s);
			if(barray[0]){
				p.setString(j,email);
				j++;
			}
			if(barray[1]){
				p.setString(j,metodoPagamento);
				j++;
			}
			if(barray[2]){
				p.setDate(j, Date.valueOf(dataInizio));
				j++;
			}
			if(barray[3]){
				p.setDate(j, Date.valueOf(dataFine));
			}
			ResultSet r = p.executeQuery();
			while(r.next()){
				ri.add(new RicevutaFiscale(r.getInt("IDRicevutaFiscale"),r.getString("email"),r.getString("metodoPagamento"),r.getDate("DataEmissione").toLocalDate(),r.getString("via"),r.getString("Cap"),r.getInt("NumeroCivico")));
			}
			return ri;
		}catch(SQLException d){
			d.printStackTrace();
		}
		
		
		return null;
	}
	
	@Override
	public ArrayList<RicevutaFiscale> doRetrieveByAll() {
		try(Connection c = ds.getConnection()){
			ArrayList<RicevutaFiscale> ricevuteFiscali = new ArrayList<>();
			PreparedStatement p = c.prepareStatement("Select * From RicevutaFiscale");
			ResultSet s = p.executeQuery();
			while(s.next()) {
				ricevuteFiscali.add(new RicevutaFiscale(s.getInt("IDRicevutaFiscale"),s.getString("email"),s.getString("metodoPagamento"),s.getDate("DataEmissione").toLocalDate(),s.getString("via"),s.getString("Cap"),s.getInt("NumeroCivico")));
			}
			return ricevuteFiscali;
		}catch(SQLException s){
			
		}
		return null;
	}
}
