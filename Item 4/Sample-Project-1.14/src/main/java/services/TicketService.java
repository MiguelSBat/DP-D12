
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TicketRepository;
import domain.Actor;
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

	public static final String		PENDING		= "pending";
	public static final String		SENT		= "sent";
	public static final String		RECEIVED	= "received";
	public static final String		CANCELLED	= "cancelled";


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
					if (t.getSeller().equals(ad.getUser())) {
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
					if (t.getBusiness().equals(ad.getBusiness())) {
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
		if (status.equals("SENT") || status.equals("CANCELLED"))
			Assert.isTrue(ticket.getBusiness().getId() == principal.getId() || ticket.getSeller().getId() == principal.getId());
		else if (status.equals("RECEIVED"))
			Assert.isTrue(ticket.getUser().getId() == principal.getId());
		else
			Assert.isTrue(false);

		ticket.setStatus(status);
		result = this.save(ticket);
		return result;
	}
}
