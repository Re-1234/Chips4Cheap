package it.unisa.chips4cheap.model.DTO;

import java.io.Serializable;

public class Prodotto implements Cloneable , Serializable{
	private String nCAutore;
	private String nomeModello;
	private double prezzo;
	private String descrizione;
	private String tipo;
	private int quantità;
	private int sconto;
	private String imagine;
	private String mimeType;
	
	public final static double EPSILON = 1e-9; 
	 
	
	public Prodotto(){
		this.nCAutore = "";
		this.nomeModello = "";
		this.prezzo = 0;
		this.descrizione = "";
		this.tipo = "";
		this.quantità = 0;
		this.sconto = 0;
		this.imagine = "";
		this.mimeType = "";
	}
	
	public Prodotto(String nCAutore , String nomeModello , double prezzo , String descrizione , String tipo , int quantità ,int sconto, String imagine,String mimeType){
		this.nCAutore = nCAutore;
		this.nomeModello = nomeModello;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.quantità = quantità;
		this.imagine = imagine;
		if(sconto >= 0 && sconto <= 100){
			this.sconto = sconto;
		}else {
			this.sconto = 0;
		}
		this.mimeType = mimeType;
	}
	
	public String getnCAutore() {
		return nCAutore;
	}
	public void setnCAutore(String nCAutore) {
		this.nCAutore = nCAutore;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNomeModello() {
		return nomeModello;
	}
	public void setNomeModello(String nomeModello) {
		this.nomeModello = nomeModello;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	public String getImagine() {
		return imagine;
	}
	public void setImagine(String imagine) {
		this.imagine = imagine;
	}
	
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public int getSconto() {
		return sconto;
	}

	public void setSconto(int sconto) {
		if(sconto >= 0 && sconto <= 100){
			this.sconto = sconto;
		}else{
			throw new RuntimeException("lo sconto deve stare in questo range di valori 0 e 100");
		}
	}
	
	public double getPrezzoScontato(){
        if(sconto == 0){
            return prezzo;
        }
        double result = -1;

        if(sconto > 0) {
             result = (prezzo * (100 - sconto))/ 100;
        }else {
            throw new RuntimeException("Lo Sconto è negativo");
        }

        return result;
    } 

	
	public double getSubtotale() {
		return getPrezzoScontato() * this.quantità;
	}
	
	@Override
	public Prodotto clone(){
		try {
			return (Prodotto) super.clone();
		}catch(CloneNotSupportedException c){
			return null;
		}
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
		
		Prodotto prodotto = (Prodotto) o;
		
		return prodotto.nCAutore.equalsIgnoreCase(nCAutore) && prodotto.nomeModello.equalsIgnoreCase(nomeModello) && Math.abs(prodotto.prezzo - prezzo) <= EPSILON && 
				descrizione.equalsIgnoreCase(prodotto.descrizione) && tipo.equalsIgnoreCase(prodotto.tipo) && quantità == prodotto.quantità && imagine.equalsIgnoreCase(prodotto.imagine);
				
	}
	
	@Override
	public String toString(){
		return getClass().getName() + "[ nCAutore = " + nCAutore + ", nomeModello = " + nomeModello + ", prezzo =" + prezzo + ", descrizione = " + descrizione +", tipo = " + tipo + ",quantità = " + quantità + "imagine = " + imagine + "]";
	}
}
