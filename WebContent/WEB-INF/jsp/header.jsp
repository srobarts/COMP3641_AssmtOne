<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles.css" >
<title>Java Address Book</title>

	<SCRIPT language="javascript" type="text/javascript"> 

		re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		regexp = /^\(?(\d{3})\)?[\.\-\/ ]?(\d{3})[\.\-\/ ]?(\d{4})$/;

		function submitIt(myForm)
		{
			errMsg = "";
			if(myForm.firstName.value == ""){
				errMsg = errMsg + "Please fill in your first name\n";
			}
			if(myForm.lastName.value == ""){
				errMsg = errMsg + "Please fill in your last name\n";
			}
			if(myForm.address.value == ""){
				errMsg = errMsg + "Please fill in your address\n";
			}
			if(myForm.city.value == ""){
				errMsg = errMsg + "Please fill in city\n";
			}
			if(myForm.country.value == ""){
				errMsg = errMsg + "Please fill in country\n";
			}
			if(myForm.code.value == ""){
				errMsg = errMsg + "Please fill in your postal code\n";
			}
			if (!regexp.test(myForm.phoneNumber.value)) {
				errMsg = errMsg + "Your phone number has been incorrectly formatted\nshould be in form 604-555-1212\n";
			}
			if (!re.test(myForm.email.value)) {
				errMsg = errMsg + "Your email address has been incorrectly formatted\nshould be in form name@server.com\n";
			}

			if(errMsg != ""){
				alert(errMsg);
				myForm.focus();
				return false;
			}
			return true;
		}
	 </SCRIPT>


</head>

<body>

	<%
		String src = "";
		String icon = (String)session.getAttribute("icon");
		if (icon.equals(null)) {
			session.setAttribute("icon", "seated");
			src = "images/Mammoth-Seated-icon.png";
		} else if(icon.equals("back")){
			//icon is back - set to seated
			session.setAttribute("icon", "seated");
			src = "images/Mammoth-Seated-icon.png";
		} else if(icon.equals("seated")) {
			//icon is seated - set to happy
			session.setAttribute("icon", "happy");
			src = "images/Mammoth-Happy-icon.png";
		} else {
			//set icon to back
			session.setAttribute("icon", "back");
			src = "images/Mammoth-Back-icon.png";
		}		
	
	
	%>
	
	<div id="wrapper">
		<div id="header">
			<img class="header_image" src=<%= src %> />
			<span class="header_text">Mammoth Address Book</span>
		</div>
		
		
		
		
		
		
		