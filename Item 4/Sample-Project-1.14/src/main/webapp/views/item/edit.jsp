

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

<security:authorize access="hasRole('USER')"><form:form action="user/item/edit.do" modelAttribute="item">
	
	<form:hidden path="id" />
	<form:hidden path="version" /> 
  
	<acme:textbox code="item.name" path="name"/>
	
	<acme:textarea code="item.description" path="description"/>
	
	<acme:textbox code="item.photo" path="photo"/>
	
	<acme:submit name="save" code="item.save"/>
	
	<acme:cancel url="/user/item/list.do" code="item.cancel"/>
	
</form:form>
</security:authorize>
<security:authorize access="hasRole('BUSINESS')"><form:form action="business/item/edit.do" modelAttribute="item">
	
	<form:hidden path="id" />
	<form:hidden path="version" /> 
  
	<acme:textbox code="item.name" path="name"/>
	
	<acme:textarea code="item.description" path="description"/>
	
	<acme:textbox code="item.photo" path="photo"/>
	
	<acme:submit name="save" code="item.save"/>
	
	<acme:cancel url="/business/item/list.do" code="item.cancel"/>
	
</form:form>
</security:authorize>	