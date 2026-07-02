package it.unisa.chips4cheap.model.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public final class RicevutaFiscale implements Serializable{
	private int idRicevutaFiscale;
	private String email;
	private String metodoPagamento;
	private LocalDate localDate;
	private int numeroCivico;
	private String via;
	private String cap;
	
	public RicevutaFiscale(){
		idRicevutaFiscale = 0;
		metodoPagamento = "";
		email = "";
		localDate = LocalDate.now();
		numeroCivico = 0;
		via = "";
		cap = "";
	}
	
	public RicevutaFiscale(int idRicevutaFiscale,String email,String metodoPagamento,LocalDate localDate,String via , String cap, int numeroCivico){
		this.idRicevutaFiscale = idRicevutaFiscale;
		this.email = email;
		this.metodoPagamento = metodoPagamento;
		this.localDate = localDate;
		this.via = via;
		this.cap = cap;
		this.numeroCivico = numeroCivico;
	}
	
	public int getIdRicevutaFiscale() {
		return idRicevutaFiscale;
	}

	public int getNumeroCivico() {
		return numeroCivico;
	}
	
	public LocalDate getLocalDate() {
		return localDate;
	}	
	
	public String getMetodoPagamento() {
		return metodoPagamento;
	}
	
	public String getEmail() {
		return email;
	}	
	
	public String getVia() {
		return via;
	}
	
	public String getCap() {
		return cap;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null) {
			return false;
		}
		
		if(this == o) {
			return true;
		}
		
		if(o.getClass() != this.getClass()){
			return false;
		}
		
		RicevutaFiscale ricevutaFiscale = (RicevutaFiscale) o;
		boolean allTrue = true;
		
		
		return ricevutaFiscale.idRicevutaFiscale == idRicevutaFiscale && allTrue && metodoPagamento.equalsIgnoreCase(ricevutaFiscale.metodoPagamento);
	}
	
	@Override
	public String toString(){
		return getClass().getName() + "[ idRicevutaFiscale = " + idRicevutaFiscale  + ", metodoPagamento = " + metodoPagamento +  ",email = " + email + ",localDate = " + localDate + ", numeroCivico = "+numeroCivico + ", via = "+via + "cap = "+cap+"]";
	}
}
