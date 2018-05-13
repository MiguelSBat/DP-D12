
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ShoppingCartRepository;
import domain.ShoppingCart;

@Service
@Transactional
public class ShoppingCartService {

	//Managed Repository ----
	@Autowired
	private ShoppingCartRepository	shoppingCartRepository;


	//Constructors
	public ShoppingCartService() {
		super();
	}

	public ShoppingCart create() {
		ShoppingCart result;

		result = new ShoppingCart();

		return result;
	}

	public Collection<ShoppingCart> findAll() {
		Collection<ShoppingCart> result;

		result = this.shoppingCartRepository.findAll();

		return result;
	}

	public void delete(final ShoppingCart shoppingCart) {

		this.shoppingCartRepository.delete(shoppingCart);

	}

	public ShoppingCart save(final ShoppingCart shoppingCart) {
		ShoppingCart result;

		result = this.shoppingCartRepository.save(shoppingCart);
		return result;
	}

	public ShoppingCart findOne(final int shoppingCartId) {
		ShoppingCart result;

		result = this.shoppingCartRepository.findOne(shoppingCartId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.shoppingCartRepository.flush();
	}

}
