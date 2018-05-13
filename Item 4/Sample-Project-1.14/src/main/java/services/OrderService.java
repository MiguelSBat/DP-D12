
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.OrderRepository;
import domain.Order;

@Service
@Transactional
public class OrderService {

	//Managed Repository ----
	@Autowired
	private OrderRepository	orderRepository;


	//Constructors
	public OrderService() {
		super();
	}

	public Order create() {
		Order result;

		result = new Order();

		return result;
	}

	public Collection<Order> findAll() {
		Collection<Order> result;

		result = this.orderRepository.findAll();

		return result;
	}

	public void delete(final Order order) {

		this.orderRepository.delete(order);

	}

	public Order save(final Order order) {
		Order result;

		result = this.orderRepository.save(order);
		return result;
	}

	public Order findOne(final int orderId) {
		Order result;

		result = this.orderRepository.findOne(orderId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.orderRepository.flush();
	}

}
