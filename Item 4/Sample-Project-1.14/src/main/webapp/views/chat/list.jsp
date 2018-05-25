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
<display:table pagesize="10" class="displaytag" 
	name="chats" requestURI="${requestURI }" id="row">
	
	
	
	<display:column >
	<jstl:if test="${row.sender.id!=principalId }">
	<jstl:out value="${row.text}" />
	</jstl:if>
	<jstl:if test="${row.sender.id==principalId }">
	
	</jstl:if>
	</display:column>
	
	
	
	<display:column >
	<jstl:if test="${row.sender.id==principalId }">
	<jstl:out value="${row.text}" />
	</jstl:if>
	<jstl:if test="${row.sender.id!=principalId }">
	</jstl:if>
	</display:column>
	
	
	

</display:table>
<form:form action="user/chat/edit.do?user2Id=${user2Id }" modelAttribute="chat">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="date" /> 
	<form:hidden path="sender" />
	<form:hidden path="receiver" />  
	
	<acme:textarea code="chat.text" path="text"/>
	
	<acme:submit name="save" code="chat.send"/>
	
	
</form:form>

