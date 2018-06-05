
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TicketRepository;

import com.paypal.base.rest.PayPalRESTException;

import domain.Actor;
import domain.Advertisement;
import domain.AuctionAdvertisement;
import domain.Bid;
import domain.ExpressAdvertisement;
import domain.FacturationData;
import domain.SaleLine;
import domain.ShipmentAddress;
import domain.ShopAdvertisement;
import domain.ShoppingCart;
import domain.Ticket;
import domain.User;
import forms.PaymentForm;

@Service
@Transactional
public class TicketService {

	//Managed Repository ----
	@Autowired
	private TicketRepository		ticketRepository;

	@Autowired
	private SaleLineService			saleLineService;

	@Autowired
	private AdvertisementService	advertisementService;

	@Autowired
	private BusinessService			businessService;

	@Autowired
	private UserService				userService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private FacturationDataService	facturationDataService;

	@Autowired
	private ShipmentAddressService	shipmentAddressService;

	@Autowired
	private ShoppingCartService		shopppingCartService;

	@Autowired
	private PaymentService			paymentService;

	@Autowired
	private BidService				bidService;

	public static final String		PENDING		= "PENDING";
	public static final String		SENT		= "SENT";
	public static final String		RECEIVED	= "RECEIVED";
	public static final String		CANCELLED	= "CANCELED";


	//Constructors
	public TicketService() {
		super();
	}

	public Ticket create() {
		Ticket result;

		result = new Ticket();

		return result;
	}

	public Collection<Ticket> findAll() {
		Collection<Ticket> result;

		result = this.ticketRepository.findAll();

		return result;
	}

	public void delete(final Ticket order) {

		this.ticketRepository.delete(order);

	}

	public Ticket save(final Ticket order) {
		Ticket result;

		result = this.ticketRepository.save(order);
		return result;
	}

	public Ticket findOne(final int orderId) {
		Ticket result;

		result = this.ticketRepository.findOne(orderId);

		return result;
	}

	public void flush() {
		this.ticketRepository.flush();
	}

	public void executeBuy(final PaymentForm form) {
		final Collection<Ticket> tickets = this.parseShoppingCart(this.shopppingCartService.findByPrincipalOrCreate());
		Assert.notEmpty(tickets);
		for (final Ticket t : tickets) {
			final Ticket saved = this.save(t);

			final ShipmentAddress shipmentAddress = this.shipmentAddressService.create(form);
			shipmentAddress.setTicket(saved);
			this.shipmentAddressService.save(shipmentAddress);

			final FacturationData facturationData = this.facturationDataService.create(form);
			facturationData.setTicket(saved);
			this.facturationDataService.save(facturationData);

			this.advertisementService.executeBuy(saved);
		}

		try {
			this.paymentService.payout(tickets);
		} catch (final PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.shopppingCartService.remove();
	}
	public Collection<Ticket> parseShoppingCart(final ShoppingCart cart) {
		final Collection<Ticket> result = new HashSet<Ticket>();
		final Collection<SaleLine> lines = this.saleLineService.findByShoppingCart(cart);
		final User principal = this.userService.findByPrincipal();
		final Date date = new Date();

		for (final SaleLine line : lines)
			if (line.getAdvertisement() instanceof ExpressAdvertisement) {
				final ExpressAdvertisement ad = (ExpressAdvertisement) line.getAdvertisement();
				Boolean exists = false;
				for (final Ticket t : result)
					if (t.getSeller() != null && t.getSeller().equals(ad.getUser())) {
						exists = true;
						line.setShoppingCart(null);
						line.setTicket(t);
						this.saleLineService.save(line);
						break;
					}
				if (!exists) {
					final Ticket t = new Ticket();
					t.setUser(principal);
					t.setSeller(ad.getUser());
					t.setDate(date);
					t.setStatus(TicketService.PENDING);
					final Ticket saved = this.save(t);
					line.setShoppingCart(null);
					line.setTicket(saved);
					result.add(saved);
					this.saleLineService.save(line);
					continue;
				}
			} else if (line.getAdvertisement() instanceof ShopAdvertisement) {
				final ShopAdvertisement ad = (ShopAdvertisement) line.getAdvertisement();
				Boolean exists = false;
				for (final Ticket t : result)
					if (t.getBusiness() != null && t.getBusiness().equals(ad.getBusiness())) {
						exists = true;
						line.setShoppingCart(null);
						line.setTicket(t);
						this.saleLineService.save(line);
						break;
					}
				if (!exists) {
					final Ticket t = new Ticket();
					t.setUser(principal);
					t.setBusiness(ad.getBusiness());
					t.setDate(date);
					t.setStatus(TicketService.PENDING);
					final Ticket saved = this.save(t);
					line.setShoppingCart(null);
					line.setTicket(saved);
					result.add(saved);
					this.saleLineService.save(line);
					continue;
				}
			}

		return result;
	}

	public Collection<Ticket> findByBusinessId(final int id) {
		Collection<Ticket> result;

		result = this.ticketRepository.findByBusinessId(id);

		return result;
	}
	public Collection<Ticket> findByUserId(final int id) {
		Collection<Ticket> result;

		result = this.ticketRepository.findByUserId(id);

		return result;
	}
	public Collection<Ticket> findBySellerId(final int id) {
		Collection<Ticket> result;

		result = this.ticketRepository.findBySellerId(id);

		return result;
	}
	public Ticket changeStatus(final int ticketId, final String status) {
		Ticket result;
		Ticket ticket;
		Actor principal;

		principal = this.actorService.findByPrincipal();
		ticket = this.findOne(ticketId);
		if (status.equals("SENT") || status.equals("CANCELLED")) {
			if (ticket.getBusiness() != null)
				Assert.isTrue(ticket.getBusiness().getId() == principal.getId());
			else
				Assert.isTrue(ticket.getSeller().getId() == principal.getId());
		} else if (status.equals("RECEIVED"))
			Assert.isTrue(ticket.getUser().getId() == principal.getId());
		else
			Assert.isTrue(false);

		ticket.setStatus(status);
		result = this.save(ticket);
		return result;
	}

	public Double getTotal(final Ticket t) {
		Double result = 0.0;
		final Collection<SaleLine> lines = this.saleLineService.findByTicketId(t.getId());

		for (final SaleLine line : lines) {
			final Advertisement ad = line.getAdvertisement();
			if (ad instanceof ExpressAdvertisement)
				result += ((ExpressAdvertisement) ad).getPrice();
			else if (ad instanceof ShopAdvertisement)
				result += line.getAmount() * ((ShopAdvertisement) ad).getPrice();
		}

		return result;
	}

	public void executeBuy(final AuctionAdvertisement auction, final PaymentForm paymentForm) {
		final Ticket ticket = new Ticket();
		ticket.setUser(this.userService.findByPrincipal());
		ticket.setSeller(auction.getUser() != null ? auction.getUser() : null);
		ticket.setBusiness(auction.getBusiness() != null ? auction.getBusiness() : null);
		ticket.setStatus(TicketService.PENDING);
		ticket.setDate(new Date());
		final Ticket savedTicket = this.save(ticket);

		final SaleLine line = new SaleLine();
		line.setAdvertisement(auction);
		line.setAmount(1);
		line.setTicket(savedTicket);
		this.saleLineService.save(line);

		this.bidService.deleteFromAuction(auction);
		auction.setEndDate(new Date());
		this.advertisementService.save(auction);

		final FacturationData fdata = this.facturationDataService.create(paymentForm);
		fdata.setTicket(savedTicket);
		this.facturationDataService.save(fdata);

		final ShipmentAddress saddress = this.shipmentAddressService.create(paymentForm);
		saddress.setTicket(savedTicket);
		this.shipmentAddressService.save(saddress);

		try {
			this.paymentService.payout(auction.getInstantBuyPrice(), auction.getUser() != null ? auction.getUser().getEmailAddress() : auction.getBusiness().getPaypalEmail());
		} catch (final PayPalRESTException e) {
			e.printStackTrace();
		}
	}

	public void executeBuy(final Bid bid, final PaymentForm paymentForm) {
		final AuctionAdvertisement auction = bid.getAuctionAdvertisement();
		final Ticket ticket = new Ticket();
		ticket.setUser(this.userService.findByPrincipal());
		ticket.setSeller(auction.getUser() != null ? auction.getUser() : null);
		ticket.setBusiness(auction.getBusiness() != null ? auction.getBusiness() : null);
		ticket.setStatus(TicketService.PENDING);
		ticket.setDate(new Date());
		final Ticket savedTicket = this.save(ticket);

		final SaleLine line = new SaleLine();
		line.setAdvertisement(auction);
		line.setAmount(1);
		line.setTicket(savedTicket);
		this.saleLineService.save(line);

		this.bidService.deleteFromAuction(auction);

		final FacturationData fdata = this.facturationDataService.create(paymentForm);
		fdata.setTicket(savedTicket);
		this.facturationDataService.save(fdata);

		final ShipmentAddress saddress = this.shipmentAddressService.create(paymentForm);
		saddress.setTicket(savedTicket);
		this.shipmentAddressService.save(saddress);

		try {
			this.paymentService.payout(bid.getAmount(), bid.getAuctionAdvertisement().getUser() != null ? bid.getAuctionAdvertisement().getUser().getEmailAddress() : bid.getAuctionAdvertisement().getBusiness().getPaypalEmail());
		} catch (final PayPalRESTException e) {
			e.printStackTrace();
		}
	}
}
