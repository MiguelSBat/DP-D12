<%--
 * action-1.jsp
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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:if test="${actorForm.authority == 'BUSINESS'}">
<strong><spring:message code="actor.businessConfirmation" /></strong>
</jstl:if>

<form:form action="actor/register.do" modelAttribute="actorForm">
	<form:hidden path="authority" />

	<form:label path="username">
		<spring:message code="actor.username" />
	</form:label>
	<form:input path="username" />
	<form:errors cssClass="error" path="username" />
	<br />
	<form:label path="password">
		<spring:message code="actor.password" />
	</form:label>
	<form:input type="password" path="password" />
	<form:errors cssClass="error" path="password" />
	<br />
	<form:label path="password2">
		<spring:message code="actor.password2" />
	</form:label>
	<form:input type="password" path="password2" />
	<form:errors cssClass="error" path="password2" />
	<br />	
	<form:label path="email">
		<spring:message code="actor.email" />
	</form:label>
	<form:input type="email" path="email" />
	<form:errors cssClass="error" path="email" />
	<br />	
	<form:label path="agree">
		<spring:message code="actor.agree" />
	</form:label>
	<form:checkbox path="agree" />
	<form:errors cssClass="error" path="agree" />
	<br />
	
	<jstl:if test="${actorForm.authority == 'BUSINESS'}">
		<form:label path="name">
			<spring:message code="actor.name" />
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />
		<br />
		<form:label path="paypalEmail">
			<spring:message code="actor.email" />
		</form:label>
		<form:input type="email" path="paypalEmail" />
		<form:errors cssClass="error" path="paypalEmail" />
		<br />	
		<form:label path="VATNumber">
			<spring:message code="actor.vatNumber" />
		</form:label>
		<form:input path="VATNumber" />
		<form:errors cssClass="error" path="VATNumber" />
		<br />
	</jstl:if>
	
	<jstl:if test="${actorForm.authority == 'USER'}">
		<form:label path="name">
			<spring:message code="actor.name" />
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />
		<br />
		<form:label path="surname">
			<spring:message code="actor.surname" />
		</form:label>
		<form:input path="surname" />
		<form:errors cssClass="error" path="surname" />
		<br />
		<form:label path="phone">
			<spring:message code="actor.phone" />
		</form:label>
		<form:input path="phone" />
		<form:errors cssClass="error" path="phone" />
		<br />
	</jstl:if>
	
	
	<input type="submit" name="save"
		value="<spring:message code="actor.save" />" />
	
	<spring:message code="actor.cancel" var="cancel"/>
	<input type="button" name="cancel" value="${cancel}" onclick="javascript:relativeRedir('welcome/index.do');" />
</form:form>

