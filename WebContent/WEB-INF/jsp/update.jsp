<jsp:include page="header.jsp"></jsp:include>

<%@ page errorPage="/WEB-INF/jsp/errorPage.jsp" %>

<div id="content">
	<h1>Update Record to Database</h1>
	
	<div id="tabblock">
		<form id="form" name="form" method="post" action="query_database">
					<input type="radio" name="action" value="query"  />Query
					<input type="radio" name="action" value="add"  />Add Record
			<button type="submit">Submit</button>
		</form>
	</div>
	
	<h2>Update record below:</h2>
	
		<div id="stylized" class="myform">
		<form id="form" name="form" method="post" action="query_database">
		<input name="action" type="hidden" value="updaterecord" />
		
			<div>
				${sqlResult }
			</div>
			
			<button type="submit">Submit</button>
			
		</form>
	</div>
	
	<div id="spacer">&nbsp;</div>
	
</div>
	
<jsp:include page="footer.html"></jsp:include>