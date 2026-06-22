package model.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public final class RicevutaFiscale implements Serializable{
	private int idRicevutaFiscale; 
	private Account account;
	private String metodoPagamento;
	private ArrayList<ProdottoRicevuta> prodottiRicevuta;
	
	public RicevutaFiscale(){
		idRicevutaFiscale = 0;
		prodottiRicevuta = new ArrayList<>();
		metodoPagamento = "";
		prodottiRicevuta = new ArrayList<>();
	}
	
	public RicevutaFiscale(int idRicevutaFiscale,ArrayList<ProdottoRicevuta> prodottiRicevuta,Account account,String metodoPagamento){
		this.idRicevutaFiscale = idRicevutaFiscale;
		for(ProdottoRicevuta prodottoRicevuta : prodottiRicevuta) {
		     this.prodottiRicevuta.add(prodottoRicevuta);	
		}
		this.metodoPagamento = metodoPagamento;
		this.account = account.clone();
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

	public String getMetodoPagamento() {
		return metodoPagamento;
	}
	
	public Account getAccount() {
		return account.clone();
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
		
		
		return ricevutaFiscale.idRicevutaFiscale == idRicevutaFiscale && allTrue && metodoPagamento.equalsIgnoreCase(ricevutaFiscale.metodoPagamento) && account.equals(ricevutaFiscale.getAccount());
	}
	
	@Override
	public String toString(){
		return getClass().getName() + "[ idRicevutaFiscale = " + idRicevutaFiscale + ", prodottiRicevuta = " + prodottiRicevuta.toString() +", metodoPagamento = " + metodoPagamento + ", account = " + account +"]";
	}

	
}
