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


<!-- soy el display de un advertisement si, no funciono obviamente modificame como veas  -->


<!-- Guardamos en una variable el formato de la fecha  -->


<spring:message code="master.page.date.format" var="dateFormat" />


<fmt:setLocale value="${locale}" />


<div id="advertisement">

	<br />

	<ul style="list-style-type: disc">
		<h1>
			<spring:message code="advertisement.type"></spring:message>
			:
			<jstl:out value="${type}"></jstl:out>
		</h1>
	<a href="actor/display.do?actorId=${advertisement.getUser().getId()}">${advertisement.getUser().getName()}</a>

		<li><b><spring:message code="advertisement.item"></spring:message>:</b>
			<jstl:out value="${advertisement.getItem().getName()}" /></li>

		<li><b><spring:message code="advertisement.endDate"></spring:message>:</b>
			<fmt:formatDate value="${advertisement.getEndDate()}"
				pattern="${dateFormat}" /></li>

		<li><b><spring:message code="advertisement.publicationDate"></spring:message>:</b>
			<fmt:formatDate value="${advertisement.getPublicationDate()}"
				pattern="${dateFormat}" /></li>

		<jstl:if test="${type.equals('shop')}">
			<li><b><spring:message code="advertisement.stock"></spring:message>:</b>
				<jstl:out value="${advertisement.getStock()}" /></li>
			<li><b><spring:message code="advertisement.price"></spring:message>:</b>
				<jstl:out value="${advertisement.price}" /></li>

		</jstl:if>
		<jstl:if test="${type.equals('express')}">
			<li><b><spring:message code="advertisement.price"></spring:message>:</b>
				<jstl:out value="${advertisement.price}" /></li>

		</jstl:if>
		<jstl:if test="${type.equals('auction')}">
			<li><b><spring:message code="advertisement.startingPrice"></spring:message>:</b>
				<jstl:out value="${advertisement.getStartingPrice()}" /></li>
			<li><b><spring:message code="advertisement.instantBuyPrice"></spring:message>:</b>
				<jstl:out value="${advertisement.getInstantBuyPrice()}" /></li>



			<br />
			<br />
			<!-- Listing grid BIDs -->
			<jstl:if test="${advertisement.secret==false}">
				<display:table pagesize="5" class="displaytag" name="bids"
					requestURI="advertisement/display.do" id="row">
					<spring:message code="advertisement.user" var="user" />
					<display:column title="${user}">
						<a href="user/display.do?userId=${row.user.id}"> <jstl:out
								value="${row.user.name}"></jstl:out></a>
					</display:column>

					<spring:message code="bid.amount" var="amount" />
					<display:column property="amount" title="${amount}" />

				</display:table>
			</jstl:if>
			
			<security:authorize access="hasRole('USER')">
			<jstl:if test="${ true}">
				<form action="auctionAdvertisement/bid.do" method="get">
					<input type="hidden" name="auctionAdvertisementId"
						value="${advertisement.id}">
					<spring:message code="bid.place" var="place" />
					<input type="number" step="0.01" name="amount" /> <br /> <input type="submit"
						value="${place}" />
				</form>
			</jstl:if>
			</security:authorize>
		</jstl:if>
	</ul>
	<input type="button" name="cancel"
		value="<spring:message code="advertisement.back" />"
		onclick="javascript: relativeRedir('advertisement/list.do')" />
</div>
