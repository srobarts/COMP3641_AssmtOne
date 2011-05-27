<jsp:include page="header.html"></jsp:include>

<%@ page errorPage="/WEB-INF/jsp/errorPage.jsp" %>

<div id="content">

	<h1>Query Result</h1>
	<br />
	<h2>Here are the results of your query:</h2>

		<div id="tablecontent">
			${sqlResult }
		</div>
</div>
	
<jsp:include page="footer.html"></jsp:include>