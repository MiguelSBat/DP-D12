

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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="user/socialIdentity/edit.do" modelAttribute="socialIdentity">
	
	<form:hidden path="id" />
	<form:hidden path="version" /> 
  
	<acme:textbox code="socialIdentity.nick" path="nick"/>
	
	<acme:textbox code="socialIdentity.accountURL" path="accountURL"/>
	
	<acme:textbox code="socialIdentity.website" path="website"/>
	
	<acme:submit name="save" code="item.save"/>
	
	<acme:cancel url="/actor/display.do?actorId${principalId }" code="item.cancel"/>
	
</form:form>