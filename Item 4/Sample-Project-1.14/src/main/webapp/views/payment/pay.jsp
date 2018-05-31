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

<h3><spring:message code="payment.amount"/> <jstl:out value="${total}"/></h3>
<br>

<form:form id="payForm" action="" modelAttribute="paymentForm">

<h2><spring:message code="payment.shipment"/></h2>

<acme:textbox code="payment.shipment.country" path="shipmentCountry" />
<acme:textbox code="payment.shipment.city" path="shipmentCity" />
<acme:textbox code="payment.shipment.postalCode" path="shipmentPostalCode" />
<acme:textarea code="payment.shipment.address" path="shipmentAdress" />

<h2><spring:message code="payment.facturation"/></h2>

<acme:textbox code="payment.facturation.name" path="name" />
<acme:textbox code="payment.facturation.surname" path="surname" />
<acme:textbox code="payment.facturation.country" path="country" />
<acme:textbox code="payment.facturation.city" path="city" />
<acme:textbox code="payment.facturation.postalCode" path="postalCode" />
<acme:textbox code="payment.facturation.idNumber" path="IDNumber" />
<br>
<div id="paypal-button"></div>

<acme:cancel url="user/shoppingCart/view.do" code="payment.cancel" />

</form:form>

<script>
	var CREATE_PAYMENT_URL  = 'user/payment/paypal.do';
	var EXECUTE_PAYMENT_URL = 'user/payment/executePaypal.do';

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
      env: 'sandbox', // Or 'sandbox',

      commit: true, // Show a 'Pay Now' button

      style: {
        color: 'gold',
        size: 'small'
      },

      payment: function() {
    	  serialized = $("#payForm").serialize();
    	  return paypal.request.post(CREATE_PAYMENT_URL,$("#payForm").serializeObject())
    	  .then(function(data) {
				return data.id;
    		});
      },

      onAuthorize: function(data, actions) {
    	  return paypal.request.post(EXECUTE_PAYMENT_URL,{'paymentId' : data.paymentID,'payerId' : data.payerID}).then(function(data) {
              if (data == 'OK'){
                  $(location).attr('href','/paymentConfirmation');
              } else if (data == 'ERROR'){
                  alert('Pago no confirmado');
              } else{
                  alert('Error en el pago');
              }
          });
      },

      onCancel: function(data, actions) {
        alert(cancel);
      },

      onError: function(err) {
        alert(error);
      }
    }, '#paypal-button');
  </script>