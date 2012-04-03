<!-- ------------------------------------------------------ -->
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="vigionline.common.lang.MessageLocator"%>
<%
        String baseUrl = request.getContextPath();
		String lang = "", country = "";
		for( Cookie c : request.getCookies() )
		{
			if( c.getName().equals("lang") ) { lang = c.getValue();}
			else if ( c.getName().equals("country") ) { country = c.getValue();}
		}
		MessageLocator messages = new MessageLocator(lang, country);
%>
<!-- ------------------------------------------------------ -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-latest.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-dropdown.js"></script>
<title>Vigionline</title>
</head>
<body>
<%@ include file="menu.jsp"%>
<div class="container">
<ul class="breadcrumb">
		<li>
	    	<a href="${pageContext.request.contextPath}">Home</a> <span class="divider">/</span>
	  	</li>
	  </ul>
<div class="hero-unit">
	