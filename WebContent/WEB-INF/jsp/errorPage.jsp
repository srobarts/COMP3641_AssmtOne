

<jsp:include page="header.jsp"></jsp:include>

	<%@ page isErrorPage="true" import="java.io.*" %>
	
	<div id="content">
		<div id="error_output">
		
			<h2><%= pageContext.getServletConfig().getInitParameter("errorMessage") %></h2>
			
			<h4><%= pageContext.getServletConfig().getInitParameter("errorQueryMessage") %></h4>
			<jsp:useBean id="DatabaseBean" class="a00222500.assignment1.DatabaseBean" scope="session" />
			<jsp:getProperty name="DatabaseBean" property="queryString" />
			
			<h4><%= pageContext.getServletConfig().getInitParameter("errorSummaryMessage") %></h4>			
			<font color="red">
				<%= exception.toString() %><br>
			</font>
			
			<h4><%= pageContext.getServletConfig().getInitParameter("errorDetailMessage") %></h4>
			<p>
			<%
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				exception.printStackTrace(pw);
				out.print(sw);
				sw.close();
				pw.close();
			%>
			</p>
			<br />
			<h2 class="link"><a href="Lab04.jsp"><%= pageContext.getServletConfig().getInitParameter("linkText") %></a></h2>
		</div> <!-- error_output -->
	</div> <!-- content -->
	
<jsp:include page="footer.html"></jsp:include>