

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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="user/review/edit.do" modelAttribute="review">

	<form:hidden path="id" />
	<form:hidden path="version" /> 
	<form:hidden path="date"/>
	<form:hidden path="user"/>
	<form:hidden path="shopAdvertisement"/>
  
	<acme:textarea code="review.text" path="text"/>
	
	<acme:textbox code="review.score" path="score"/>
	

	
	<acme:submit name="save" code="review.save"/>
	
	<spring:message code="review.cancel" var="cancel"/>
	<input type="button" name="cancel" value="${cancel}" onclick="javascript:window.history.back()" />
	<br />	
	
</form:form>