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

<spring:message code="master.page.date.format" var="dateFormat" />

<b><spring:message code="ticket.date"></spring:message>:</b>
			<fmt:formatDate value="${ticket.date}" pattern="${dateFormat}" />
<br />
<b><spring:message code="ticket.status"></spring:message>:</b>
			<jstl:if test="${ticket.status=='PENDING'}"><spring:message code="ticket.display.pending" /></jstl:if>
	<jstl:if test="${ticket.status=='RECEIVED'}"><spring:message code="ticket.display.received" /></jstl:if>
	<jstl:if test="${ticket.status=='SENT'}"><spring:message code="ticket.display.sent" /></jstl:if>
	<jstl:if test="${ticket.status=='CANCELED'}"><spring:message code="ticket.display.canceled" /></jstl:if>
<br />
<b><spring:message code="ticket.seller"></spring:message>:</b>
			<jstl:if test="${ticket.seller!=null }"><jstl:out value="${ticket.seller.name}"/></jstl:if>
	<jstl:if test="${ticket.business!=null }"><jstl:out value="${ticket.business.name}"/></jstl:if>
<br />
<b><spring:message code="ticket.buyer"></spring:message>:</b>
			<jstl:out value="${ticket.user.name}"/>
<br />

<display:table pagesize="5" class="displaytag" 
	name="saleLines" requestURI="ticket/display.do" id="row">
	
	</display:table>