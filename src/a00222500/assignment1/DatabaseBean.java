package a00222500.assignment1;

import java.sql.*;
import java.util.*;

public class DatabaseBean
{
	private String queryString = "";
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet queryResults = null;
	@SuppressWarnings("rawtypes")
	private Vector vRows = null;
	@SuppressWarnings("rawtypes")
	private Vector headers = null;
	private String url = "";
	private String driver = "";
	private String username = "";
	private String password = "";

	public DatabaseBean(){}
	
	public void setURL(String url)
	{
		this.url = url;
	}
	
	public String getURL()
	{
		return (url);
	}
	
	public void setDriver(String driver)
	{
		this.driver = driver;
	}
	
	public String getDriver()
	{
		return (driver);
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return (username);
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPassword()
	{
		return (password);
	}

	public void connect(String url, String username, String password, String driver)
	{
		try {
			Class.forName( driver );
			con = DriverManager.getConnection( url, username, password );
			//stmt = con.createStatement();

		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getQueryString()
	{
		return queryString;
	}

	public void setQueryString(String qs)
	{
		queryString = qs;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector runQuery()
	{
		vRows = new Vector();
		int numCols;

		try {
			System.out.print(con);
			stmt = con.createStatement();

			queryResults = stmt.executeQuery (queryString);
			ResultSetMetaData meta = queryResults.getMetaData();
			numCols = meta.getColumnCount();

			while (queryResults.next()) {
				Vector vOneRow = new Vector();
				for (int ndx=1; ndx<=numCols; ndx++) {
					vOneRow.addElement(queryResults.getString(ndx));
				}
				vRows.addElement(vOneRow);
			}

		} catch(SQLException ex) {
			ex.printStackTrace();
		} 
		return vRows;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector generateMetaData() throws SQLException {

		ResultSetMetaData rsmd = queryResults.getMetaData();
		int columnCount = rsmd.getColumnCount();

		headers = new Vector();
        for ( int i = 1; i <= columnCount; i++) {
			headers.add(rsmd.getColumnName(i));
        }
        return headers;
	}
	
	public void setResultSet(ResultSet rs)
	{
		queryResults = rs;
	}

	public void cleanUp()
	{
		try {
			con.close();
			stmt.close();
		}catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}
}

