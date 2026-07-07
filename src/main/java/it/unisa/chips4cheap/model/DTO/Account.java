package it.unisa.chips4cheap.model.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Cloneable , Serializable{
	private String username;
	private String password;
	private String via;
	private String cap;
	private int numeroCivico;
	private String email;
	private boolean amministratore;

	public Account(){
		username = "";
		password = "";
		via = "";
		cap = "";
		numeroCivico = 0;
		email = "";
		amministratore = false;
	}
	
	public Account(String username,String password , String via , String cap , int numeroCivico , String email , boolean amministratore){
		this.username = username;
		this.password = password;
		this.via = via;
		this.cap =  cap;
		this.numeroCivico = numeroCivico;
		this.email = email;
		this.amministratore = amministratore;
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


	public String getCap() {
		return cap;
	}


	public void setCap(String cap) {
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
		 
		 return username.equalsIgnoreCase(account.username) && password.equalsIgnoreCase(account.password) && via.equalsIgnoreCase(account.via) && 
	     cap.equalsIgnoreCase(account.cap) && this.numeroCivico == account.numeroCivico && this.email.equalsIgnoreCase(account.email) && this.amministratore == account.amministratore; 
	 }

	 
	 public String toString() {
		 return this.getClass().getName() + "[ username = " + username + ", password = " + password + ", via = " + via + ",cap = " + cap + ", numeroCivico =" + numeroCivico + ", email = " + email + ",amministratore = " + amministratore + ", ricevuteFiscali = " + "]";
	 }
}
