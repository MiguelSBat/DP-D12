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
<script src="scripts/checkout.js"></script>
<jstl:choose>
	<jstl:when test="${premium}">
		<spring:message code="payment.hasPremium"/>
	</jstl:when>
	<jstl:otherwise>
		<h3><spring:message code="payment.amount"/> <jstl:out value="${total}"/></h3>

		<p><spring:message code="payment.premium"/></p>
		<br>
		<div id="paypal-button"></div>
	</jstl:otherwise>
</jstl:choose>


<acme:cancel url="auctionAdvertisement/list.do" code="payment.cancel" />

<script>
	var CREATE_PAYMENT_URL  = 'user/payment/buyPremium.do';
	var EXECUTE_PAYMENT_URL = 'user/payment/executePremium.do';
	
	$.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
    
    paypal.Button.render({
      env: 'sandbox',
      commit: true,       
      locale: '<spring:message code="payment.locale"/>',

      style: {
        color: 'gold',
        size: 'small'
      },

      payment: function() {
    	  return paypal.request.get(CREATE_PAYMENT_URL)
    	  .then(function(data) {
				return data.id;
    		});
      },

      onAuthorize: function(data, actions) {
    	  return paypal.request.post(EXECUTE_PAYMENT_URL,{'paymentId' : data.paymentID,'payerId' : data.payerID}).then(function(data) {
    		 $(location).attr('href','user/payment/payConfirmation.do');
          });
      },

      onCancel: function(data, actions) {
        $(location).attr('href','user/payment/payPremium.do');
      },

      onError: function(err) {
        alert('error');
      }
    }, '#paypal-button');
  </script>