package model.DTO;

import java.io.Serializable;

public final class ProdottoRicevuta implements Serializable{
	private String nCAutore;
	private String nomeModello;
	private double prezzo;
	private String descrizione;
	private String tipo;
	private int quantità;
	private String imagine;
	
	public final static double EPSILON = 1e9;
	public String getnCAutore() {
		return nCAutore;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public String getNomeModello() {
		return nomeModello;
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
				descrizione.equalsIgnoreCase(prodotto.descrizione) && tipo.equalsIgnoreCase(prodotto.tipo) && quantità == prodotto.quantità && imagine.equalsIgnoreCase(prodotto.imagine);
				
	}
	
	@Override
	public String toString(){
		return getClass().getName() + "[ nCAutore = " + nCAutore + ", nomeModello = " + nomeModello + ", prezzo =" + prezzo + ", descrizione = " + ", tipo = " + tipo + ",quantità = " + quantità + "imagine = " + imagine + "]";
	}
}
