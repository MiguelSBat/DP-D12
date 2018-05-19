

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

<security:authorize access="hasRole('USER')"><form:form action="user/shippingInfo/edit.do" modelAttribute="shippingInfo">

	
	<form:hidden path="id" />
	<form:hidden path="version" /> 
	<form:hidden path="ticket"/>
  
	<acme:textbox code="shippingInfo.trackingNumber" path="trackingNumber"/>
	
	<acme:textarea code="shippingInfo.company" path="company"/>
	
	<acme:textbox code="shippingInfo.additionalInfo" path="additionalInfo"/>
	
	<acme:submit name="save" code="shippingInfo.save"/>
	
	<acme:cancel url="/welcome.do" code="shippingInfo.cancel"/>
	
</form:form>
</security:authorize>
	<security:authorize access="hasRole('BUSINESS')"><form:form action="business/shippingInfo/edit.do" modelAttribute="shippingInfo">
	
		<form:hidden path="id" />
	<form:hidden path="version" /> 
	<form:hidden path="ticket"/>
  
	<acme:textbox code="shippingInfo.trackingNumber" path="trackingNumber"/>
	
	<acme:textarea code="shippingInfo.company" path="company"/>
	
	<acme:textbox code="shippingInfo.additionalInfo" path="additionalInfo"/>
	
	<acme:submit name="save" code="shippingInfo.save"/>
	
	<acme:cancel url="/welcome.do" code="shippingInfo.cancel"/>
	
</form:form>
	</security:authorize>