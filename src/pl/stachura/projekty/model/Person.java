package pl.stachura.projekty.model;

/**
 * This class represents the parameters of the Person
 * 
 * @author Stachura Bartlomiej
 */
public class Person {

	/**
	 * The Person First Name
	 */
	private String name;

	/**
	 * The Person Last Name
	 */
	private String surname;

	/**
	 * The Person Login
	 */
	private String login;

	public Person() {
	}

	public Person(String name, String surname, String login) {
		super();
		this.name = name;
		this.surname = surname;
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "[name: " + name + ", surname: " + surname + ", login: " + login
				+ "]";
	}

}
