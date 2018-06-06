
package controllers.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AuctionAdvertisementService;
import services.BidService;
import services.ConfigService;
import services.PaymentService;
import services.SaleLineService;
import services.ShoppingCartService;
import services.TicketService;
import services.UserService;

import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import domain.Actor;
import domain.AuctionAdvertisement;
import domain.Bid;
import domain.Business;
import domain.User;
import forms.PaymentForm;
import forms.PaymentResponse;

@Controller
@RequestMapping("/user/payment")
public class PaymentUserController {

	@Autowired
	ShoppingCartService			shoppingCartService;

	@Autowired
	UserService					userService;

	@Autowired
	SaleLineService				saleLineService;

	@Autowired
	TicketService				ticketService;

	@Autowired
	PaymentService				paymentService;

	@Autowired
	AuctionAdvertisementService	auctionAdvertisementService;

	@Autowired
	BidService					bidService;

	@Autowired
	ConfigService				configService;

	@Autowired
	ActorService				actorService;


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

	@RequestMapping(value = "/payBuyNow", method = RequestMethod.GET)
	public ModelAndView payBuyNow(@RequestParam final int auctionId, final HttpSession session) {
		final ModelAndView result;
		final AuctionAdvertisement ad = this.auctionAdvertisementService.findOne(auctionId);

		final Double total = ad.getInstantBuyPrice();
		final PaymentForm form = this.userService.getPaymentForm();
		session.setAttribute("auction", ad);

		result = new ModelAndView("payment/payBuyNow");
		result.addObject("total", total);
		result.addObject("paymentForm", form);
		return result;
	}

	@RequestMapping(value = "/payBid", method = RequestMethod.GET)
	public ModelAndView payBid(@RequestParam final int auctionId, final HttpSession session) {
		final ModelAndView result;
		final Bid bid = this.bidService.findHighest(auctionId);

		final Double total = bid.getAmount();
		final PaymentForm form = this.userService.getPaymentForm();
		session.setAttribute("bid", bid);

		result = new ModelAndView("payment/payBid");
		result.addObject("total", total);
		result.addObject("paymentForm", form);
		return result;
	}

	@RequestMapping(value = "/payPremium", method = RequestMethod.GET)
	public ModelAndView payPremium() {
		final ModelAndView result;
		final Actor a = this.actorService.findByPrincipal();
		Boolean premium = false;

		if (a instanceof User)
			premium = ((User) a).isPremium();
		else if (a instanceof Business)
			premium = ((Business) a).isPremium();

		final Double total = this.configService.findConfiguration().getPremiumPrice();

		result = new ModelAndView("payment/payPremium");
		result.addObject("total", total);
		result.addObject("premium", premium);
		return result;
	}

	@RequestMapping(value = "/paypal", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public PaymentResponse paypal(@Valid final PaymentForm body, final BindingResult binding, final HttpServletRequest request, final HttpSession session) {
		PaymentResponse result = new PaymentResponse();

		if (!binding.hasErrors()) {

			final Payment payment = this.paymentService.buildPayment(request.getLocalName());

			try {
				final APIContext apiContext = new APIContext(PaymentService.clientId, PaymentService.clientSecret, "sandbox");
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
		} else {
			result.setErrors(this.paymentService.parseErrors(binding, request));
			result.setState("validationError");
		}

		return result;
	}

	@RequestMapping(value = "/buyNow", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public PaymentResponse buyNow(@Valid final PaymentForm body, final BindingResult binding, final HttpServletRequest request, final HttpSession session) {
		final PaymentResponse result = new PaymentResponse();

		if (!binding.hasErrors()) {

			final AuctionAdvertisement auction = (AuctionAdvertisement) session.getAttribute("auction");

			final Payment payment = this.paymentService.buildPayment(request.getLocalName(), auction.getInstantBuyPrice(), auction.getBusiness() != null ? auction.getBusiness().getPaypalEmail() : auction.getUser().getEmailAddress());

			try {
				final APIContext apiContext = new APIContext(PaymentService.clientId, PaymentService.clientSecret, "sandbox");
				final Payment createdPayment = payment.create(apiContext);
				System.out.println(createdPayment.toString());
				if (createdPayment.getState().equals("created")) {
					result.setId(createdPayment.getId());
					result.setState(createdPayment.getState());
					session.setAttribute("paymentForm", body);
				}
			} catch (final PayPalRESTException e) {
				return null;
			} catch (final Exception ex) {
				return null;
			}

		} else {
			result.setErrors(this.paymentService.parseErrors(binding, request));
			result.setState("validationError");
		}

		return result;
	}

	@RequestMapping(value = "/buyBid", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public PaymentResponse buyBid(@Valid final PaymentForm body, final BindingResult binding, final HttpServletRequest request, final HttpSession session) {
		final PaymentResponse result = new PaymentResponse();
		;

		if (!binding.hasErrors()) {

			final Bid bid = (Bid) session.getAttribute("bid");

			final Payment payment = this.paymentService.buildPayment(request.getLocalName(), bid.getAmount(), bid.getAuctionAdvertisement().getBusiness() != null ? bid.getAuctionAdvertisement().getBusiness().getPaypalEmail() : bid
				.getAuctionAdvertisement().getUser().getEmailAddress());

			try {
				final APIContext apiContext = new APIContext(PaymentService.clientId, PaymentService.clientSecret, "sandbox");
				final Payment createdPayment = payment.create(apiContext);
				System.out.println(createdPayment.toString());
				if (createdPayment.getState().equals("created")) {
					result.setId(createdPayment.getId());
					result.setState(createdPayment.getState());
					session.setAttribute("paymentForm", body);
				}
			} catch (final PayPalRESTException e) {
				return null;
			} catch (final Exception ex) {
				return null;
			}

		} else {
			result.setErrors(this.paymentService.parseErrors(binding, request));
			result.setState("validationError");
		}

		return result;
	}

	@RequestMapping(value = "/buyPremium", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public PaymentResponse buyBid(final HttpServletRequest request) {
		PaymentResponse result = null;

		final Payment payment = this.paymentService.buildPayment(request.getLocalName(), this.configService.findConfiguration().getPremiumPrice(), "");

		try {
			final APIContext apiContext = new APIContext(PaymentService.clientId, PaymentService.clientSecret, "sandbox");
			final Payment createdPayment = payment.create(apiContext);
			System.out.println(createdPayment.toString());
			if (createdPayment.getState().equals("created")) {
				result = new PaymentResponse();
				result.setId(createdPayment.getId());
				result.setState(createdPayment.getState());
			}
		} catch (final PayPalRESTException e) {
			return null;
		} catch (final Exception ex) {
			return null;
		}

		return result;
	}

	@RequestMapping(value = "/executePaypal", method = RequestMethod.POST)
	@ResponseBody
	public PaymentResponse executePaypal(final String paymentId, final String payerId, final HttpServletRequest request, final HttpSession session) {
		final PaymentResponse response = new PaymentResponse();
		final APIContext apiContext = new APIContext(PaymentService.clientId, PaymentService.clientSecret, "sandbox");
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
				response.setState("OK");
			}
		} catch (final PayPalRESTException e) {
			response.setState("ERROR");
		} finally {
			session.removeAttribute("paymentForm");
		}

		return response;
	}

	@RequestMapping(value = "/executeBuyNow", method = RequestMethod.POST)
	@ResponseBody
	public PaymentResponse executeBuyNow(final String paymentId, final String payerId, final HttpSession session) {
		final PaymentResponse response = new PaymentResponse();
		final APIContext apiContext = new APIContext(PaymentService.clientId, PaymentService.clientSecret, "sandbox");
		try {
			final Payment payment = Payment.get(apiContext, paymentId);
			Assert.notNull(payment);
			final PaymentExecution execution = new PaymentExecution();
			execution.setPayerId(payerId);
			final Payment result = payment.execute(apiContext, execution);
			System.out.println(result.toString());
			if (result.getState().equals("approved")) {
				this.ticketService.executeBuy((AuctionAdvertisement) session.getAttribute("auction"), (PaymentForm) session.getAttribute("paymentForm"));
				response.setState("OK");
			}
		} catch (final PayPalRESTException e) {
			response.setState("ERROR");
		} finally {
			session.removeAttribute("auction");
		}

		return response;
	}

	@RequestMapping(value = "/executeBid", method = RequestMethod.POST)
	@ResponseBody
	public PaymentResponse executeBid(final String paymentId, final String payerId, final HttpSession session) {
		final PaymentResponse response = new PaymentResponse();
		final APIContext apiContext = new APIContext(PaymentService.clientId, PaymentService.clientSecret, "sandbox");
		try {
			final Payment payment = Payment.get(apiContext, paymentId);
			Assert.notNull(payment);
			Assert.isTrue(((Bid) session.getAttribute("bid")).getUser().equals(this.userService.findByPrincipal()));
			final PaymentExecution execution = new PaymentExecution();
			execution.setPayerId(payerId);
			final Payment result = payment.execute(apiContext, execution);
			System.out.println(result.toString());
			if (result.getState().equals("approved")) {
				this.ticketService.executeBuy((Bid) session.getAttribute("bid"), (PaymentForm) session.getAttribute("paymentForm"));
				response.setState("OK");
			}
		} catch (final PayPalRESTException e) {
			response.setState("ERROR");
		} finally {
			session.removeAttribute("bid");
		}

		return response;
	}

	@RequestMapping(value = "/executePremium", method = RequestMethod.POST)
	@ResponseBody
	public PaymentResponse executePremium(final String paymentId, final String payerId) {
		final PaymentResponse response = new PaymentResponse();
		final APIContext apiContext = new APIContext(PaymentService.clientId, PaymentService.clientSecret, "sandbox");
		try {
			final Payment payment = Payment.get(apiContext, paymentId);
			Assert.notNull(payment);
			final PaymentExecution execution = new PaymentExecution();
			execution.setPayerId(payerId);
			final Payment result = payment.execute(apiContext, execution);
			System.out.println(result.toString());
			if (result.getState().equals("approved")) {
				response.setState("OK");
				this.userService.makePremium();
			}
		} catch (final PayPalRESTException e) {
			response.setState("ERROR");
		}

		return response;
	}

	@RequestMapping(value = "/payConfirmation", method = RequestMethod.GET)
	public ModelAndView paymentConfirmation() {
		return new ModelAndView("payment/confirmation");
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
