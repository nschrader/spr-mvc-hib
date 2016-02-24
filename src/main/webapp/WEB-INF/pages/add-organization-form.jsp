<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><spring:message code="nav.organization.add"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap-theme.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-2.2.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
  </head>
  <body>
  <%@include file="navbar.jsp" %>
    <div class="jumbotron">
      <div class="container-fluid">
	<h1><spring:message code="nav.organization.add"/></h1>
	<p><spring:message code="aof.description"/></p>
	<p>${message}</p>
	<form:form method="post" commandName="organization" action="${pageContext.request.contextPath}/organization/add.html">
	  <table class="table">
	    <tbody>
	      <tr>
		<td><spring:message code="loo.table.c2"/>:</td>
		<td><form:input path="name" /></td>
	      </tr>
	      <tr>
		<td>
		  <input type="submit" value="<spring:message code="aof.submit"/>"/>
		</td>
		<td></td>
	      </tr>
	    </tbody>
	  </table>
	</form:form>
      </div>
    </div>
  </body>
</html>