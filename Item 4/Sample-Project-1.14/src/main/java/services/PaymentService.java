
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Payout;
import com.paypal.api.payments.PayoutBatch;
import com.paypal.api.payments.PayoutItem;
import com.paypal.api.payments.PayoutSenderBatchHeader;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import domain.SaleLine;
import domain.Ticket;
import domain.User;

@Service
@Transactional
public class PaymentService {

	public static final String	clientId		= "AaeyIU4ky8uiKZssASo0KkXb8zjr9h9ORIl1FOsIIsz7QbK8FPdsRKQd1Rt_HMwowtm-24_1t4fKpya6";
	public static final String	clientSecret	= "EFLsg0z_UNU5Ec6mDhVlHxnnjCKa899R4F8LW4KBCzl6aPGI1t563k_r0x2nTf_my3cS3uvO7qMKLLRL";

	@Autowired
	private UserService			userService;

	@Autowired
	private ShoppingCartService	shoppingCartService;

	@Autowired
	private SaleLineService		saleLineService;

	@Autowired
	private TicketService		ticketService;


	public Payment buildPayment(final String baseUrl) {
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

	public void payout(final Collection<Ticket> tickets) throws PayPalRESTException {
		final Payout payout = new Payout();

		final PayoutSenderBatchHeader senderBatchHeader = new PayoutSenderBatchHeader();
		final Random random = new Random();
		senderBatchHeader.setSenderBatchId(new Double(random.nextDouble()).toString()).setEmailSubject("Pago de AcmeShop");

		final List<PayoutItem> items = new ArrayList<PayoutItem>();

		for (final Ticket t : tickets) {
			final Currency amount = new Currency();
			amount.setValue("100").setCurrency("EUR");
			final PayoutItem senderItem = new PayoutItem();
			senderItem.setRecipientType("Email").setNote("Pago de AcmeShop").setReceiver(t.getSeller() != null ? t.getSeller().getEmailAddress() : t.getBusiness().getPaypalEmail()).setSenderItemId("201404324234").setAmount(amount);
		}

		payout.setSenderBatchHeader(senderBatchHeader).setItems(items);

		PayoutBatch batch = null;

		try {
			final APIContext apiContext = new APIContext(PaymentService.clientId, PaymentService.clientSecret, "sandbox");

			batch = payout.create(apiContext, new HashMap<String, String>());
			System.out.println(batch.toString());
		} catch (final PayPalRESTException e) {
			e.printStackTrace();
		}
	}

	public Payment buildPayment(final String baseUrl, final Double price, final String recipient) {
		final User user = this.userService.findByPrincipal();
		final List<Transaction> transactions = new LinkedList<Transaction>();

		final Amount amount = new Amount();
		amount.setCurrency("EUR");
		amount.setTotal(Double.toString(price));

		final Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription("AcmeShop");
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

	public void payout(final Double amount, final String email) throws PayPalRESTException {
		final Payout payout = new Payout();

		final PayoutSenderBatchHeader senderBatchHeader = new PayoutSenderBatchHeader();
		final Random random = new Random();
		senderBatchHeader.setSenderBatchId(new Double(random.nextDouble()).toString()).setEmailSubject("Pago de AcmeShop");

		final List<PayoutItem> items = new ArrayList<PayoutItem>();

		final Currency currency = new Currency();
		currency.setValue(Double.toString(amount)).setCurrency("EUR");
		final PayoutItem item = new PayoutItem();
		item.setRecipientType("Email").setNote("Pago de AcmeShop").setReceiver(email).setSenderItemId("201404324234").setAmount(currency);
		items.add(item);
		payout.setSenderBatchHeader(senderBatchHeader).setItems(items);

		PayoutBatch batch = null;

		try {
			final APIContext apiContext = new APIContext(PaymentService.clientId, PaymentService.clientSecret, "sandbox");

			batch = payout.create(apiContext, new HashMap<String, String>());
			System.out.println(batch.toString());
		} catch (final PayPalRESTException e) {
			e.printStackTrace();
		}
	}
}
