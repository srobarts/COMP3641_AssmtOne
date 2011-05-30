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
	
	public String create_table_query()
	{
		//Since this function is pretty specific to this address book it is not
		//a generic create table function where I pass in table names and
		//column names.  If you need to change the table then the code has
		//to change - but if you're changing the database that is pretty major
		//anyway under most circumstances.
		String query = 	"IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='a00222500_Members' and xtype='U') CREATE TABLE a00222500_Members " +
						"( memberID	INT IDENTITY(1,1), " +
						"firstName	VARCHAR(255) NOT NULL, " +
						"lastName VARCHAR(255) NOT NULL, " +
						"address VARCHAR(255) NOT NULL, " +
						"city VARCHAR(255) NOT NULL, " +
						"country VARCHAR(255) NOT NULL, " +
						"code CHAR(255) NOT NULL, " +
						"phoneNumber CHAR(255) NOT NULL, " +
						"email VARCHAR(255) NOT NULL) ";
		
		return query;
	}
	
	public void create_table(String create_table_query)
	{
		try {
			stmt = con.createStatement();
			stmt.execute(create_table_query);
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	public int getMaxID() {
		ResultSet rs = null;
		int maxID = 0;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM a00222500_Members WHERE MemberID = (SELECT MAX( MemberID ) FROM a00222500_Members)");
			while(rs.next()) {
				maxID = rs.getInt("memberID");
				System.out.println("bean");
				System.out.println(maxID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxID;
	}
	
	public void insertRecord(String query) {
		try {
			stmt = con.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateRecord(int id, String firstName, String lastName, String address, 
		String city, String country, String code, String phoneNumber, String email) {
		
		//create update statement
		try {
			stmt = con.createStatement();
			String update_query = "UPDATE a00222500_Members" +
								" SET firstName = '" + firstName + "'" + 
								", lastName = '" + lastName + "'" +
								", address = '" + address + "'" +
								", city = '" + city + "'" +
								", country = '" + country + "'" +
								", code = '" + code + "'" +
								", phoneNumber = '" + phoneNumber + "'" +
								", email = '" + email + "'" +
								" WHERE memberID = " + id;
			System.out.println(update_query);
			stmt.executeUpdate(update_query);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void deleteRecord(int memberID) {
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM a00222500_Members WHERE memberID = " + memberID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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

