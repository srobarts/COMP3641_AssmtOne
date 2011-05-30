package a00222500.assignment1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DatabaseBean db;
	private final String REG_PHONE = "(\\()?(\\d{3})(\\))?([\\.\\-\\/ ])?(\\d{3})([\\.\\-\\/ ])?(\\d{4})";
	private final String REG_EMAIL = "(\\w)([\\.-]?\\w+)*@(\\w+)([\\.-]?\\w+)*(\\.\\w{2,3})";
	private final String REG_STRING = "^[a-zA-Z']{1,40}$";

	/**
	 * servletInit() retrieves database information from web.xml and connects to database
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String url;
		String driver;
		String username;
		String password;
		
		db = new DatabaseBean();
		
		config = getServletConfig();
		driver = config.getInitParameter("driver");
		url = config.getInitParameter("url");
		username = config.getInitParameter("username");
		password = config.getInitParameter("password");
		
		//connect to database
		db.connect(url, username, password, driver);	
		
		String create_table_query = db.create_table_query();
		db.create_table(create_table_query);
		
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//initialize session variables
		HttpSession session = request.getSession();
		session.setAttribute("icon", "seated");
		session.setAttribute("requestType", "query");
		session.setAttribute("insertStatus", "valid");
		String sqlResult = "";
		//String sqlStatement =  request.getParameter("query");
		
		
		String requestedAction = request.getParameter("action");
		if(requestedAction.equals("")) {
			//submit is clicked with no attached action - just sent user to start page
			String url = "/start.html";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} else if(requestedAction.equals("query")) {
			//query the database - send the user back to the view (query.jsp)
			String url = "/WEB-INF/jsp/query.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);			
		} else if(requestedAction.equals("add")) {
			//update the database
			String url = "/WEB-INF/jsp/addrecord.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
			//see note about include call above
			
		} else if(requestedAction.equals("newquery")) {
			//query database and display result
			
			//gather select query information
			
			//get the SELECT values
			String query = "SELECT ";
			String select = "";
			String[] selectCheckboxes = request.getParameterValues("select");
			if (selectCheckboxes != null)
			{
				for (int i = 0; i < selectCheckboxes.length; ++i)
				{
					select = selectCheckboxes[i];
					query = query + select + " ";
				}
			}
			//add FROM
			query = query + " FROM a00222500 ";
			
			//add WHERE
			String where = request.getParameterValues("where");
			query = query + where + " ";
			
			String queryString = "SELECT * FROM a00222500_Members ORDER BY MemberID";
			db.setQueryString(queryString);
			@SuppressWarnings("rawtypes")
			Vector tableData = db.runQuery();
			@SuppressWarnings("rawtypes")
			Iterator rows = tableData.iterator();
			
			//display headers
			@SuppressWarnings("rawtypes")
			Vector headerNames = null;
			try {
				headerNames = db.generateMetaData();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			@SuppressWarnings("rawtypes")
			Iterator headers = headerNames.iterator();
			
			try {
				sqlResult = a00222500.assignment1.ServletUtilities.getTableHTML(headers, rows);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//close database connection
			//db.cleanUp();
			
			//send results to results page
			session.setAttribute("requestType", "query");
			session.setAttribute("insertStatus", "valid");
			session.setAttribute("queryString", queryString);
			session.setAttribute("sqlResult", sqlResult);
			String url2 = "/WEB-INF/jsp/output.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url2);
			dispatcher.forward(request, response);
			
		} else if(requestedAction.equals("addrecord")) {
			//user is requesting to add a new record to the database
			//data is being passed in from addrecord.jsp	
			int maxID = 0;
			
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String address = request.getParameter("address");
			String city	 = request.getParameter("city");
			String country = request.getParameter("country");
			String code = request.getParameter("code");
			String phoneNumber = request.getParameter("phoneNumber");
			String email = request.getParameter("email");
			
			if( ServletUtilities.isValidInput(phoneNumber, REG_PHONE) && 
					ServletUtilities.isValidInput(email, REG_EMAIL) &&
						ServletUtilities.isValidInput(firstName, REG_STRING) &&
							ServletUtilities.isValidInput(lastName, REG_STRING) &&
								 	ServletUtilities.isValidInput(city, REG_STRING) &&
								 		ServletUtilities.isValidInput(country, REG_STRING) ) {
				System.out.println("valid");
				
				maxID = db.getMaxID();
				maxID++;
				
				System.out.println("servlet");
				System.out.println(maxID);
				
				String query = "INSERT INTO a00222500_Members VALUES " +
								"('" + firstName + "'," +
								"'" + lastName + "'," +
								"'" + address + "'," +
								"'" + city + "'," +
								"'" + country + "'," +
								"'" + code + "'," +
								"'" + phoneNumber + "'," +
								"'" + email + "')";
		
				//String query = "DROP TABLE a00222500_Members";
				
				db.insertRecord(query);		
			
				//display output to show that record has been created
				String queryString = "SELECT * FROM a00222500_Members WHERE memberID = " + maxID;
				System.out.println(queryString);
				db.setQueryString(queryString);
				@SuppressWarnings("rawtypes")
				Vector tableData = db.runQuery();
				@SuppressWarnings("rawtypes")
				Iterator rows = tableData.iterator();
				
				//display headers
				@SuppressWarnings("rawtypes")
				Vector headerNames = null;
				try {
					headerNames = db.generateMetaData();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				@SuppressWarnings("rawtypes")
				Iterator headers = headerNames.iterator();
				
				try {
					sqlResult = a00222500.assignment1.ServletUtilities.getTableHTML(headers, rows);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				//send results to results page
				session.setAttribute("requestType", "insert");
				session.setAttribute("insertStatus", "valid");
				session.setAttribute("queryString", queryString);
				session.setAttribute("sqlResult", sqlResult);
				String url2 = "/WEB-INF/jsp/output.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url2);
				dispatcher.forward(request, response);
			
			} else {
				session.setAttribute("requestType", "insert");
				System.out.println("invalid");
				//display message saying that input was invalid
				session.setAttribute("insertStatus", "invalid");
				String url2 = "/WEB-INF/jsp/output.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url2);
				dispatcher.forward(request, response);
			}
			
			
		} else if(requestedAction.equals("modify")) {
			int memberID = Integer.parseInt(request.getParameter("memberID"));
			System.out.println("modify " + memberID);
			
			//get and display the record indicated by the memberID
			String queryString = "SELECT * FROM a00222500_Members WHERE memberID = " + memberID;
			db.setQueryString(queryString);
			@SuppressWarnings("rawtypes")
			Vector tableData = db.runQuery();
			@SuppressWarnings("rawtypes")
			Iterator rows = tableData.iterator();
			
			//display headers
			@SuppressWarnings("rawtypes")
			Vector headerNames = null;
			try {
				headerNames = db.generateMetaData();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			@SuppressWarnings("rawtypes")
			Iterator headers = headerNames.iterator();
			
			try {
				sqlResult = a00222500.assignment1.ServletUtilities.getUpdateTableHTML(headers, rows);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//send results to results page
			session.setAttribute("queryString", queryString);
			session.setAttribute("sqlResult", sqlResult);
			String url2 = "/WEB-INF/jsp/update.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url2);
			dispatcher.forward(request, response);
			
			
		} else if(requestedAction.equals("updaterecord")) {
			int id = Integer.parseInt(request.getParameter("memberID"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String country = request.getParameter("country");
			String code = request.getParameter("code");
			String phoneNumber = request.getParameter("phoneNumber");
			String email = request.getParameter("email");
			
			//tuck away into database
			db.updateRecord(id, firstName, lastName, address, city, country, code, phoneNumber, email);			
			
			//display output to show that record has been updated
			//re-display page with same query as previous
			@SuppressWarnings("rawtypes")
			Vector tableData = db.runQuery();
			@SuppressWarnings("rawtypes")
			Iterator rows = tableData.iterator();
			
			//display headers
			@SuppressWarnings("rawtypes")
			Vector headerNames = null;
			try {
				headerNames = db.generateMetaData();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			@SuppressWarnings("rawtypes")
			Iterator headers = headerNames.iterator();
			
			try {
				sqlResult = a00222500.assignment1.ServletUtilities.getTableHTML(headers, rows);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//send results to results page
			session.setAttribute("requestType", "update");
			session.setAttribute("insertStatus", "valid");
			String queryString = db.getQueryString();
			session.setAttribute("queryString", queryString);
			session.setAttribute("sqlResult", sqlResult);
			String url2 = "/WEB-INF/jsp/output.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url2);
			dispatcher.forward(request, response);			
			
		} else if(requestedAction.equals("delete")) {
			int memberID = Integer.parseInt(request.getParameter("memberID"));
			System.out.println("delete " + memberID);
			//delete the record indicated by the memberID
			db.deleteRecord(memberID);
			
			//re-display page with same query as previous
			@SuppressWarnings("rawtypes")
			Vector tableData = db.runQuery();
			@SuppressWarnings("rawtypes")
			Iterator rows = tableData.iterator();
			
			//display headers
			@SuppressWarnings("rawtypes")
			Vector headerNames = null;
			try {
				headerNames = db.generateMetaData();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			@SuppressWarnings("rawtypes")
			Iterator headers = headerNames.iterator();
			
			try {
				sqlResult = a00222500.assignment1.ServletUtilities.getTableHTML(headers, rows);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//send results to results page
			session.setAttribute("requestType", "delete");
			session.setAttribute("insertStatus", "valid");
			String queryString = db.getQueryString();
			session.setAttribute("queryString", queryString);
			session.setAttribute("sqlResult", sqlResult);
			String url2 = "/WEB-INF/jsp/output.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url2);
			dispatcher.forward(request, response);
			
		}
		
		
		
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
