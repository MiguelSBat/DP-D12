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
<display:table pagesize="5" class="displaytag" name="businesses" requestURI="${requestURI}" id="row">

	<spring:message code="business.name" var="name" />
	<display:column title="${name}" sortable="false">
		<jstl:out value="${row.name}" />
	</display:column>

	<spring:message code="business.paypalEmail" var="paypalEmail" />
	<display:column title="${paypalEmail}" sortable="false">
		<jstl:out value="${row.paypalEmail}" />
	</display:column>

	<spring:message code="business.VATNumber" var="VATNumber" />
	<display:column title="${VATNumber}" sortable="false">
		<jstl:out value="${row.VATNumber}" />
	</display:column>
	<security:authorize access="hasRole('ADMIN')">
	<display:column >
	<a href="administrator/business/verify.do?businessId=${row.id}"><spring:message code="business.verify" /></a>
	</display:column>
	
	</security:authorize>
	
	<security:authorize access="hasRole('MODERATOR')">
	<display:column >
	<a href="moderator/business/verify.do?businessId=${row.id}"><spring:message code="business.verify" /></a>
	</display:column>
	
	</security:authorize>
</display:table>


