package model.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Cloneable , Serializable{
	private String username;
	private String password;
	private String via;
	private int cap;
	private int numeroCivico;
	private String email;
	private boolean amministratore;
	private ArrayList<RicevutaFiscale> ricevutaFiscale;

	public Account(){
		username = "";
		password = "";
		via = "";
		cap = 0;
		numeroCivico = 0;
		email = "";
		amministratore = false;
		ricevutaFiscale = new ArrayList<>();
	}
	
	public Account(String username,String password , String via , int cap , int numeroCivico , String email , boolean amministratore , ArrayList<RicevutaFiscale> ricevuteFiscale){
		this.username = username;
		this.password = password;
		this.via = via;
		this.cap =  cap;
		this.numeroCivico = numeroCivico;
		this.email = email;
		this.amministratore = amministratore;
		for(RicevutaFiscale ricevutaFiscale : ricevuteFiscale){
			this.ricevutaFiscale.add(ricevutaFiscale);
		}
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getVia() {
		return via;
	}


	public void setVia(String via) {
		this.via = via;
	}


	public int getCap() {
		return cap;
	}


	public void setCap(int cap) {
		this.cap = cap;
	}


	public int getNumeroCivico() {
		return numeroCivico;
	}


	public void setNumeroCivico(int numeroCivico) {
		this.numeroCivico = numeroCivico;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isAmministratore() {
		return amministratore;
	}


	public void setAmministratore(boolean amministratore) {
		this.amministratore = amministratore;
	}
	
	public void setRicevuteFiscali(ArrayList<RicevutaFiscale> ricevutaFiscale){
		for(RicevutaFiscale ricevuteFiscale: ricevutaFiscale){
			this.ricevutaFiscale.add(ricevuteFiscale);
		}
	}
	
	public ArrayList<RicevutaFiscale> getRicevuteFiscali(){
		ArrayList<RicevutaFiscale> ricev = new ArrayList<>();
		for(RicevutaFiscale ricevutaFiscale: this.ricevutaFiscale){
			ricev.add(ricevutaFiscale);
		}
		return ricev;
	}
	
	 @Override
	public Account clone(){
		try {
			return (Account)super.clone();
		}catch(CloneNotSupportedException c){
			return null;
		}
	}
	 
	 @Override
	 public boolean equals(Object o){
		 if(o == null){
			 return false;
		 }
		 
		 if(this == o) {
			 return true;
		 }
		 
		 if(o.getClass() != this.getClass()){
			 return false;
		 }
		 
		 Account account = (Account) o;
		 
		 boolean allTrue = true;
		 
		 if(ricevutaFiscale.size() == account.ricevutaFiscale.size()){
			 for(RicevutaFiscale rF : account.ricevutaFiscale){
				 if(!ricevutaFiscale.contains(rF)){
					 allTrue = false;
				 }
			 }
		 }else{
			 allTrue = false;
		 }
		 
		 return username.equalsIgnoreCase(account.username) && password.equalsIgnoreCase(account.password) && via.equalsIgnoreCase(account.via) && 
	     this.cap == account.cap && this.numeroCivico == account.numeroCivico && this.email.equalsIgnoreCase(account.email) && this.amministratore == account.amministratore && allTrue; 
	 }
	 
	 public String toString() {
		 return this.getClass().getName() + "[ username = " + username + ", password = " + password + ", via = " + via + ",cap = " + cap + ", numeroCivico =" + numeroCivico + ", email = " + email + ",amministratore = " + amministratore + ", ricevuteFiscali = " + ricevutaFiscale + "]";
	 }
}
