<jsp:include page="header.jsp"></jsp:include>

<%@ page errorPage="/WEB-INF/jsp/errorPage.jsp" %>

<div id="content">
	<h1>Add Record to Database</h1>
	
	<div id="tabblock">
		<form id="form" name="form" method="post" action="query_database">
					<input type="radio" name="action" value="query"  />Query
					<input type="radio" name="action" value="add"  />Add Record
			<button type="submit">Submit</button>
		</form>
	</div>
	
	<h2>Please fill in the form below to add to database:</h2>
		
	<div id="stylized" class="myform">
		<form id="form" name="form" method="post" action="query_database" onSubmit="return submitIt(this)">
		<input name="action" type="hidden" value="addrecord" />
		
			<div>
				<label>First Name:
				<span class="small">Enter your first name:</span>
				</label>
				<input SIZE=5 MAXLENGTH=25 name="firstName" id="firstName" />
				
				<label>Last Name:
				<span class="small">Enter your last name:</span>
				</label>
				<input SIZE=5 MAXLENGTH=25 name="lastName" id="lastName" />
				
				<label>Address:
				<span class="small">Enter your address:</span>
				</label>
				<input SIZE=5 MAXLENGTH=25 name="address" id="address" />
				
				<label>City:
				<span class="small">Enter your city:</span>
				</label>
				<input SIZE=5 MAXLENGTH=25 name="city" id="city" />
				
				<label>Country:
				<span class="small">Enter your country:</span>
				</label>
				<input SIZE=5 MAXLENGTH=25 name="country" id="country" />
				
				<label>Postal Code:
				<span class="small">Enter your postal code (example: VON 1B0):</span>
				</label>
				<input SIZE=5 MAXLENGTH=25 name="code" id="code" />
				
				<label>Phone Number:
				<span class="small">Enter the phone number where you wish to be contacted:</span>
				</label>
				<input SIZE=5 MAXLENGTH=25 name="phoneNumber" id="phoneNumber" />
				
				<label>Email:
				<span class="small">Enter your main email address:</span>
				</label>
				<input SIZE=5 MAXLENGTH=25 name="email" id="email" />
			</div>
			
			<button type="submit">Submit</button>
			
		</form>
	</div>
	
	<div id="spacer">&nbsp;</div>
	
</div>

<jsp:include page="footer.html"></jsp:include>