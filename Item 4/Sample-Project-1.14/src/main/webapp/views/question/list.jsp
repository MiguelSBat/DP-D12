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


<b><spring:message code="question.business"/>:</b>
<a href="actor/display.do?actorId=${business.id}">
	<jstl:out value="${business.name}"/>
</a>
<br>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" name="questions"
	requestURI="question/list.do" id="row">

	<spring:message code="question.user" var="user" />
	<display:column title="${user}">
		<a href="actor/display.do?actorId=${row.user.id}"><jstl:out
				value="${row.user.name}"></jstl:out></a>
	</display:column>

	<spring:message code="question.date" var="date" />
	<spring:message code="master.page.date.format" var="dateFormat" />
	<display:column property="date"
		format="{0,date,${dateFormat}}" title="${date}" />
	
	<spring:message code="question.text" var="text" />
	<display:column title="${text}">
		<jstl:out value="${row.text}"></jstl:out>
	</display:column>

	<spring:message code="question.answers" var="answers" />
	<display:column>
		<a href="question/display.do?questionId=${row.id}"><jstl:out
				value="${answers}"></jstl:out></a>
	</display:column>
	
	<security:authorize access="hasRole('BUSINESS')">
		<jstl:if test="${businessId!=null}">
			<spring:message code="question.answer" var="answer" />
			<display:column>
				<a href="answer/create.do?questionId=${row.id}">
					<jstl:out value="${answer}"></jstl:out></a>
			</display:column>
			
			<spring:message code="question.delete" var="delete" />
			<display:column>
				<a href="business/question/delete.do?questionId=${row.id}">
					<jstl:out value="${delete}"></jstl:out></a>
			</display:column>
		</jstl:if>
	</security:authorize>

</display:table>

<%-- <security:authorize access="hasRole('USER')">
	<a href="question/create.do?businessId=${business.id}"><spring:message
			code="question.create" /></a>
</security:authorize> --%>


