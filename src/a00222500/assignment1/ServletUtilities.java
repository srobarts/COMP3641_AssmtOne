package a00222500.assignment1;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

public class ServletUtilities {
	
	public static String getHTMLTable(ResultSet results) throws SQLException {
		  StringBuffer htmlRows = new StringBuffer();
		  ResultSetMetaData metaData = results.getMetaData();
		  int columnCount = metaData.getColumnCount();
		  
		  htmlRows.append("<table cellpadding=\"5\" border=\"1\">");
		  htmlRows.append("<tr>");
		  for(int i=1; i<=columnCount; i++)
			  htmlRows.append("<td><b>" + metaData.getColumnName(i) + "</b></td>");
		  htmlRows.append("</tr>");
		  
		  while(results.next())
		  {
			  htmlRows.append("<tr>");
			  for(int i=1; i<=columnCount; i++)
				  htmlRows.append("<td>" + results.getString(i) + "</td>");
		  }
		  htmlRows.append("</tr>");
		  htmlRows.append("</table>");
		  return htmlRows.toString();
	  }
	
	public static String getTableHTML(@SuppressWarnings("rawtypes") Iterator headers, @SuppressWarnings("rawtypes") Iterator rows) throws SQLException {
		StringBuffer htmlRows = new StringBuffer();
		
		htmlRows.append("<table cellpadding=\"5\" border=\"1\">");
		
		//display columns
		int count = 0;
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
				htmlRows.append("<td>" + field + "</td>");
			}
			htmlRows.append("</tr>");
			count++;
		}
		htmlRows.append("</table>");
		return htmlRows.toString();
			
	}

}
