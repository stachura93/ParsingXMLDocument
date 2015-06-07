package pl.stachura.projekty.service;

import java.util.List;

import pl.stachura.projekty.model.Person;

public class JsonObjectTable {
	 int iTotalRecords;

	    int iTotalDisplayRecords;

	    String sEcho;

	    String sColumns;

	    List<Person> aaData;

		public int getiTotalRecords() {
			return iTotalRecords;
		}

		public void setiTotalRecords(int iTotalRecords) {
			this.iTotalRecords = iTotalRecords;
		}

		public int getiTotalDisplayRecords() {
			return iTotalDisplayRecords;
		}

		public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
			this.iTotalDisplayRecords = iTotalDisplayRecords;
		}

		public String getsEcho() {
			return sEcho;
		}

		public void setsEcho(String sEcho) {
			this.sEcho = sEcho;
		}

		public String getsColumns() {
			return sColumns;
		}

		public void setsColumns(String sColumns) {
			this.sColumns = sColumns;
		}

		public List<Person> getAaData() {
			return aaData;
		}

		public void setAaData(List<Person> aaData) {
			this.aaData = aaData;
		}


}
