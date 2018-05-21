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


   <head>
<link rel="stylesheet" href="jquery.rating.css">
        <script src="jquery.js"></script>
        <script src="jquery.rating.js"></script>
   </head>
<!-- search  -->






<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" 
	name="usuariosNoRepetidos" requestURI="valoration/list.do" id="row">

	
	<display:column title="${nameHeader}">
	<jstl:out	value="${row.getName()}"></jstl:out></a>
	</display:column>


	
</display:table>

<display:table pagesize="5" class="displaytag" 
	name="businessNoRepetidos" requestURI="valoration/list.do" id="row">

	
	<display:column title="${nameHeader}">
	<jstl:out	value="${row.getName()}"></jstl:out></a>
	</display:column>
	<display:column >
		
	
 		<form method="get" action='valoration/valorate.do?actorId=${row.id}&?'>
       		<input type="radio" name="rating" value="1" >
            <input type="radio" name="rating" value="2" >
            <input type="radio" name="rating" value="3" >
            <input type="radio" name="rating" value="4" >
            <input type="radio" name="rating" value="5" >
            <input type="hidden" name="actorId" value=${row.id}>
            <input type="submit" value="rating">
           
        </form>
	</display:column>
</display:table>
