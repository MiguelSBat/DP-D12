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
<spring:message code="master.page.date.format" var="dateFormat" />
<display:table pagesize="5" class="displaytag" 
	name="tickets" requestURI="ticket/list.do" id="row">
	
	<jstl:if test="${listType=='myTickets' }">
	<spring:message code="ticket.seller" var="rowHeader" />
	<display:column title="${rowHeader}">
	<jstl:if test="${row.seller!=null }"><jstl:out value="${row.seller.name}"></jstl:out></jstl:if>
	<jstl:if test="${row.business!=null }"><jstl:out value="${row.business.name}"></jstl:out></jstl:if>
	</display:column>
	</jstl:if>
	
	<jstl:if test="${listType=='mySales' }">
	<spring:message code="ticket.buyer" var="rowHeader" />
	<display:column title="${rowHeader}"><jstl:out value="${row.user.name}"></jstl:out>
	</display:column>
	</jstl:if>
	
	<spring:message code="ticket.date" var="rowHeader" />
	<display:column title="${rowHeader}">
	<fmt:formatDate value="${row.date}" pattern="${dateFormat}" />
	</display:column>
	
	<spring:message code="ticket.status" var="rowHeader" />
	<display:column title="${rowHeader}">
	<jstl:if test="${row.status=='PENDING'}"><spring:message code="ticket.display.pending" /></jstl:if>
	<jstl:if test="${row.status=='RECEIVED'}"><spring:message code="ticket.display.received" /></jstl:if>
	<jstl:if test="${row.status=='SENT'}"><spring:message code="ticket.display.sent" /></jstl:if>
	<jstl:if test="${row.status=='CANCELED'}"><spring:message code="ticket.display.canceled" /></jstl:if>
	</display:column>
	
	<security:authorize access="hasRole('USER')">
	<display:column >
	<a href="user/ticket/display.do?ticketId=${row.id }"><spring:message code="ticket.view"/></a>
	</display:column>
	</security:authorize>
	<security:authorize access="hasRole('BUSINESS')">
	<display:column >
	<a href="business/ticket/display.do?ticketId=${row.id }"><spring:message code="ticket.view"/></a>
	</display:column>
	</security:authorize>
	
	

</display:table>


