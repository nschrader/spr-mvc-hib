<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><spring:message code="nav.organization.list"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap-theme.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-2.2.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
  </head>
  <body>
    <%@include file="navbar.jsp" %>
    <div class="jumbotron">
      <div class="container-fluid">
	<h1><spring:message code="nav.organization.list"/></h1>
	<p><spring:message code="loo.description"/></p>
	<p>${message}</p>
	<table class="table" >
	  <thead>
	    <tr>
	      <th width="10%"><spring:message code="loo.table.c1"/></th>
	      <th width="15%"><spring:message code="loo.table.c2"/></th>
	      <th width="10%"><spring:message code="loo.table.c3"/></th>
	    </tr>
	  </thead>
	  <tbody>
	    <c:forEach var="organization" items="${organizations}">
	      <tr>
		<td>${organization.id}</td>
		<td>${organization.name}</td>
		<td>
		  <a class="edit" href="${pageContext.request.contextPath}/organization/edit/${organization.id}.html">
		    <spring:message code="nav.organization.edit"/>
		  </a>
		  <br/>
		  <a class="delete" href="${pageContext.request.contextPath}/organization/delete/${organization.id}.html">
		    <spring:message code="loo.table.delete"/>
		  </a>
		  <br/>
		</td>
	      </tr>
	    </c:forEach>
	  </tbody>
	</table>
      </div>
    </div>
  </body>
</html>