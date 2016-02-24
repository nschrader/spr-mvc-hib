<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">spr-mvc-hib</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="<c:if test="${page.equals('home')}">active </c:if>">
	<a href="${pageContext.request.contextPath}/index.html"><spring:message code="nav.home"/></a>
      </li>
      <li class="<c:if test="${page.equals('organization')}">active </c:if>dopdown">
	<a data-toggle="dropdown" class="dropdown-toggle" href="#">
	  <spring:message code="nav.organization"/>
	  <b class="caret"></b>
	</a>
	<ul role="menu" class="dropdown-menu">
	  <li>
	    <a href="${pageContext.request.contextPath}/organization/add.html"><spring:message code="nav.organization.add"/></a>
	  </li>
	  <li>
	    <a href="${pageContext.request.contextPath}/organization/list.html"><spring:message code="nav.organization.list"/></a>
	  </li>
	</ul>
      </li>
      <li class="<c:if test="${page.equals('team')}">active </c:if>dopdown">
	<a data-toggle="dropdown" class="dropdown-toggle" href="#">
	  <spring:message code="nav.team"/>
	  <b class="caret"></b>
	</a>
	<ul role="menu" class="dropdown-menu">
	  <li>
	    <a href="${pageContext.request.contextPath}/team/add.html"><spring:message code="nav.team.add"/></a>
	  </li>
	  <li>
	    <a href="${pageContext.request.contextPath}/team/list.html"><spring:message code="nav.team.list"/></a>
	  </li>
	</ul>
      </li>
      <li class="<c:if test="${page.equals('member')}">active </c:if>dopdown">
	<a data-toggle="dropdown" class="dropdown-toggle" href="#">
	  <spring:message code="nav.member"/>
	  <b class="caret"></b>
	</a>
	<ul role="menu" class="dropdown-menu">
	  <li>
	    <a href="${pageContext.request.contextPath}/member/add.html"><spring:message code="nav.member.add"/></a>
	  </li>
	  <li>
	    <a href="${pageContext.request.contextPath}/member/list.html"><spring:message code="nav.member.list"/></a>
	  </li>
	</ul>
      </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li>
	<a href="?lang=en"><spring:message code="nav.language.en"/></a>
      </li>
      <li>
	<a href="?lang=de"><spring:message code="nav.language.de"/></a>
      </li>
    </ul>
  </div>
</nav>
