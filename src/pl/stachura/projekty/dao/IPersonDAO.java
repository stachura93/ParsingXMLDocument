package pl.stachura.projekty.dao;

import java.util.List;

import pl.stachura.projekty.model.Person;

/**
 * This interface represents the parameters of the DAOPerson
 * 
 * @author Stachura Bartlomiej
 */
public interface IPersonDAO {

	/**
	 * Creating schema in database
	 */
	public void createTable();

	/**
	 * Delete table from database
	 */
	public void doDelate();

	/**
	 * Save all people in database
	 * @param persons
	 */
	public void doInsertBatch(final List<Person> persons);

	/**
	 * @return All Person from database 
	 */
	public List<Person> doSelect();

}
