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
<display:table pagesize="5" class="displaytag" name="items" requestURI="${requestURI}" id="row">

	<spring:message code="item.name" var="name" />
	<display:column title="${name}" sortable="false">
		<jstl:out value="${row.name}" />
	</display:column>

	<spring:message code="item.description" var="description" />
	<display:column title="${description}" sortable="false">
		<jstl:out value="${row.description}" />
	</display:column>

	

	<spring:message code="item.photo" var="photo" />
	<display:column title="${photo}">
		<img src="${row.photo}" height="200" width="300" />
	</display:column>

</display:table>

<security:authorize access="hasRole('USER')">
<a href="user/item/create.do"> 
		<spring:message code="item.create" />
</a>
</security:authorize>

<security:authorize access="hasRole('BUSINESS')">
<a href="business/item/create.do"> 
		<spring:message code="item.create" />
</a>
</security:authorize>


