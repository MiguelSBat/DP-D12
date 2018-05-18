<%--
 * edit.jsp
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
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="expressAdvertisement/edit.do" modelAttribute="expressAdvertisement">



	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="publicationDate" />
	<br />
	
	<spring:message code="advertisement.endDate" var="endDate"/>
	<form:label path="endDate">${endDate}</form:label>
	<form:input path="endDate" placeholder="dd/mm/yyyy HH:MM"/>
	<form:errors cssClass="error" path="endDate"/>
	<br />
	<acme:textarea code="advertisement.tags" path="tags" />
	<acme:textbox code="advertisement.price" path="price" />
	
	
	<form:label path="item">
		<spring:message code="advertisement.item" />
	</form:label>
	<form:select path="item">
		<form:option label="-----" value="0" />
		<form:options items="${items}" itemLabel="name" itemValue="id" />
	</form:select>
	<form:errors path="item"/>
	<br />

	<acme:submit name="save" code="advertisement.save" />


	<acme:cancel url="expressAdvertisement/MyList.do" code="advertisement.cancel" />

<%-- 	<jstl:out value="${message}"></jstl:out>
 --%>
</form:form>
