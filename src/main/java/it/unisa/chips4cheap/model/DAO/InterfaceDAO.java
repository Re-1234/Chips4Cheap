package it.unisa.chips4cheap.model.DAO;


public interface InterfaceDAO<T>{
	int doSave(T elemet);
	void doDelete(T element);
	T doSearchElement(Object o);
	void doUpdate(T element);
}