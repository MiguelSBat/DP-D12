<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" name="actors" requestURI="${requestURI}" id="row">

	<spring:message code="actor.name" var="name" />
	<display:column title="${name}" sortable="false">
		<jstl:out value="${row.name}" />
	</display:column>

	<spring:message code="actor.email" var="email" />
	<display:column title="${email}" sortable="false">
		<jstl:out value="${row.emailAddress}" />
	</display:column>
	
	<security:authorize access="hasRole('MODERATOR')">
	<display:column>
	<a href="moderator/report/list.do?actorId=${row.id }"><spring:message code="actor.reports" /></a>
	</display:column>
	
	<display:column>
	<jstl:if test="${row.softBan=='false'}">
	<a href="moderator/actor/softBan.do?actorId=${row.id }"><spring:message code="actor.softBan" /></a>
		</jstl:if>
	</display:column>

	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column>
	<a href="administrator/report/list.do?actorId=${row.id }"><spring:message code="actor.reports" /></a>
	</display:column>
	
	<display:column>
	<jstl:if test="${row.softBan=='false'}">
	<a href="administrator/actor/softBan.do?actorId=${row.id }"><spring:message code="actor.softBan" /></a>
	</jstl:if>
	</display:column>
	
	</security:authorize>
	
	<security:authorize access="hasRole('MODERATOR')">
	
	<display:column>
	<jstl:if test="${row.hardBan=='false'}">
	<a href="moderator/actor/hardBan.do?actorId=${row.id }"><spring:message code="actor.hardBan" /></a>
	</jstl:if>
	</display:column>
	
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	
	<display:column>
	<jstl:if test="${row.hardBan=='false'}">
	<a href="administrator/actor/hardBan.do?actorId=${row.id }"><spring:message code="actor.hardBan" /></a>
	</jstl:if>
	</display:column>
	
	</security:authorize>
	
</display:table>


