
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

	@Autowired
	private ActorService			actorService;

	@Autowired
	private FacturationDataService	facturationDataService;

	@Autowired
	private ShipmentAddressService	shipmentAddressService;


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
		//		FacturationData facturationData;
		//		FacturationData facturationDataAux;
		//		ShipmentAddress shipmentAddress;
		//		ShipmentAddress shipmentAddressAux;
		Actor actor;
		User principal;

		result = new ArrayList<Ticket>();
		business = this.businessService.findByShoppingCartId(shoppingCartId);
		users = this.userService.findByShoppingCartId(shoppingCartId);
		actor = this.actorService.findByPrincipal();
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
			//			//Datos Facturaci�n1 TODO: Esto ir�a en una clase formulario y no aqu�, se rellenar�a por defecto con estos datos y ya
			//			facturationDataAux = this.facturationDataService.findByUserId(principal.getId()).iterator().next();
			//			if (facturationDataAux != null)
			//				facturationData = this.facturationDataService.create(facturationDataAux);
			//			else
			//				facturationData = this.facturationDataService.create();
			//			facturationData.setTicket(ticketAux);
			//			facturationData = this.facturationDataService.save(facturationData);
			//			//Direcci�n de env�o
			//			shipmentAddressAux = this.shipmentAddressService.findByUserId(principal.getId()).iterator().next();
			//			if (shipmentAddressAux != null)
			//				shipmentAddress = this.shipmentAddressService.create(shipmentAddressAux);
			//			else
			//				shipmentAddress = this.shipmentAddressService.create();
			//			shipmentAddress.setTicket(ticketAux);
			//			shipmentAddress = this.shipmentAddressService.save(shipmentAddress);

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
			//			//Datos Facturaci�n2 TODO: Esto ir�a en una clase formulario y no aqu�, se rellenar�a por defecto con estos datos y ya
			//			facturationDataAux = this.facturationDataService.findByUserId(principal.getId()).iterator().next();
			//			if (facturationDataAux != null)
			//				facturationData = this.facturationDataService.create(facturationDataAux);
			//			else
			//				facturationData = this.facturationDataService.create();
			//			facturationData.setTicket(ticketAux);
			//			facturationData = this.facturationDataService.save(facturationData);
			//			//Direcci�n de env�o2
			//			shipmentAddressAux = this.shipmentAddressService.findByUserId(principal.getId()).iterator().next();
			//			if (shipmentAddressAux != null)
			//				shipmentAddress = this.shipmentAddressService.create(shipmentAddressAux);
			//			else
			//				shipmentAddress = this.shipmentAddressService.create();
			//			shipmentAddress.setTicket(ticketAux);
			//			shipmentAddress = this.shipmentAddressService.save(shipmentAddress);

			result.add(ticketAux);
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
		if (status == "SENT" || status == "CANCELLED")
			Assert.isTrue(ticket.getBusiness().getId() == principal.getId() || ticket.getSeller().getId() == principal.getId());
		else if (status == "RECEIVED")
			Assert.isTrue(ticket.getUser().getId() == principal.getId());

		ticket.setStatus(status);
		result = this.save(ticket);
		return result;
	}
}
