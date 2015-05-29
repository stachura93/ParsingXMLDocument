package pl.stachura.projekty.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import pl.stachura.projekty.jdbc.MySQLConnector;
import pl.stachura.projekty.model.Person;

public class PersonDAO implements IPerson {

	@Override
	public void insert(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Person> select() {
		List<Person> persons = new LinkedList<Person>();
        try {
        	   Connection con = (Connection) MySQLConnector.getConnection();
               Statement statement = (Statement) con.createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT * FROM USER");
                
               Person person = null;
               while(resultSet.next()){
                   person = new Person();
                   person.setName(resultSet.getString("name"));
                   person.setSurname("surname");
                   person.setLogin(resultSet.getString("login"));
                   persons.add(person);
               }
               resultSet.close();
               statement.close();
               con.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
           return persons;
	}
}
