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


<form:form action="businessInfo/edit.do" modelAttribute="businessInfo">



	<form:hidden path="id" />
	<form:hidden path="version" />
	

	<br />
	
	
	<acme:textbox code="businessInfo.address" path="address" />
	<acme:textbox code="businessInfo.city" path="city" />
	<acme:textbox code="businessInfo.country" path="country" />
	
	<acme:textarea code="businessInfo.additionalInfo" path="additionalInfo" />
	
	

	<acme:submit name="save" code="advertisement.save" />


	<acme:cancel url="/" code="advertisement.cancel" />


</form:form>
