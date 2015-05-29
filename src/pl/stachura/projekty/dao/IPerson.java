package pl.stachura.projekty.dao;

import java.util.List;

import pl.stachura.projekty.model.*;

public interface IPerson {
	
	 public void insert(Person person);
	 public List<Person> select();
	 
}
