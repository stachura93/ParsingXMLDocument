package pl.stachura.projekty.service;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pl.stachura.projekty.model.Person;

public class ReadXMLPersonSAX extends DefaultHandler {

	private ArrayList<Person> personsList = null;
	private Person person = null;

	public ArrayList<Person> getPersonsList() {
		return personsList;
	}

	boolean bName;
	boolean bSurname;
	boolean bLogin;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("user")) {

			person = new Person();

			if (personsList == null)
				personsList = new ArrayList<>();
		} else if (qName.equalsIgnoreCase("name")) {
			bName = true;
		} else if (qName.equalsIgnoreCase("surname")) {
			bSurname = true;
		} else if (qName.equalsIgnoreCase("login")) {
			bLogin = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase("user")) {
			personsList.add(person);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (bName) {
			person.setName(new String(ch, start, length));
			bName = false;
		} else if (bSurname) {
			person.setSurname(new String(ch, start, length));
			bSurname = false;
		} else if (bLogin) {
			person.setLogin(new String(ch, start, length));
			bLogin = false;
		}

	}

}
