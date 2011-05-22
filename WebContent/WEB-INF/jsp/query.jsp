<jsp:include page="header.jsp"></jsp:include>

<%@ page errorPage="/WEB-INF/jsp/errorPage.jsp" %>

<div id="content">
	<h1>Query Database</h1>
	<br />
			<form id="two" name="form" method="post" action="query_database">
				<!-- hidden input to tell controller servlet what we are doing -->
				<input name="action" type="hidden" value="newquery" />
			
				<fieldset id="select">
				    <legend>SELECT</legend>
				    <p>Choose what items to include in SELECT ...</p><br />
				    <label for="userid">User ID: </label>
				    <input name="select" id="userid" type="checkbox" value="userid" tabindex="20" />
				    <br />
				    <label for="firstname">firstname : </label> 
				    <input name="select" id="firstname" type="checkbox" value="firstname" tabindex="21" />
				    <br />
				    <label for="lastname">lastname : </label> 
				    <input name="select" id="lastname" type="checkbox" value="lastname" tabindex="22" />
				    <br />
				    <label for="address">address : </label> 
				    <input name="select" id="address" type="checkbox" value="address" tabindex="23" />
				    <br />
				    <label for="city">city : </label> 
				    <input name="select" id="city" type="checkbox" value="city" tabindex="23" />
				    <br />
				    <label for="country">country : </label> 
				    <input name="select" id="country" type="checkbox" value="country" tabindex="23" />
				    <br />
				    <label for="postalcode">postalcode : </label> 
				    <input name="select" id="postalcode" type="checkbox" value="postalcode" tabindex="23" />
				    <br />
				    <label for="phone">phone : </label> 
				    <input name="select" id="phone" type="checkbox" value="phone" tabindex="23" />
				    <br />
				    <label for="email">phone : </label> 
				    <input name="select" id="email" type="checkbox" value="email" tabindex="23" />
				    <br />
				</fieldset>
				
				<fieldset id="where">
				    <legend>WHERE</legend>
				    <p>Qualifying WHERE clause ...</p><br />
				    <label for="where">WHERE : </label> 
				    <input name="where" size="50" id="where" type="text" tabindex="1" />
				</fieldset>
				
				<fieldset id="orderby">
				    <legend>ORDER BY</legend>
				    <p>Choose what items to ORDER BY ...</p><br />
				    <label for="userid">User ID: </label>
				    <input name="orderby" id="userid" type="checkbox" value="userid" tabindex="20" />
				    <br />
				    <label for="firstname">firstname : </label> 
				    <input name="orderby" id="firstname" type="checkbox" value="firstname" tabindex="21" />
				    <br />
				    <label for="lastname">lastname : </label> 
				    <input name="orderby" id="lastname" type="checkbox" value="lastname" tabindex="22" />
				    <br />
				    <label for="address">address : </label> 
				    <input name="orderby" id="address" type="checkbox" value="address" tabindex="23" />
				    <br />
				    <label for="city">city : </label> 
				    <input name="orderby" id="city" type="checkbox" value="city" tabindex="23" />
				    <br />
				    <label for="country">country : </label> 
				    <input name="orderby" id="country" type="checkbox" value="country" tabindex="23" />
				    <br />
				    <label for="postalcode">postalcode : </label> 
				    <input name="orderby" id="postalcode" type="checkbox" value="postalcode" tabindex="23" />
				    <br />
				    <label for="phone">phone : </label> 
				    <input name="orderby" id="phone" type="checkbox" value="phone" tabindex="23" />
				    <br />
				    <label for="email">phone : </label> 
				    <input name="orderby" id="email" type="checkbox" value="email" tabindex="23" />
				    <br />
				</fieldset>
				
				<fieldset id="opt">
				    <legend>ASC/DESC</legend>
				    <p>Choose ASCENDING or DESCENDING sort order ...</p><br />
				    <select name="sort_order">
				      <option selected="selected" label="asc" value="asc">ASC</option>
				      <option label="desc" value="desc">DESC</option>
					</select>
				</fieldset>
				
				<p>
  					<input id="button1" type="submit" value="Send" /> 
  				</p>
				
			</form>
</div>

<jsp:include page="footer.html"></jsp:include>