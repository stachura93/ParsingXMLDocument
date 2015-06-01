package pl.stachura.projekty.dao;

import java.util.List;

import pl.stachura.projekty.model.Person;

public interface IPerson {
	 
	 public void createTable();
	 public void doDelate();
	 public void doInsertBatch(final List<Person> persons);
	 public List<Person> doSelect();
	 
}
