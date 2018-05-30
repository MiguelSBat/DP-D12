<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
<!-- Listing grid -->

<b><spring:message code="administrator.avgSS"/></b>
<fmt:formatNumber value="${avgStockShop}" pattern="####0.00"/>
<br/>
<b><spring:message code="administrator.avgPE"/></b>
<fmt:formatNumber value="${avgPriceExp}" pattern="####0.00"/>
<br/><br/><br/>

<b><spring:message code="administrator.avgVU"/></b>
<fmt:formatNumber value="${avgValoUser}" pattern="####0.00"/>
<br/>
<b><spring:message code="administrator.avgVB"/></b>
<fmt:formatNumber value="${avgValoBusiness}" pattern="####0.00"/>
<br/><br/><br/>

<b><spring:message code="administrator.avgQS"/></b>
<fmt:formatNumber value="${avgQuestionsShop}" pattern="####0.00"/>
<br/>
<b><spring:message code="administrator.avgRB"/></b>
<fmt:formatNumber value="${avgReportBusiness}" pattern="####0.00"/>
<br/><br/><br/>

<b><spring:message code="administrator.avgRU"/></b>
<fmt:formatNumber value="${avgReportUsers}" pattern="####0.00"/>
<br/><br/><br/>

<b><spring:message code="administrator.topFS"/></b>
<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="topFiveSellers" requestURI="${requestURI}" id="row">
	
	<b><spring:message code="administrator.topFS" var="title"/></b>
	<display:column title="${title}">
		<a href="actor/display.do?actorId=${row.id}">${row.name}</a>
	</display:column>
	
</display:table>
<br/><br/><br/>

<b><spring:message code="administrator.topFB"/></b>
<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="topFiveBusiness" requestURI="${requestURI}" id="row">
	
	<b><spring:message code="administrator.topFB" var="title"/></b>
	<display:column title="${title}">
		<a href="actor/display.do?actorId=${row.id}">${row.name}</a>
	</display:column>
	
</display:table>
<br/><br/><br/>

<b><spring:message code="administrator.ratioUvsB"/></b>
<fmt:formatNumber value="${ratioUservsBusiness}" pattern="####0.00"/>

<br/><br/><br/>

</security:authorize>


	