
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TicketRepository;
import domain.Ticket;

@Service
@Transactional
public class TicketService {

	//Managed Repository ----
	@Autowired
	private TicketRepository	ticketRepository;


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

}
