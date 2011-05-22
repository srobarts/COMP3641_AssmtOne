<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles.css" >
<title>Java Address Book</title>
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
		
		
		
		
		
		
		