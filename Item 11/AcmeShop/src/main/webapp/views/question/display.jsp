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
<spring:message code="question.date" var="date" />




<div id="question">
	
	<h3><spring:message code="question" />: <jstl:out value="${question.text}"/></h3>	
	
	<spring:message code="question.business" />:
	<a href="actor/display.do?actorId=${question.shopAdvertisement.business.id}">
		<jstl:out value="${question.shopAdvertisement.business.name}"/>
	</a>
	<br />

	<display:table pagesize="5" class="displaytag" name="answers" requestURI="question/display.do" id="row">

		<display:column property="date" format="{0,date,${dateFormat}}" title="${date}" />

		<spring:message code="question.text" var="text" />
		<display:column property="text" title="${text}" />

	</display:table>




</div>
