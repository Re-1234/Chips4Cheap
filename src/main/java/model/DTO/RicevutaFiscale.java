package model.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public final class RicevutaFiscale implements Serializable{
	private int idRicevutaFiscale;
	private String email;
	private String metodoPagamento;
	private LocalDate localDate;
	private ArrayList<ProdottoRicevuta> prodottiRicevuta;
	
	public RicevutaFiscale(){
		idRicevutaFiscale = 0;
		prodottiRicevuta = new ArrayList<>();
		metodoPagamento = "";
		localDate = LocalDate.now();
	}
	
	public RicevutaFiscale(int idRicevutaFiscale,ArrayList<ProdottoRicevuta> prodottiRicevuta,String metodoPagamento,LocalDate localDate){
		this.idRicevutaFiscale = idRicevutaFiscale;
		this.prodottiRicevuta = new ArrayList<>();
		for(ProdottoRicevuta prodottoRicevuta : prodottiRicevuta) {
		     this.prodottiRicevuta.add(prodottoRicevuta);	
		}
		this.metodoPagamento = metodoPagamento;
		this.localDate = localDate;
	}
	
	public double totaleRicevuta(){
		double tot = 0;
		for(ProdottoRicevuta prodottoRicevuta : prodottiRicevuta) {
			tot += prodottoRicevuta.getPrezzo(); 
		}
		
		return tot;
	}
	
	public int getIdRicevutaFiscale() {
		return idRicevutaFiscale;
	}
	
	public ArrayList<ProdottoRicevuta> getprodottiRicevuta(){
		ArrayList<ProdottoRicevuta> prodottiRicevuta = new ArrayList<>();
		for(ProdottoRicevuta prodottoRicevuta : this.prodottiRicevuta) {
			prodottiRicevuta.add(prodottoRicevuta);
		}
		return prodottiRicevuta;
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
		
		if(this.prodottiRicevuta.size() == ricevutaFiscale.getIdRicevutaFiscale()){
			for(ProdottoRicevuta prodottiRicevuta : ricevutaFiscale.getprodottiRicevuta()){
				if(!this.prodottiRicevuta.contains(prodottiRicevuta)){
					allTrue = false;
					break;
				}
			}
		}else{
			allTrue = false;
		}
		
		
		return ricevutaFiscale.idRicevutaFiscale == idRicevutaFiscale && allTrue && metodoPagamento.equalsIgnoreCase(ricevutaFiscale.metodoPagamento);
	}
	
	@Override
	public String toString(){
		return getClass().getName() + "[ idRicevutaFiscale = " + idRicevutaFiscale + ", prodottiRicevuta = " + prodottiRicevuta.toString() +", metodoPagamento = " + metodoPagamento +  ",email = " + email + ",localDate = " + localDate + "]";
	}

	
}
