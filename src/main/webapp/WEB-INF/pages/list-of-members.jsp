<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><spring:message code="nav.member.list"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap-theme.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-2.2.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
  </head>
  <body>
    <%@include file="navbar.jsp" %>
    <div class="jumbotron">
      <div class="container-fluid">
	<h1><spring:message code="nav.member.list"/></h1>
	<p><spring:message code="lom.description"/></p>
	<p>${message}</p>
	<table class="table" >
	  <thead>
	    <tr>
	      <th width="10%"><spring:message code="lom.table.c1"/></th>
	      <th width="15%"><spring:message code="lom.table.c2"/></th>
	      <th width="10%"><spring:message code="lom.table.c3"/></th>
	      <th width="10%"><spring:message code="lom.table.c4"/></th>
	    </tr>
	  </thead>
	  <tbody>
	    <c:forEach var="member" items="${members}">
	      <tr>
		<td>${member.id}</td>
		<td>${member.name}</td>
		<td>
		  <c:forEach var="team" items="${member.teams}">
		    ${team.name}<br/>
		  </c:forEach>
		</td>
		<td>
		  <a href="${pageContext.request.contextPath}/member/edit/${member.id}.html">
		    <spring:message code="nav.member.edit"/>
		  </a>
		  <br/>
		  <a href="${pageContext.request.contextPath}/member/delete/${member.id}.html">
		    <spring:message code="lom.table.delete"/>
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