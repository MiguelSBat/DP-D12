
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TicketRepository;
import domain.Actor;
import domain.Business;
import domain.SaleLine;
import domain.Ticket;
import domain.User;

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
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.ticketRepository.flush();
	}

	public Collection<Ticket> createAndSaveFromShoppingCartId(final int shoppingCartId) {
		Ticket ticket;
		Ticket ticketAux;
		Collection<Ticket> result;
		Collection<SaleLine> salesLines;
		Collection<Business> business;
		Collection<User> users;
		Actor actor;
		User principal;

		result = new ArrayList<Ticket>();
		business = this.businessService.findByShoppingCartId(shoppingCartId);
		users = this.userService.findByShoppingCartId(shoppingCartId);
		actor = null; //TODO inicializar actor
		principal = (User) actor;

		for (final Business b : business) {
			ticket = this.create();
			ticket.setBusiness(b);
			ticket.setStatus("PENDING");
			ticket.setUser(principal);
			ticket.setDate(new Date());
			ticketAux = this.save(ticket);
			salesLines = this.saleLineService.findByShoppingCartAndBusinessId(shoppingCartId, b.getId());
			for (final SaleLine s : salesLines) {
				s.setShoppingCart(null);
				s.setTicket(ticketAux);
				this.saleLineService.save(s);
			}
			result.add(ticketAux);
		}
		for (final User u : users) {
			ticket = this.create();
			ticket.setSeller(u);
			ticket.setStatus("PENDING");
			ticket.setUser(principal);
			ticket.setDate(new Date());
			ticketAux = this.save(ticket);
			salesLines = this.saleLineService.findFromUserByShoppingCartAndUserId(shoppingCartId, u.getId());
			for (final SaleLine s : salesLines) {
				s.setShoppingCart(null);
				s.setTicket(ticketAux);
				this.saleLineService.save(s);
			}
			result.add(ticketAux);
		}
		return result;
	}
}
