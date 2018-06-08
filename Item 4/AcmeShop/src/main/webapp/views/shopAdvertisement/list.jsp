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

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" name="advertisements"
	requestURI="${requestURI}"  id="row">

	<spring:message code="advertisement.item" var="itemHeader" />
	<display:column title="${itemHeader}">
		<a href="advertisement/display.do?advertisementId=${row.getId()}"><jstl:out
				value="${row.item.getName()}"></jstl:out></a>
	</display:column>

	<spring:message code="advertisement.publicationDate"
		var="publicationDateHeader" />
	<spring:message code="master.page.date.format" var="dateFormat" />
	<display:column property="publicationDate"
		format="{0,date,${dateFormat}}" title="${publicationDateHeader}" />

	<spring:message code="advertisement.endDate" var="endDateHeader" />
	<spring:message code="master.page.date.format" var="dateFormat" />
	<display:column property="endDate" format="{0,date,${dateFormat}}"
		title="${endDateHeader}" />

	<spring:message code="advertisement.price" var="priceHeader" />
		<display:column property="price" title="${priceHeader}" />
		
	<spring:message code="advertisement.stock" var="stockHeader" />
		<display:column property="stock" title="${stockHeader}" />

	<spring:message code="advertisement.tags" var="tagsHeader" />
	<display:column property="tags" title="${tagsHeader}" />
	
	
	<display:column >
	<jsp:useBean id="now" class="java.util.Date"/>
	<jstl:if test="${row.getEndDate() > now && row.getBusiness().getId() == aID}">
		<a href="shopAdvertisement/delete.do?shopAdvertisementId=${row.id}"><spring:message code="advertisement.delete"></spring:message></a>
	
	</jstl:if>
	</display:column>
	
	

</display:table>
<security:authorize access="hasAnyRole('BUSINESS')">
	<a href="shopAdvertisement/create.do"><spring:message
			code="shopAdvertisement.create" /></a>
</security:authorize>


