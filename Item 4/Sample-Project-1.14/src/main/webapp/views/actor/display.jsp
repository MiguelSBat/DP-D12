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



<b><spring:message code="actor.name" /></b>
<jstl:out value="${actor.getName()}" />
<br />

<b><spring:message code="actor.email" /></b>
<jstl:out value="${actor.getEmailAddress()}" />
<br />
<b><spring:message code="actor.sum" /></b>
<jstl:out value="${sum}" />
<br />
<display:table pagesize="5" class="displaytag" name="info" requestURI="${requestURI}" id="row">

	<spring:message code="businessInfo.address" var="address" />
	<display:column title="${address}" sortable="false">
		<jstl:out value="${row.address}" />
	</display:column>

	<spring:message code="businessInfo.city" var="city" />
	<display:column title="${city}" sortable="true">
		<jstl:out value="${row.city}" />
	</display:column>
	
	<spring:message code="businessInfo.country" var="country" />
	<display:column title="${country}" sortable="false">
		<jstl:out value="${row.country}" />
	</display:column>

	<spring:message code="businessInfo.additionalInfo" var="country" />
	<display:column title="${additionalInfo}" sortable="false">
		<jstl:out value="${row.additionalInfo}" />
	</display:column>

	<

</display:table>

