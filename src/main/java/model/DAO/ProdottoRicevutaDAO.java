package model.DAO;

import model.DTO.ProdottoRicevuta;

public class ProdottoRicevutaDAO implements InterfaceDAO<ProdottoRicevuta>{

	@Override
	public void doSave(ProdottoRicevuta elemet) {
		if(elemet == null) {
			throw new NullPointerException();
		}
		
	}

	@Override
	public void doDelete(ProdottoRicevuta element) {
		throw new NonSupportatoException();
	}

	@Override
	public ProdottoRicevuta doSearchElement(Object o) {
		if(o == null) {
			throw new NullPointerException();
		}
		return null;
	}

	@Override
	public void doUpdate(ProdottoRicevuta element) {
		throw new NonSupportatoException();
	}

}
