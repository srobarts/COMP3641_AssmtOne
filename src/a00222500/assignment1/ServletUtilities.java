package a00222500.assignment1;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServletUtilities {
	
	public static String getTableHTML(@SuppressWarnings("rawtypes") Iterator headers, @SuppressWarnings("rawtypes") Iterator rows) throws SQLException {
		StringBuffer htmlRows = new StringBuffer();
		
		htmlRows.append("<div id=\"tablecontent\"><table cellpadding=\"5\" border=\"1\" >");
		
		htmlRows.append("<tr>");
		while (headers.hasNext()) {
			String title = (String)headers.next();
			htmlRows.append("<th>" + title + "</th>");
		}
		htmlRows.append("<th colspan=\"3\">Actions</th>");
		htmlRows.append("</tr>");
		
		//display columns
		int count = 0;
		int fieldCount = 1;
		String memberID = "";
		while (rows.hasNext()) {			
			if (count % 2 == 0) {
				htmlRows.append("<tr bgcolor=\"#c3f3c3\">");
			} else {
				htmlRows.append("<tr bgcolor=\"#FFFFF\">");
			}
			@SuppressWarnings("rawtypes")
			Vector singleRow = (Vector)rows.next();
			@SuppressWarnings("rawtypes")
			Iterator fields = singleRow.iterator();
			while (fields.hasNext()) {
				String field = (String)fields.next();
				if(fieldCount == 1){ 
					//if we are looking at the first field it is the memberID
					memberID = field; 
				}
				htmlRows.append("<td>" + field + "</td>");
				fieldCount++;
			}
			//reset field count
			fieldCount = 1;
			htmlRows.append("<form id=\"two\" name=\"form\" method=\"post\" action=\"query_database\">");
			htmlRows.append("<td><input type=\"radio\" name=\"action\" value=\"modify\" />Update</td>");
			htmlRows.append("<td><input type=\"radio\" name=\"action\" value=\"delete\" />Delete</td>");
			htmlRows.append("<input type=\"hidden\" name=\"memberID\" value=\"" + memberID + "\" />");
			htmlRows.append("<td><button type=\"submit\">Submit</button></td>");
			htmlRows.append("</form>");
			htmlRows.append("</tr>");
			count++;
		}
		htmlRows.append("</table></div>");
		return htmlRows.toString();
			
	}
	
	public static String getUpdateTableHTML(@SuppressWarnings("rawtypes") Iterator headers, @SuppressWarnings("rawtypes") Iterator rows) throws SQLException {
		StringBuffer htmlRows = new StringBuffer();
		
		String field;
		int fieldCount = 1;
		String memberID = "";
		String firstName = "";
		String lastName = "";
		String address = "";
		String city = "";
		String country = "";
		String code = "";
		String phoneNumber = "";
		String email = "";
		

	
		//get columns
		@SuppressWarnings("rawtypes")
		Vector singleRow = (Vector)rows.next();
		@SuppressWarnings("rawtypes")
		Iterator fields = singleRow.iterator();
		while (fields.hasNext()) {
			field = (String)fields.next();
			if(fieldCount == 1){ 
				//if we are looking at the first field it is the memberID
				memberID = field; 
			} else if(fieldCount == 2) {
				firstName = field;
			} else if(fieldCount == 3) {
				lastName = field;
			} else if(fieldCount == 4) {
				address = field;
			} else if(fieldCount == 5) {
				city = field;
			} else if(fieldCount == 6) {
				country = field;
			} else if(fieldCount == 7) {
				code = field;
			} else if(fieldCount == 8) {
				phoneNumber = field;
			} else if(fieldCount == 9) {
				email = field;
			}
			fieldCount++;
		}
		
		//display columns
		htmlRows.append("<label>MemberID:</label><input type=\"text\" name=\"memberID\" value=\"" + memberID + "\" />");
		htmlRows.append("<label>First Name:</label><input type=\"text\" name=\"firstName\" value=\"" + firstName + "\" />");
		htmlRows.append("<label>Last Name:</label><input type=\"text\" name=\"lastName\" value=\"" + lastName + "\" />");
		htmlRows.append("<label>Address:</label><input type=\"text\" name=\"address\" value=\"" + address + "\" />");
		htmlRows.append("<label>City:</label><input type=\"text\" name=\"city\" value=\"" + city + "\" />");
		htmlRows.append("<label>Country:</label><input type=\"text\" name=\"country\" value=\"" + country + "\" />");
		htmlRows.append("<label>Code:</label><input type=\"text\" name=\"code\" value=\"" + code + "\" />");
		htmlRows.append("<label>Phone Number:</label><input type=\"text\" name=\"phoneNumber\" value=\"" + phoneNumber + "\" />");
		htmlRows.append("<label>Email:</label><input type=\"text\" name=\"email\" value=\"" + email + "\" />");

		return htmlRows.toString();
		
	}
	
	public static boolean isValidInput(String input, String pattern) {
		Pattern patt = Pattern.compile(pattern);
		Matcher match = patt.matcher(input);
		
		return match.matches();
	}

}










