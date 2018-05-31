
package controllers.user;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import services.SaleLineService;
import services.ShoppingCartService;
import services.TicketService;
import services.UserService;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import domain.SaleLine;
import domain.User;
import forms.PaymentForm;
import forms.PaymentResponse;

@Controller
@RequestMapping("/user/payment")
@EnableWebMvc
public class PaymentUserController {

	private static final String	clientId		= "AaeyIU4ky8uiKZssASo0KkXb8zjr9h9ORIl1FOsIIsz7QbK8FPdsRKQd1Rt_HMwowtm-24_1t4fKpya6";
	private static final String	clientSecret	= "EFLsg0z_UNU5Ec6mDhVlHxnnjCKa899R4F8LW4KBCzl6aPGI1t563k_r0x2nTf_my3cS3uvO7qMKLLRL";

	@Autowired
	ShoppingCartService			shoppingCartService;

	@Autowired
	UserService					userService;

	@Autowired
	SaleLineService				saleLineService;

	@Autowired
	TicketService				ticketService;


	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public ModelAndView pay() {
		final ModelAndView result;

		final Double total = this.shoppingCartService.getTotal();
		final PaymentForm form = this.userService.getPaymentForm();

		result = new ModelAndView("payment/pay");
		result.addObject("total", total);
		result.addObject("paymentForm", form);
		return result;
	}

	@RequestMapping(value = "/paypal", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public PaymentResponse paypal(@Valid final PaymentForm body, final BindingResult binding, final HttpServletRequest request, final HttpSession session) {
		PaymentResponse result = null;

		if (!binding.hasErrors()) {

			final Payment payment = this.buildPayment(request.getLocalName());

			try {
				final APIContext apiContext = new APIContext(PaymentUserController.clientId, PaymentUserController.clientSecret, "sandbox");
				final Payment createdPayment = payment.create(apiContext);
				System.out.println(createdPayment.toString());
				if (createdPayment.getState().equals("created")) {
					result = new PaymentResponse();
					result.setId(createdPayment.getId());
					result.setState(createdPayment.getState());
					session.setAttribute("paymentForm", body);
				}
			} catch (final PayPalRESTException e) {
				return null;
			} catch (final Exception ex) {
				return null;
			}
		}
		return result;
	}

	@RequestMapping(value = "/executePaypal", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> executePaypal(final String paymentId, final String payerId, final HttpServletRequest request, final HttpSession session) {
		ResponseEntity<String> response = null;
		final APIContext apiContext = new APIContext(PaymentUserController.clientId, PaymentUserController.clientSecret, "sandbox");
		try {
			final Payment payment = Payment.get(apiContext, paymentId);
			Assert.notNull(payment);
			final PaymentExecution execution = new PaymentExecution();
			execution.setPayerId(payerId);
			final Payment result = payment.execute(apiContext, execution);
			System.out.println(result.toString());
			final PaymentForm paymentForm = (PaymentForm) session.getAttribute("paymentForm");
			if (result.getState().equals("approved")) {
				this.ticketService.executeBuy(paymentForm);
				response = new ResponseEntity<String>("OK", HttpStatus.OK);
			}
		} catch (final PayPalRESTException e) {
			response = new ResponseEntity<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	private Payment buildPayment(final String baseUrl) {
		final User user = this.userService.findByPrincipal();
		final Double total = this.shoppingCartService.getTotal();
		final Collection<SaleLine> lines = this.saleLineService.findByPrincipal();
		String description = "";
		final List<Transaction> transactions = new LinkedList<Transaction>();

		final Amount amount = new Amount();
		amount.setCurrency("EUR");
		amount.setTotal(Double.toString(total));

		final Transaction transaction = new Transaction();
		transaction.setAmount(amount);

		for (final SaleLine line : lines) {
			description += line.getAdvertisement().getItem().getName();
			description += ", ";
		}
		transaction.setDescription(description);
		transactions.add(transaction);

		final PayerInfo payerInfo = new PayerInfo();
		payerInfo.setFirstName(user.getName());
		payerInfo.setLastName(user.getSurname());
		payerInfo.setEmail(user.getEmailAddress());

		final Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		payer.setPayerInfo(payerInfo);

		final Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);

		final RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(baseUrl + "/user/shoppingCart/view.do");
		redirectUrls.setReturnUrl(baseUrl + "/");
		payment.setRedirectUrls(redirectUrls);
		return payment;
	}

	//	private List<Transaction> getTransactions(final Collection<SaleLine> lines) { 		// PayPal doesn't support multiple-transaction operations anymore, 
	//		final List<Transaction> transactions = new ArrayList<Transaction>();			// so we can't provide a breakdown of the total price
	//
	//		for (final SaleLine line : lines) {
	//			final Transaction transaction = new Transaction();
	//			Double price = null;
	//
	//			final Advertisement ad = line.getAdvertisement();
	//			if (ad instanceof ExpressAdvertisement)
	//				price = ((ExpressAdvertisement) ad).getPrice();
	//			if (ad instanceof ShopAdvertisement)
	//				price = ((ShopAdvertisement) ad).getPrice();
	//
	//			final Amount amount = new Amount();
	//			amount.setCurrency("EUR");
	//			amount.setTotal(Double.toString(price));
	//
	//			transaction.setAmount(amount);
	//			transaction.setDescription(ad.getItem().getName());
	//			transactions.add(transaction);
	//		}
	//		return transactions;
	//	}
}
