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



<!-- search  -->

<form action="advertisement/list.do" method="get">





	<input name="criteria" /> <br /> <input type="submit" value="search" />

</form>


<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" 
	name="advertisements" requestURI="advertisement/list.do" id="row">

	<spring:message code="advertisement.item" var="itemHeader" />
	<display:column title="${itemHeader}">
		<a href="advertisement/display.do?advertisementId=${row.id}"><jstl:out
				value="${row.title}"></jstl:out></a>
	</display:column>

	<spring:message code="advertisement.business" var="businessHeader" />
	<display:column property="business" title="${businessHeader}" />

	<spring:message code="advertisement.publicationDate"
		var="publicationDateHeader" />
	<spring:message code="master.page.date.format" var="dateFormat" />
	<display:column property="publicationDate"
		format="{0,date,${dateFormat}}" title="${publicationDateHeader}" />

<spring:message code="advertisement.endDate"
		var="endDateHeader" />
	<spring:message code="master.page.date.format" var="dateFormat" />
	<display:column property="endDateDate"
		format="{0,date,${dateFormat}}" title="${endDateHeader}" />

	<spring:message code="advertisement.price" var="priceHeader" />
		<display:column property="price" title="${priceHeader}" />


	<spring:message code="advertisement.picture" var="pictureHeader" />
	<display:column title="${pictureHeader}">
		<img src="${row.picture}" height="150" width="250" />
	</display:column>
	
	<security:authorize access="hasRole('CUSTOMER')">
		<display:column sortable="false">
			<jstl:if test="${row.publicity==true && !advertisementscustomer.contains(row)}">
				<a href="customer/subscribe.do?advertisementId=${row.id}"><spring:message
						code="advertisement.subscribe" /></a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('AGENT')">
		<display:column sortable="false">
				<a href="agent/advertisement/create.do?advertisementId=${row.id}"><spring:message
						code="advertisement.advertisement" /></a>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="administrator/advertisement/delete.do?advertisementId=${row.id}"><spring:message
					code="chirp.delete" /></a>
		</display:column>
	</security:authorize>
</display:table>


