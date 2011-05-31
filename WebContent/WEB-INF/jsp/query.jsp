<jsp:include page="header.jsp"></jsp:include>

<%@ page errorPage="/WEB-INF/jsp/errorPage.jsp" %>

<div id="content">
	<h1>Query Database</h1>
	
	<div id="tabblock">
		<form id="form" name="form" method="post" action="query_database">
					<input type="radio" name="action" value="query"  />Query
					<input type="radio" name="action" value="add"  />Add Record
			<button type="submit">Submit</button>
		</form>
	</div>
	
	<div>
			<form id="two" name="form" method="post" action="query_database">
				<!-- hidden input to tell controller servlet what we are doing -->
				<input name="action" type="hidden" value="newquery" />
			
				<fieldset id="select">
				    <legend>SELECT</legend>
				    <p>Choose what items to include in SELECT ...</p><br />
				    <label for="userid">User ID: </label>
				    <input name="select" id="userid" type="checkbox" checked="checked" value="memberID" tabindex="20" />
				    <br />
				    <label for="firstname">firstname : </label> 
				    <input name="select" id="firstname" type="checkbox" checked="checked" value="firstName" tabindex="21" />
				    <br />
				    <label for="lastname">lastname : </label> 
				    <input name="select" id="lastname" type="checkbox" checked="checked" value="lastName" tabindex="22" />
				    <br />
				    <label for="address">address : </label> 
				    <input name="select" id="address" type="checkbox" checked="checked" value="address" tabindex="23" />
				    <br />
				    <label for="city">city : </label> 
				    <input name="select" id="city" type="checkbox" checked="checked" value="city" tabindex="23" />
				    <br />
				    <label for="country">country : </label> 
				    <input name="select" id="country" type="checkbox" checked="checked" value="country" tabindex="23" />
				    <br />
				    <label for="postalcode">postalcode : </label> 
				    <input name="select" id="postalcode" type="checkbox" checked="checked" value="code" tabindex="23" />
				    <br />
				    <label for="phone">phone : </label> 
				    <input name="select" id="phone" type="checkbox" checked="checked" value="phoneNumber" tabindex="23" />
				    <br />
				    <label for="email">phone : </label> 
				    <input name="select" id="email" type="checkbox" checked="checked" value="email" tabindex="23" />
				    <br />
				</fieldset>
				
				<fieldset id="where">
				    <legend>WHERE</legend>
				    <p>Qualifying WHERE clause ...</p><br />
				    <label for="where">WHERE : </label> 
				    <input name="where" size="50" type="text" />
				</fieldset>
				
				<fieldset id="orderby">
				    <legend>ORDER BY</legend>
				    <p>Choose what items to ORDER BY ...</p><br />
				    <label for="userid">User ID: </label>
				    <input name="orderby" id="userid" type="radio" value="memberID" tabindex="20" />
				    <br />
				    <label for="firstname">firstname : </label> 
				    <input name="orderby" id="firstname" type="radio" value="firstName" tabindex="21" />
				    <br />
				    <label for="lastname">lastname : </label> 
				    <input name="orderby" id="lastname" type="radio" checked="checked" value="lastName" tabindex="22" />
				    <br />
				    <label for="address">address : </label> 
				    <input name="orderby" id="address" type="radio" value="address" tabindex="23" />
				    <br />
				    <label for="city">city : </label> 
				    <input name="orderby" id="city" type="radio" value="city" tabindex="23" />
				    <br />
				    <label for="country">country : </label> 
				    <input name="orderby" id="country" type="radio" value="country" tabindex="23" />
				    <br />
				    <label for="postalcode">postalcode : </label> 
				    <input name="orderby" id="postalcode" type="radio" value="code" tabindex="23" />
				    <br />
				    <label for="phone">phone : </label> 
				    <input name="orderby" id="phone" type="radio" value="phoneNumber" tabindex="23" />
				    <br />
				    <label for="email">phone : </label> 
				    <input name="orderby" id="email" type="radio" value="email" tabindex="23" />
				    <br />
				</fieldset>
				
				<fieldset id="opt">
				    <legend>ASC/DESC</legend>
				    <p>Choose ASCENDING or DESCENDING sort order ...</p><br />
				    <select name="sort_order">
				      <option selected="selected" label="asc" value="ASC">ASC</option>
				      <option label="desc" value="DESC">DESC</option>
					</select>
				</fieldset>
				
				<p>
  					<input id="button1" type="submit" value="Search" /> 
  				</p>
				
			</form>
			
		</div>
		<div id="spacer">&nbsp;</div>
		<div id="spacer">&nbsp;</div>
		<div class="clearing">&nbsp;</div>
</div>

<jsp:include page="footer.html"></jsp:include>