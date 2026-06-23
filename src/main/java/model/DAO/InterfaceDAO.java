package model.DAO;

import java.util.ArrayList;

public interface InterfaceDAO<T>{
	void doSave(T elemet);
	void doDelete(T element);
	T doSearchElement(Object o);
	ArrayList<T> doSearchElements(Object o);
	void doUpdate(T element);
}
