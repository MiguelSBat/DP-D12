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


<!-- soy el edit de un shopAdvertisement si, no funciono obviamente modificame como veas  -->

<form:form action="shopAdvertisement/edit.do"
	modelAttribute="shopAdvertisement">



	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="publicationDate" />
	<br />

	<acme:textbox code="shopAdvertisement.endDate" path="endDate" />
	<acme:textarea code="shopAdvertisement.tag" path="tags" />
	<acme:textbox code="shopAdvertisement.startingPrice"
		path="startingPrice" />
	<acme:textbox code="shopAdvertisement.instantBuyPrice"
		path="instantBuyPrice" />

	<form:label path="item">
		<spring:message code="shopAdvertisement.item" />
	</form:label>
	<form:select path="item">
		<form:option label="-----" value="0" />
		<form:options items="${items}" itemLabel="name" itemValue="id" />
	</form:select>
	<form:errors path="item" />
	<br />

	<input type="checkbox" name="secret" id="draftMode" value="True">
	<spring:message code="shopAdvertisement.secret" />
	<br />

	<%-- 	<form:label path="secret">
		<spring:message code="shopAdvertisement.secret" />:
	</form:label>
	<form:select path="secret">
		<option value="">--</option>
		<option value="true">
			<spring:message code="shopAdvertisement.secret" />
		</option>
		<option value="false">
			<spring:message code="shopAdvertisement.nonSecret" />
		</option>
	</form:select> --%>

	<acme:submit name="save" code="shopAdvertisement.save" />

	<!-- El bueno -->
	<acme:cancel url="shopAdvertisement/myList.do"
		code="shopAdvertisement.cancel" />

</form:form>

