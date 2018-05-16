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


	<!-- soy el display de un advertisement si, no funciono obviamente modificame como veas  -->


	<!-- Guardamos en una variable el formato de la fecha  -->
	

	<spring:message code="master.page.date.format" var="dateFormat" />
	
	
 	<fmt:setLocale value="${locale}"/>
	
	
<div id="advertisement">

	<br />

	<ul style="list-style-type: disc">
	<h1><spring:message code="advertisement.type"></spring:message>:  <jstl:out value="${type}"></jstl:out></h1>

	<li><b><spring:message code="advertisement.item"></spring:message>:</b>
			<jstl:out value="${advertisement.getItem().getName()}" /></li>

	<li><b><spring:message code="advertisement.endDate"></spring:message>:</b> 
			<fmt:formatDate value="${advertisement.getEndDate()}" pattern="${dateFormat}" /></li>

	<li><b><spring:message code="advertisement.publicationDate"></spring:message>:</b> 
			<fmt:formatDate value="${advertisement.getPublicationDate()}" pattern="${dateFormat}" /></li>
			
	<li><b><spring:message code="advertisement.price"></spring:message>:</b> 
			<jstl:out value="${advertisement.getPrice()}" /></li>
	<jstl:if test="${type.equals('shop')}">
	<li><b><spring:message code="advertisement.stock"></spring:message>:</b> 
			<jstl:out value="${advertisement.getStock()}" /></li>
	
	</jstl:if>
	<jstl:if test="${type.equals('auction')}">
	<li><b><spring:message code="advertisement.startingPrice"></spring:message>:</b> 
			<jstl:out value="${advertisement.getStartingPrice()}" /></li>
	<li><b><spring:message code="advertisement.instantBuyPrice"></spring:message>:</b> 
			<jstl:out value="${advertisement.getInstantBuyPrice()}" /></li>
	
	</jstl:if>
	
<input type="button" name="cancel"
	value="<spring:message code="advertisement.back" />"
	onclick="javascript: relativeRedir('advertisement/list.do')" />
	
