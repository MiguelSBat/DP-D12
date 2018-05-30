<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Sample Co., Inc." />
</div>

<div>
	<ul id="jMenu">
	    <li><a class="fNiv" href="advertisement/list.do"><spring:message code="master.page.advertisement" /></a>
	    	<ul>
					<li class="arrow"></li>
		<li><a href="expressAdvertisement/list.do"><spring:message code="master.page.express" /></a></li>
		<li><a href="auctionAdvertisement/list.do"><spring:message code="master.page.auctions" /></a></li>
		<li><a href="shopAdvertisement/list.do"><spring:message code="master.page.shops" /></a></li>
			</ul>
		</li>
		<!-- Do not forget the "fNiv" class for the first level links !! -->

	<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a href="actor/create.do?actorType=USER"><spring:message
										code="master.page.createUser" /></a></li>
			<li><a href="actor/create.do?actorType=BUSINESS"><spring:message
										code="master.page.createBusiness" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv" href="actor/display.do?actorId=0"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href='chat/opened.do'><spring:message code="actor.openedChat" /></a></li>
					<security:authorize access="hasAnyRole('USER','BUSINESS')">
					<li><a href="expressAdvertisement/create.do"><spring:message code="master.page.createExpress" /></a></li>
					<li><a href="expressAdvertisement/MyList.do"><spring:message code="master.page.MyList" /></a></li>
					<li><a href="auctionAdvertisement/myList.do"><spring:message code="master.page.myAuctions" /></a></li>
					<li><a href="valoration/list.do"><spring:message code="master.page.valoration" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('USER')">
						<li><a href="auctionAdvertisement/bidded.do"><spring:message code="master.page.user.bidded" /></a></li>
						<li><a href="user/item/list.do"><spring:message code="master.page.user.item" /></a></li>
						<li><a href="user/ticket/myTickets.do"><spring:message code="master.page.user.myTickets" /></a></li>
						<li><a href="user/ticket/mySales.do"><spring:message code="master.page.user.mySales" /></a></li>
						
					</security:authorize>
					<security:authorize access="hasRole('MODERATOR')">
						<li><a href="moderator/business/list.do"><spring:message code="master.page.moderator.business.list" /></a></li>	
						<li><a href="moderator/actor/list.do"><spring:message code="master.page.moderator.actorSuspicious.list" /></a></li>				
						<li><a href="moderator/actor/listToModerate.do"><spring:message code="master.page.moderator.actor.reported.list" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('BUSINESS')">
						<li><a href="business/ticket/mySales.do"><spring:message code="master.page.user.mySales"/></a></li>	
						<li><a href="businessInfo/create.do"><spring:message code="master.page.businessInfo"/></a></li>
							
						<li><a href="shopAdvertisement/myList.do"><spring:message code="master.page.myShop" /></a></li>
						<li><a href="business/item/list.do"><spring:message code="master.page.user.item" /></a></li>
						<li><a href="business/question/list.do"><spring:message code="master.page.business.question" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('ADMIN')">
						<li><a href="administrator/business/list.do"><spring:message code="master.page.moderator.business.list" /></a></li>	
						<li><a href="administrator/actor/list.do"><spring:message code="master.page.moderator.actorSuspicious.list" /></a></li>
						<li><a href="administrator/actor/listToModerate.do"><spring:message code="master.page.moderator.actor.reported.list" /></a></li>
						<li><a href="administrator/config/display.do"><spring:message code="master.page.administrator.config.display" /></a></li>	
						<li><a href="administrator/config/edit.do"><spring:message code="master.page.administrator.config.edit" /></a></li>	
						<li><a href="actor/create.do?actorType=ADMIN"><spring:message
									code="master.page.createAdmin" /></a></li>
						<li><a href="actor/create.do?actorType=MODERATOR"><spring:message
									code="master.page.createModerator" /></a></li>				
					</security:authorize>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv" href="user/shoppingCart/view.do"><spring:message code="master.page.shoppingCart" /></a></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

