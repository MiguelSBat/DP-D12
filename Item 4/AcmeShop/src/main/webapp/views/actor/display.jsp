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

<security:authorize access="hasRole('USER')">
	
	<jstl:if test="${actor.getId()!=principalId }"><a href="report/create.do?actorId=${actor.id }"><spring:message code="actor.report" /></a></jstl:if>
	
</security:authorize>
<security:authorize access="hasRole('BUSINESS')">

	<jstl:if test="${actor.getId()!=principalId }"><a href="report/create.do?actorId=${actor.id }"><spring:message code="actor.report" /></a></jstl:if>
	
</security:authorize>


<security:authorize access="isAuthenticated()">
<br/>
<jstl:if test="${actor.getId()!=principalId }"><a href='chat/list.do?user2Id=${actor.id }'><b><spring:message code="actor.openChat" /></b></a></jstl:if>
</security:authorize>

<jstl:if test="${esBusiness}">
<display:table pagesize="5" class="displaytag" name="info"
	requestURI="${requestURI}" id="row">

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
	<jstl:if test="${puedeEditarBInfo}">
	<display:column>
		<a href="businessInfo/edit.do?businessInfoId=${row.id}" ><spring:message code="businessInfo.edit"></spring:message></a>
	</display:column>
	</jstl:if>
</display:table>
</jstl:if>
<br>
<jstl:if test="${!esBusiness }">
<b><spring:message code="actor.socialIdentities" />:</b>
<display:table pagesize="5" class="displaytag" name="socialIdentities"
	requestURI="${requestURI}" id="row">
	<spring:message code="actor.socialIdentity.nick" var="nick" />
	<display:column title="${nick}" sortable="false">
	<!-- DONE: Se muestra el accountURL en vez del nick // Arreglado-->
		<jstl:out value="${row.nick}" />
	</display:column>
	<spring:message code="actor.socialIdentity.accountURL" var="accountURL" />
	<display:column title="${accountURL}" sortable="false">
		<a href="<jstl:url value="${row.accountURL }" />"><jstl:out value="${row.accountURL}" /></a>
	</display:column>
	<spring:message code="actor.socialIdentity.website" var="website" />
	<display:column title="${website}" sortable="false">
	<jstl:out value="${row.website}" />
	</display:column>
	<jstl:if test="${principalId==actor.id }">
	<display:column>
	<a href="user/socialIdentity/edit.do?socialIdentityId=${row.id }"><spring:message code="socialIdentity.edit" /></a>
	</display:column>
	<display:column>
	<a href="user/socialIdentity/delete.do?socialIdentityId=${row.id }"><spring:message code="socialIdentity.remove" /></a>
	</display:column>
	</jstl:if>
</display:table>
<jstl:if test="${principalId==actor.id }">
<a href="user/socialIdentity/create.do"><spring:message code="actor.socialIdentity.add" /></a>
</jstl:if>
</jstl:if>

