<%--
 * action-2.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table pagesize="5" class="displaytag" 
	name="lines" requestURI="user/shoppingCart/view.do" id="row">

	<spring:message code="shoppingCart.name" var="itemName" />
	<display:column title="${itemName}">
		<a href="advertisement/display.do?advertisementId=${row.advertisement.id}"><jstl:out value="${row.advertisement.item.name}" /></a>
	</display:column>
	
	<spring:message code="shoppingCart.price" var="unitPrice" />
	<display:column title="${unitPrice}">
		<jstl:out value="${row.advertisement.price}" />
	</display:column>	
	
	<spring:message code="shoppingCart.amount" var="lineAmount" />
	<display:column title="${lineAmount}">
		<jstl:out value="${row.amount}" />
	</display:column>
	
	<spring:message code="shoppingCart.linePrice" var="linePrice" />
	<display:column title="${linePrice}">
		<jstl:out value="${row.amount * row.advertisement.price}" />
	</display:column>	
	
	<display:column title="${itemName}">
		<a href="user/shoppingCart/remove.do?saleLine=${row}"><spring:message code="shoppingCart.remove"/></a>
	</display:column>
	
</display:table>

<h3><spring:message code="shoppingCart.total"/> <jstl:out value="${total}" /></h3>
