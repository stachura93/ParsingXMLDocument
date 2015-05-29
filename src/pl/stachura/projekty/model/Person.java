package pl.stachura.projekty.model;

public class Person {

	private String name;
	private String surname;
	private String login;

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
		return "[name: " + name + ", surname: " + surname + ", login: " + login + "]";
	}

}
