package pl.stachura.projekty.model;

/**
 * This class represents the parameters of the jQuery DataTable
 * 
 * @author Stachura Bartlomiej
 */
public class DataTableParamModel {

	/**
	 * The request sequence number sent by DataTable
	 */
	public String sEcho;

	/**
	 * The searchText
	 */
	public String sSearch;

	/**
	 * The number of entries shown in the table
	 */
	public int iDisplayLength;

	/**
	 * The first entry to be shown (used for paging)
	 */
	public int iDisplayStart;

	/**
	 * The number of columns
	 */
	public int iColumns;

	/**
	 * The number of columns used in sorting
	 */
	public int iSortingCols;

	/**
	 * The index of the column currently sorted
	 */
	public int iSortColumnIndex;

	/**
	 * The sorting direction ("asc" or desc")
	 */
	public String sSortDirection;

	/**
	 * List of column names (seperated with a comma)
	 */
	public String sColumns;
}
