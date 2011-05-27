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
		//initialize session variable for icon
		HttpSession session = request.getSession();
		session.setAttribute("icon", "seated");
		String sqlResult = "";
		//String sqlStatement =  request.getParameter("query");
		
		
		String requestedAction = request.getParameter("action");
		if(requestedAction.equals("query")) {
			//query the database - send the user back to the view (query.jsp)
			String url = "/WEB-INF/jsp/query.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
			//note - include call will boomerang control back to servlet when jsp is done processing
			
		} else if(requestedAction.equals("add")) {
			//update the database
			String url = "/WEB-INF/jsp/addrecord.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
			//see note about include call above
			
		} else if(requestedAction.equals("newquery")) {
			//query database and display result
			
			//gather select query information
			/*String[] selectCheckboxes = request.getParameterValues("select");
			if (selectCheckboxes != null)
			{
				for (int i = 0; i < selectCheckboxes.length; ++i)
				{
					String selectInputs = selectCheckboxes[i];
				}
			}*/
			
			String queryString = "SELECT * FROM a00222500_Members";
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
			session.setAttribute("sqlResult", sqlResult);
			String url2 = "/WEB-INF/jsp/output.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url2);
			dispatcher.forward(request, response);
			
		} else if(requestedAction.equals("addrecord")) {
			//user is requesting to add a new record to the database
			//data is being passed in from addrecord.jsp
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String address = request.getParameter("address");
			String city	 = request.getParameter("city");
			String country = request.getParameter("country");
			String code = request.getParameter("code");
			String phoneNumber = request.getParameter("phoneNumber");
			String email = request.getParameter("email");
			
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
			System.out.println(query);
			
			db.insertRecord(query);			
		} else if(requestedAction.equals("modify")) {
			int memberID = Integer.parseInt(request.getParameter("memberID"));
			System.out.println("modify " + memberID);
			//get and display the record indicated by the memberID
			
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
			
			//close database connection
			//db.cleanUp();
			
			//send results to results page
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
