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
<script src="scripts/checkout.js"></script>

<script>
	function addToCartAction(adId){
		var amount = $("#cartAmount").val();
		
		$.ajax({
		    type:'GET',
		    url: "user/advertisement/addToCart.do?adId="+adId+"&amount="+amount,
		    dataType: 'text',
		    success:function(data, status){

		        switch(data){
		        	case "added":
		        		$(".cartMsg").addClass("hidden");
		        		$("#added").removeClass("hidden");
		        		break;
		        	case "outOfStock":
		        		$(".cartMsg").addClass("hidden");
		        		$("#outOfStock").removeClass("hidden");
		        		break;
		        	case "invalidAmount":
		        		$(".cartMsg").addClass("hidden");
		        		$("#amount").removeClass("hidden");
		        		break;
		        	case "error":
		        		$(".cartMsg").addClass("hidden");
		        		$(".#cartError").removeClass("hidden");
		        		break;
		        	default:
		        		$(".cartMsg").addClass("hidden");
		        		break;
		        		
		        }

		        },
		    error:function(xhr, status, errorThrown){

		        console.log(xhr);
		        console.log(status);
		        console.log(errorThrown);
		    }

		});
	}
</script>


<!-- soy el display de un advertisement si, no funciono obviamente modificame como veas - ES TO FEO, DEJO MI BOTON Y ME VOY bye -->


<!-- Guardamos en una variable el formato de la fecha  -->


<spring:message code="master.page.date.format" var="dateFormat" />


<fmt:setLocale value="${locale}" />
	<security:authorize access="hasAnyRole('USER','BUSINESS') || isAnonymous()">
	<jstl:if test="${showPremiumAd==false}">
	<a class="premium" href="advertisement/display.do?advertisementId=${premium.getId()}">
	<div class="solid">
	<h3 ><spring:message code="advertisement.premium"></spring:message></h3>
	<b><spring:message code="advertisement.item"></spring:message>:</b>
			<jstl:out value="${premium.getItem().getName()}" />
			
		<jstl:if test="${premium.getClass().name == 'domain.ShopAdvertisement'}">
			<li><b><spring:message code="advertisement.stock"></spring:message>:</b>
				<jstl:out value="${premium.stock}" /></li>
			<li><b><spring:message code="advertisement.price"></spring:message>:</b>
				<jstl:out value="${premium.price}" /></li>
		</jstl:if>
		
		<jstl:if test="${premium.getClass().name == 'domain.AuctionAdvertisement'}">
			<li><b><spring:message code="advertisement.instantBuyPrice"></spring:message>:</b>
				<jstl:out value="${premium.getInstantBuyPrice()}" /> 
			<li><b><spring:message code="advertisement.startingPrice"></spring:message>:</b>
				<jstl:out value="${premium.getStartingPrice()}" /></li>
		</jstl:if>
		
		<jstl:if test="${premium.getClass().name == 'domain.ExpressAdvertisement'}">
			<li><b><spring:message code="advertisement.price"></spring:message>:</b>
				<jstl:out value="${premium.getPrice()}" /></li>
		</jstl:if>
		
	<b><spring:message code="advertisement.endDate"></spring:message>:</b>
	<fmt:formatDate value="${premium.getEndDate()}" />
	
	<img class="premiumphoto" src="${premium.item.photo}"/>

	<p class="message">	<spring:message code="advertisement.click"/>
	</p>
	</div>
	</a>
	</jstl:if>
	</security:authorize>
	
<div id="advertisement">

	<br />
	
	<ul style="list-style-type: disc">
		<h1>
			<spring:message code="advertisement.type"></spring:message>
			:
			<jstl:out value="${type}"></jstl:out>
		</h1>
		<jstl:if test="${user}">
	<a href="actor/display.do?actorId=${advertisement.getUser().getId()}">${advertisement.getUser().getName()}</a>
	</jstl:if>
	<jstl:if test="${business}">
	<a href="actor/display.do?actorId=${advertisement.getBusiness().getId()}">${advertisement.getBusiness().getName()}</a>
	</jstl:if>
		<li><b><spring:message code="advertisement.item"></spring:message>:</b>
			<jstl:out value="${advertisement.getItem().getName()}" /></li>

		<li><b><spring:message code="advertisement.endDate"></spring:message>:</b>
			<fmt:formatDate value="${advertisement.getEndDate()}"
				pattern="${dateFormat}" /></li>

		<li><b><spring:message code="advertisement.publicationDate"></spring:message>:</b>
			<fmt:formatDate value="${advertisement.getPublicationDate()}"
				pattern="MM/dd/yyyy HH:mm" /></li>

		<jstl:if test="${type.equals('shop')}">
			<li><b><spring:message code="advertisement.stock"></spring:message>:</b>
				<jstl:out value="${advertisement.getStock()}" /></li>
			<li><b><spring:message code="advertisement.price"></spring:message>:</b>
				<jstl:out value="${advertisement.price}" /></li>
			<img src="${advertisement.item.photo}" height="200" width="300" />
			<li><b><spring:message code="advertisement.score"></spring:message>:</b>
				<jstl:out value="${score}" /></li>
			
			<display:table pagesize="5" class="displaytag" name="reviews"
					requestURI="advertisement/display.do" id="row">
					<spring:message code="advertisement.user" var="user" />
					<display:column title="${user}">
						<a href="actor/display.do?actorId=${row.user.id}"> <jstl:out
								value="${row.user.name}"></jstl:out></a>
					</display:column>

					<spring:message code="review.date" var="date" />
					<display:column property="date" title="${date}" />

					<spring:message code="review.text" var="text" />
					<display:column property="text" title="${text}" />
					
					<spring:message code="review.score" var="score" />
					<display:column property="score" title="${score}" />

			</display:table>
					<display:table pagesize="5" class="displaytag" name="questions"
					requestURI="advertisement/display.do" id="row">
					<spring:message code="advertisement.user" var="user" />
					<display:column title="${user}">
						<a href="actor/display.do?actorId=${row.user.id}"> <jstl:out
								value="${row.user.name}"></jstl:out></a>
					</display:column>

					<spring:message code="review.date" var="date" />
					<display:column property="date" title="${date}" />

					<spring:message code="review.text" var="text" />
					<display:column property="text" title="${text}" />
					
					<spring:message code="review.questionanswers" var="answers" />
					<display:column title="${answers}">
						<a href="question/display.do?questionId=${row.id}"> <jstl:out
								value="${answers}"></jstl:out></a>
					</display:column>

			</display:table>
			<security:authorize access="hasRole('USER')">
				<a href="user/question/create.do?shopAdvertisementId=${advertisement.id}">
					<spring:message code="advertisement.question.ask" />
				</a>
			</security:authorize>

		</jstl:if>
		<jstl:if test="${type.equals('express')}">
			<li><b><spring:message code="advertisement.price"></spring:message>:</b>
				<jstl:out value="${advertisement.price}" /></li>
			<img src="${advertisement.item.photo}" height="200" width="300" />

		</jstl:if>
		<jstl:if test="${type.equals('auction')}">
			<li><b><spring:message code="advertisement.startingPrice"></spring:message>:</b>
				<jstl:out value="${advertisement.getStartingPrice()}" /></li>
			<li><b><spring:message code="advertisement.instantBuyPrice"></spring:message>:</b>
				<jstl:out value="${advertisement.getInstantBuyPrice()}" /> <input type="button" name="cancel"
																				value="<spring:message code="advertisement.buy.now" />"
																				onclick="javascript: relativeRedir('user/payment/payBuyNow.do?auctionId=${advertisement.id}')" /></li>
				
				

	
	<img src="${advertisement.item.photo}" height="200" width="300" />
	
			<br />
			<br />
			<!-- Listing grid BIDs -->
			<jstl:if test="${advertisement.secret==false}">
				<display:table pagesize="5" class="displaytag" name="bids"
					requestURI="advertisement/display.do" id="row">
					<spring:message code="advertisement.user" var="user" />
					<display:column title="${user}">
						<a href="actor/display.do?actorId=${row.user.id}"> <jstl:out
								value="${row.user.name}"></jstl:out></a>
					</display:column>

					<spring:message code="bid.amount" var="amount" />
					<display:column property="amount" title="${amount}" />

				</display:table>
			</jstl:if>
			
			<security:authorize access="hasRole('USER')">
			<jstl:if test="${biddable}">
				<jstl:if test="${minimumBid==true}">
					<spring:message code="bid.minimum"/>
					<br>
				</jstl:if>
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
	
	
	
	<jstl:if test="${type.equals('shop')}">
		<input type="button" name="addToCart"
			value="<spring:message code="advertisement.addToCart" />"
			onclick="addToCartAction('${advertisement.id}')" />
		<input type="number" step="1" min="0" max="${advertisement.stock}" value="1" name="cartAmount" id="cartAmount" />
	</jstl:if>
	<jstl:if test="${type.equals('express')}">
		<input type="button" name="addToCart"
			value="<spring:message code="advertisement.addToCart" />"
			onclick="addToCartAction('${advertisement.id}')" />
		<input style="display: none;" type="number" value="1" name="cartAmount" id="cartAmount" />
	</jstl:if>
	
	<!-- Cart confirmation messages -->
	
	<strong id="added" class="hidden cartMsg"><spring:message code="advertisement.addToCart.added" /><a href="user/shoppingCart/view.do"><spring:message code="advertisement.viewCart" /></a></strong>
	<strong id="outOfStock" class="hidden cartMsg"><spring:message code="advertisement.addToCart.outOfStock" /></strong>
	<strong id="amount" class="hidden cartMsg"><spring:message code="advertisement.addToCart.amount" /></strong>
	<strong id="cartError" class="hidden cartMsg"><spring:message code="advertisement.addToCart.error" /></strong>
	
	
	<br>
	<br>
	
	<input type="button" name="cancel"
		value="<spring:message code="advertisement.back" />"
		onclick="javascript: relativeRedir('advertisement/list.do')" />
		
	<h3><spring:message code="advertisement.related" /></h3>
	
	<display:table pagesize="5" class="displaytag" 
	name="related" requestURI="advertisement/display.do?advertisementId=${advertisement.id}" id="row">

	<spring:message code="advertisement.item" var="itemHeader" />
	<display:column title="${itemHeader}">
		<a href="advertisement/display.do?advertisementId=${row.getId()}"><jstl:out
				value="${row.item.getName()}"></jstl:out></a>
	</display:column>

	<%-- <spring:message code="advertisement.business" var="businessHeader" />
	<display:column property="business.name" title="${businessHeader}" />
 --%>
	<spring:message code="advertisement.publicationDate"
		var="publicationDateHeader" />
	<spring:message code="master.page.date.format" var="dateFormat" />
	<display:column property="publicationDate"
		format="{0,date,${dateFormat}}" title="${publicationDateHeader}" />

<spring:message code="advertisement.endDate"
		var="endDateHeader" />
	<spring:message code="master.page.date.format" var="dateFormat" />
	<display:column property="endDate"
		format="{0,date,${dateFormat}}" title="${endDateHeader}" />
	
		<spring:message code="advertisement.price" var="priceHeader" />
		
		<display:column title="${priceHeader}">
			<jstl:if test="${row.getClass().name != 'domain.AuctionAdvertisement'}">
				<jstl:out value="${row.price}"/>
			</jstl:if>
			<jstl:if test="${row.getClass().name == 'domain.AuctionAdvertisement'}">
				<jstl:out value="${row.startingPrice}"/>
			</jstl:if>
		</display:column>
		
	<spring:message code="advertisement.tags" var="tagsHeader" />
			<display:column property="tags" title="${tagsHeader}" />
	

</display:table>
</div>

