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
<jstl:if test="${ticket.status=='SENT'&&ticket.user.id==principal.id}">
<b><spring:message code="ticket.display.changeStatus"></spring:message>:</b>
<a href="user/ticket/status.do?status=RECEIVED&ticketId=${ticket.id }">
<spring:message code="ticket.display.received"/>
</a>
<br />
</jstl:if>
<jstl:if test="${ticket.status=='PENDING'&&(ticket.seller.id==principal.id||ticket.business.id==principal.id)}">
<b><spring:message code="ticket.display.changeStatus"></spring:message>:</b>
<a href="business/ticket/status.do?status=SENT&ticketId=${ticket.id }">
<spring:message code="ticket.display.sent"/>
</a>
<a href="business/ticket/status.do?status=CANCELED&ticketId=${ticket.id }">
<spring:message code="ticket.display.canceled"/>
</a>
<br />
</jstl:if>

<b><spring:message code="ticket.seller"></spring:message>:</b>
			<jstl:if test="${ticket.seller!=null }"><jstl:out value="${ticket.seller.name}"/></jstl:if>
	<jstl:if test="${ticket.business!=null }"><jstl:out value="${ticket.business.name}"/></jstl:if>
<br />
<b><spring:message code="ticket.buyer"></spring:message>:</b>
			<jstl:out value="${ticket.user.name}"/>
<br />
<br />
<!-- SHIPMENT ADDRESS -->
<h1><spring:message code="ticket.display.shipmentAddress"/></h1>
<b><spring:message code="ticket.display.country"></spring:message>:</b>
			<jstl:out value="${shipmentAddress.country}"/>
<br />
<b><spring:message code="ticket.display.city"></spring:message>:</b>
			<jstl:out value="${shipmentAddress.city}"/>
<br />
<b><spring:message code="ticket.display.postalCode"></spring:message>:</b>
			<jstl:out value="${shipmentAddress.postalCode}"/>
<br />
<b><spring:message code="ticket.display.address"></spring:message>:</b>
			<jstl:out value="${shipmentAddress.address}"/>
<br />
<br />
<!-- FACTURATION DATA -->
<h1><spring:message code="ticket.display.facturationData"/></h1>
<b><spring:message code="ticket.display.name"></spring:message>:</b>
			<jstl:out value="${facturationData.name}"/>
<br />
<b><spring:message code="ticket.display.surname"></spring:message>:</b>
			<jstl:out value="${facturationData.surname}"/>
<br />
<b><spring:message code="ticket.display.IDNumber"></spring:message>:</b>
			<jstl:out value="${facturationData.IDNumber}"/>
<br />
<b><spring:message code="ticket.display.country"></spring:message>:</b>
			<jstl:out value="${facturationData.country}"/>
<br />
<b><spring:message code="ticket.display.city"></spring:message>:</b>
			<jstl:out value="${facturationData.city}"/>
<br />
<b><spring:message code="ticket.display.postalCode"></spring:message>:</b>
			<jstl:out value="${facturationData.postalCode}"/>
<br />
<br />

<!-- SHIPPING INFO -->
<jstl:if test="${shippingInfo!=null }">
<h1><spring:message code="ticket.display.shippingInfo"/></h1>
<b><spring:message code="ticket.display.trackingNumber"></spring:message>:</b>
			<jstl:out value="${shippingInfo.trackingNumber}"/>
<br />
<b><spring:message code="ticket.display.company"></spring:message>:</b>
			<jstl:out value="${shippingInfo.company}"/>
<br />
<b><spring:message code="ticket.display.additionalInfo"></spring:message>:</b>
			<jstl:out value="${shippingInfo.additionalInfo}"/>
<br />

</jstl:if>
<jstl:if test="${shippingInfo==null && ticket.business.id==principal.id}">
<a href="business/shippingInfo/create.do?ticketId=${ticket.id }">
<spring:message code="ticket.shippingInfo"></spring:message>
</a>
</jstl:if>
<jstl:if test="${shippingInfo==null && ticket.seller.id==principal.id}">
<a href="user/shippingInfo/create.do?ticketId=${ticket.id }">
<spring:message code="ticket.shippingInfo"></spring:message>
</a>
</jstl:if>
<display:table pagesize="5" class="displaytag" 
	name="saleLines" requestURI="ticket/display.do" id="row">
	
	<spring:message code="ticket.display.name" var="rowHeader" />
	<display:column title="${rowHeader}">
	<a href="advertisement/display.do?advertisementId=${row.id }"><jstl:out value="${row.advertisement.item.name }"></jstl:out></a>
	</display:column>
	
	
	<spring:message code="ticket.display.amount" var="rowHeader" />
	<display:column title="${rowHeader}">
	<jstl:out value="${row.amount }"></jstl:out>
	</display:column>
	
	</display:table>