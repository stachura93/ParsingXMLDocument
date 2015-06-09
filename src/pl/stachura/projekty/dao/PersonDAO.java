package pl.stachura.projekty.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import pl.stachura.projekty.jdbc.MySQLConnector;
import pl.stachura.projekty.model.Person;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * This class represents PersonDAO use to communicate with database
 *  
 * @author Stachura Bartlomiej
 */
public class PersonDAO implements IPersonDAO {

	@Override
	public List<Person> doSelect() {
		String sql = "SELECT * FROM USER";
		List<Person> persons = new LinkedList<Person>();

		try {
			Connection con = (Connection) MySQLConnector.getConnection();
			Statement statement = (Statement) con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			Person person = null;
			while (resultSet.next()) {
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

	@Override
	public void createTable() {
		String sql = "CREATE TABLE USER (name VARCHAR(25), surname VARCHAR(25), login VARCHAR(25) )";
		try {
			Connection con = (Connection) MySQLConnector.getConnection();
			Statement statement = (Statement) con.createStatement();
			statement.executeUpdate(sql);

			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doDelate() {
		String sql = "DROP TABLE USER";
		try {
			Connection con = (Connection) MySQLConnector.getConnection();
			Statement statement = (Statement) con.createStatement();
			statement.executeUpdate(sql);

			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void doInsertBatch(final List<Person> persons) {
		String sql = "insert into USER (name, surname, login) values (?, ?, ?)";
		try {
			Connection con = (Connection) MySQLConnector.getConnection();
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(sql);

			final int batchSize = 1000; // We must remember about JDBC limit
			int count = 0;

			for (Person person : persons) {
				ps.setString(1, person.getName());
				ps.setString(2, person.getSurname());
				ps.setString(3, person.getLogin());
				ps.addBatch();
				if (++count % batchSize == 0) {
					ps.executeBatch();
				}
			}
			ps.executeBatch();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
