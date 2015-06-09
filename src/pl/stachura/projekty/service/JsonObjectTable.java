package pl.stachura.projekty.service;

import java.util.List;

import pl.stachura.projekty.model.Person;

/**
 * This class represents the parameters for DataTable from the specified request
 * 
 * @see <a href="https://www.datatables.net/manual/server-side">DataTables param</a>
 * 
 * @author Stachura Bartlomiej
 */
public class JsonObjectTable {

	/**
	 * The draw counter that this object is a response to - from the draw
	 * parameter sent as part of the data request.
	 */
	int draw;
	/**
	 * Total records, before filtering (i.e. the total number of records in the
	 * database)
	 */
	int recordsTotal;
	/**
	 * Total records, after filtering (i.e. the total number of records after
	 * filtering has been applied)
	 */
	int recordsFiltered;

	/**
	 * An unaltered copy of sEcho sent from the client side. This parameter will
	 * change with each draw.
	 */
	String sEcho;

	/**
	 * This is a string of column names, comma separated (used in combination
	 * with sName)
	 */
	String sColumns;

	/**
	 * The data in a 2D array. Note that you can change the name of this
	 * parameter with sAjaxDataProp.
	 */
	List<Person> data;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
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

	public List<Person> getData() {
		return data;
	}

	public void setData(List<Person> data) {
		this.data = data;
	}

}
