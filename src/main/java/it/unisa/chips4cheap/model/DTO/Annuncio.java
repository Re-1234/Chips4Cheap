package it.unisa.chips4cheap.model.DTO;

import java.io.Serializable;
import java.time.LocalDate;

public class Annuncio implements Cloneable,Serializable{
	private int idAnnuncio;
	private String titolo;
	private LocalDate dataPubblicazione;
	private String text;
	
	public Annuncio() {
		idAnnuncio = 0;
		titolo = "";
		dataPubblicazione = LocalDate.now();
		text = "";
	}
	
	public Annuncio(int idAnnunncio , String titolo , LocalDate dataPubblicazione , String text){
		this.idAnnuncio = idAnnunncio;
		this.titolo = titolo;
		this.text = text;
		this.dataPubblicazione = dataPubblicazione;
	}
	
	public String getTitolo(){
		return titolo;
	}
	
	public void setTitolo(String titolo){
		this.titolo = titolo;
	}
	
	public int getIdAnnuncio() {
		return idAnnuncio;
	}
	
	public void setIdAnnuncio(int idAnnuncio){
		this.idAnnuncio = idAnnuncio;
	}
	
	public void setDataPubblicazione(LocalDate dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	
	public LocalDate getDataPublicazione(){
		return dataPubblicazione;
	}
	
	public String getText(){
		return text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	@Override
	public String toString(){
		return getClass().getName() + "[ idAnnuncio = " + idAnnuncio + ", titolo = " + titolo + ", dataPubblicazione = " + dataPubblicazione +", text = " + text + "]";
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		
		if(this == o){
			return true;
		}
		
		if(o.getClass() != getClass()){
			return false;
		}
		
		Annuncio c = (Annuncio) o;
		
		
		return c.idAnnuncio == idAnnuncio && c.titolo.equalsIgnoreCase(titolo) && c.text.equalsIgnoreCase(text) && c.dataPubblicazione.equals(dataPubblicazione);
	}
	
	@Override
	public Annuncio clone(){
		try {
			return (Annuncio) super.clone();
		}catch(CloneNotSupportedException c){
			c.printStackTrace();
		}
		return null;
	}
	
	
}
