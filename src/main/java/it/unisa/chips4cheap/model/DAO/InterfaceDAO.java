package it.unisa.chips4cheap.model.DAO;

import java.util.ArrayList;

public interface InterfaceDAO<T>{
	int doSave(T elemet);
	void doDelete(T element);
	T doSearchElement(Object o);
	void doUpdate(T element);
	ArrayList<T> doRetrieveByAll();
}