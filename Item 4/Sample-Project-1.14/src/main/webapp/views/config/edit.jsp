

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

<form:form action="administrator/config/edit.do" modelAttribute="config">
	
	<form:hidden path="id" />
	<form:hidden path="version" /> 
	<form:hidden path="spamWords"/>
	
	<form:label path="VAT">
	<spring:message code="config.VAT"/>	
	</form:label>
	<form:input path="VAT" />
	<form:errors cssClass="error" path="VAT" />
	<br />
	
	<acme:textbox code="config.englishWelcomeMessage" path="englishWelcomeMessage"/>
	
	<acme:textbox code="config.spanishWelcomeMessage" path="spanishWelcomeMessage"/>
	
	<form:label path="userMaxAdvertisements">
	<spring:message code="config.userMaxAdvertisements"/>	
	</form:label>
	<form:input path="userMaxAdvertisements" />
	<form:errors cssClass="error" path="userMaxAdvertisements" />
	<br />
	
	<form:label path="businessMaxAdvertisements">
	<spring:message code="config.businessMaxAdvertisements"/>	
	</form:label>
	<form:input path="businessMaxAdvertisements" />
	<form:errors cssClass="error" path="businessMaxAdvertisements" />
	<br />

	<form:label path="premiumMaxAdvertisements">
	<spring:message code="config.premiumMaxAdvertisements"/>	
	</form:label>
	<form:input path="premiumMaxAdvertisements" />
	<form:errors cssClass="error" path="premiumMaxAdvertisements" />
	<br />
	
	<form:label path="advertisementExpirationMonths">
	<spring:message code="config.advertisementExpirationMonths"/>	
	</form:label>
	<form:input path="advertisementExpirationMonths" />
	<form:errors cssClass="error" path="advertisementExpirationMonths" />
	<br />
	
	<form:label path="premiumPrice">
	<spring:message code="config.premiumPrice"/>	
	</form:label>
	<form:input path="premiumPrice" />
	<form:errors cssClass="error" path="premiumPrice" />
	<br />
	
	<form:label path="transactionReportWeight">
	<spring:message code="config.transactionReportWeight"/>	
	</form:label>
	<form:input path="transactionReportWeight" />
	<form:errors cssClass="error" path="transactionReportWeight" />
	<br />
	
	<form:label path="reportWeightTreshold">
	<spring:message code="config.reportWeightTreshold"/>	
	</form:label>
	<form:input path="reportWeightTreshold" />
	<form:errors cssClass="error" path="reportWeightTreshold" />
	<br />
	
	<form:label path="reputationTreshold">
	<spring:message code="config.reputationTreshold"/>	
	</form:label>
	<form:input path="reputationTreshold" />
	<form:errors cssClass="error" path="reputationTreshold" />
	<br />
	
	<acme:submit name="save" code="config.save"/>
	
	<acme:cancel url="administrator/config/display.do" code="config.cancel"/>
	
</form:form>
	