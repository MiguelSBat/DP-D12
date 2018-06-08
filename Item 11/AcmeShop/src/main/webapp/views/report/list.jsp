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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<!-- Listing grid -->
<spring:message code="master.page.date.format" var="dateFormat" />
<display:table pagesize="5" class="displaytag" 
	name="reports" requestURI="${requestURI }" id="row">
	
	<spring:message code="actor.name" var="name" />
	<display:column title="${name}" sortable="false">
		<a href="actor/display.do?actorId=${row.actor.id }"><jstl:out value="${row.actor.name}" /></a>
	</display:column>
	
	<spring:message code="actor.weight" var="weight" />
	<display:column title="${weight}" sortable="false">
		<jstl:out value="${row.weight}" />
	</display:column>
	<spring:message code="actor.weight" var="weight" />
	
	<spring:message code="actor.text" var="text" />
	<display:column title="${text}" sortable="false">
		<jstl:out value="${row.text}" />
	</display:column>	
	

</display:table>


