package it.unisa.chips4cheap.model.DTO;

import java.io.Serializable;

public final class ProdottoRicevuta implements Serializable{
	private String nCAutore;
	private String nomeModello;
	private double prezzo;
	private String email;
	private String tipo;
	private int quantità;
	private String imagine;
	private int iDRicevutaFiscale;
	
	public final static double EPSILON = 1e9;
	
	public ProdottoRicevuta(){
		nCAutore = "";
		nomeModello = "";
		prezzo = 0;
		tipo = "";
		quantità = 0;
		imagine = "";
		email = "";
		iDRicevutaFiscale = 0;
	}
	
	public ProdottoRicevuta(String nCAutore,int iDRicevutaFiscale,String email,String nomeModello ,double prezzo , String tipo , int quantità , String image) {
		this.nCAutore = nCAutore;
		this.nomeModello = nomeModello;
		this.prezzo = prezzo;
		this.tipo = tipo;
		this.email = email;
		this.quantità = quantità;
		this.imagine = image; 
		this.iDRicevutaFiscale = iDRicevutaFiscale;
	}
	
	
	
	public String getnCAutore() {
		return nCAutore;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public String getNomeModello() {
		return nomeModello;
	}
	
	public String getEmail() {
		return email;
	}

	public String getTipo() {
		return tipo;
	}
	
	public int getQuantità() {
		return quantità;
	}
	
	public String getImagine() {
		return imagine;
	}
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		
		if(this == o) {
			return true;
		}
		
		if(o.getClass() != this.getClass()) {
			return false;
		}
		
		ProdottoRicevuta prodotto = (ProdottoRicevuta) o;
		
		return prodotto.nCAutore.equalsIgnoreCase(nCAutore) && prodotto.nomeModello.equalsIgnoreCase(nomeModello) && Math.abs(prodotto.prezzo - prezzo) <= EPSILON && 
				 tipo.equalsIgnoreCase(prodotto.tipo) && quantità == prodotto.quantità && imagine.equalsIgnoreCase(prodotto.imagine);
				
	}
	
	@Override
	public String toString(){
		return getClass().getName() + "[ nCAutore = " + nCAutore + ", nomeModello = " + nomeModello + ", prezzo =" + prezzo  + ", tipo = " + tipo + ",quantità = " + quantità + "imagine = " + imagine + ",IDRicevutaFiscale = "+iDRicevutaFiscale+ "]";
	}

	public int getIDRicevutaFiscale() {
		return iDRicevutaFiscale;
	}

}
