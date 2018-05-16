<%--
 * display.jsp
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
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<b><spring:message code="config.VAT" /></b>
<jstl:out value="${config.VAT}" />
<br />

<b><spring:message code="config.englishWelcomeMessage" /></b>
<jstl:out value="${config.englishWelcomeMessage}" />
<br />

<b><spring:message code="config.spanishWelcomeMessage" /></b>
<jstl:out value="${config.spanishWelcomeMessage}" />
<br />

<b><spring:message code="config.userMaxAdvertisements" /></b>
<jstl:out value="${config.userMaxAdvertisements}" />
<br />

<b><spring:message code="config.businessMaxAdvertisements" /></b>
<jstl:out value="${config.businessMaxAdvertisements}" />
<br />

<b><spring:message code="config.premiumMaxAdvertisements" /></b>
<jstl:out value="${config.premiumMaxAdvertisements}" />
<br />

<b><spring:message code="config.advertisementExpirationMonths" /></b>
<jstl:out value="${config.advertisementExpirationMonths}" />
<br />

<b><spring:message code="config.premiumPrice" /></b>
<jstl:out value="${config.premiumPrice}" />
<br />

<b><spring:message code="config.paymentHoldDays" /></b>
<jstl:out value="${config.paymentHoldDays}" />
<br />

<b><spring:message code="config.transactionReportWeight" /></b>
<jstl:out value="${config.transactionReportWeight}" />
<br />

<b><spring:message code="config.reportWeightTreshold" /></b>
<jstl:out value="${config.reportWeightTreshold}" />
<br />

<b><spring:message code="config.reputationTreshold" /></b>
<jstl:out value="${config.reputationTreshold}" />
<br />


<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" name="config.spamWords"  requestURI="${requestURI}" id="row">

	<spring:message code="config.spam" var="titleHeader" />
	<display:column title="${titleHeader}"><jstl:out value="${row}"></jstl:out></display:column>
	
	<display:column ><a href="administrator/config/removeSpam.do?spamWord=${row }"><spring:message code="config.spam.remove" /></a></display:column>
	
	

</display:table>

<a href="administrator/config/addSpam.do"><spring:message code="config.spam.add"></spring:message></a>



