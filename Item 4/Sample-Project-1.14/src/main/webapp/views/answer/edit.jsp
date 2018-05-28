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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="business/answer/edit.do"
	modelAttribute="answer">


	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="date" />
	<form:hidden path="question" />
	<br />

	
	<acme:textarea code="answer.text" path="text" />
	
	<acme:submit name="save" code="answer.save" />

	<!-- El bueno -->
	<acme:cancel url="question/list.do?businessId=${answer.question.shopAdvertisement.business.id}"
		code="answer.cancel" />

</form:form>

