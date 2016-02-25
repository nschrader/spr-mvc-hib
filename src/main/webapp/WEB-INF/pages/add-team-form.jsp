<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><spring:message code="nav.team.add"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap-theme.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-2.2.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
  </head>
  <body>
  <%@include file="navbar.jsp" %>
    <div class="jumbotron">
      <div class="container-fluid">
	<h1><spring:message code="nav.team.add"/></h1>
	<p><spring:message code="atf.description"/></p>
	<p>${message}</p>
	<form:form method="post" commandName="team" action="${pageContext.request.contextPath}/team/add.html" onSubmit="if (this.name.value == '' || this.rating.value == '') {return false;}">
	  <table class="table">
	    <tbody>
	      <tr>
		<td><spring:message code="lot.table.c2"/>:</td>
		<td><form:input path="name" /></td>
	      </tr>
	      <tr>
		<td><spring:message code="lot.table.c3"/>:</td>
		<td><form:input path="rating" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/></td>
	      </tr>
	      <tr>
		<td><spring:message code="lot.table.c4"/>:</td>
		<td>
		  <form:select path="organization">
		    <form:options items="${organizations}" itemValue="id" itemLabel="name" />
		  </form:select>
		</td>
	      </tr>
	      <tr>
		<td>
		  <input type="submit" value="<spring:message code="atf.submit"/>"/>
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