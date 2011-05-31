<jsp:include page="header.jsp"></jsp:include>

	<%@ page isErrorPage="true" import="java.io.*" %>
	
	<div id="content">
		<h1>An Error Has Occured</h1>
	
		<div id="tabblock">
			<form id="form" name="form" method="post" action="query_database">
						<input type="radio" name="action" value="query"  />Query
						<input type="radio" name="action" value="add"  />Add Record
				<button type="submit">Submit</button>
			</form>
		</div>
		
		<div id="error_output">

			<h3>Request that failed: ${pageContext.errorData.requestURI}</h3>
			<h3>Status code: ${pageContext.errorData.statusCode}</h3>
			<h3>Exception: ${pageContext.errorData.throwable}</h3>
			<h3>${pageContext.errorData.servletName}</h3>
				<br /><br />
		</div> <!-- error_output -->
		
	</div> <!-- content -->
	
<jsp:include page="footer.html"></jsp:include>