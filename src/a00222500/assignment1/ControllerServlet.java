package a00222500.assignment1;

import java.io.IOException;

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
	
	private String url;
	private String driver;
	private String username;
	private String password;

	/**
	 * servletInit() retrieves database information from web.xml and connects to database
	 */
	public void servletInit(){
		
		DatabaseBean db = new DatabaseBean();
		
		ServletConfig config = getServletConfig();
		driver = config.getInitParameter("driver");
		url = config.getInitParameter("url");
		username = config.getInitParameter("username");
		password = config.getInitParameter("password");
		
		//connect to database
		db.connect(url, username, password, driver);		
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
		
		String requestedAction = request.getParameter("action");
		if(requestedAction.equals("query")) {
			//query the database - send the user back to the view (query.jsp)
			String url = "/WEB-INF/jsp/query.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.include(request, response);
			//note - include call will boomerang control back to servlet when jsp is done processing
			
		} else if(requestedAction.equals("add")) {
			//update the database
			String url = "/WEB-INF/jsp/addrecord.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.include(request, response);
			//see note about include call above
			
		} else if(requestedAction.equals("newquery")) {
			//query database and display result
			String url = "/WEB-INF/jsp/results.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
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
