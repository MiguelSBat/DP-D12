
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ShoppingCartRepository;
import domain.Advertisement;
import domain.ExpressAdvertisement;
import domain.SaleLine;
import domain.ShopAdvertisement;
import domain.ShoppingCart;
import domain.User;

@Service
@Transactional
public class ShoppingCartService {

	//Managed Repository ----
	@Autowired
	private ShoppingCartRepository	shoppingCartRepository;

	@Autowired
	private SaleLineService			saleLineService;

	@Autowired
	private UserService				userService;


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

	public ShoppingCart findByUserOrCreate(final User user) {
		ShoppingCart result;

		result = this.shoppingCartRepository.findByUser(user.getId());
		if (result == null)
			result = this.create(user);

		return result;
	}

	private ShoppingCart create(final User user) {
		final ShoppingCart cart = new ShoppingCart();

		cart.setUser(user);
		final ShoppingCart result = this.save(cart);

		return result;
	}

	public Integer getAmountInCart(final ShoppingCart cart, final Advertisement ad) {
		Integer result = 0;

		for (final SaleLine line : this.saleLineService.findByShoppingCart(cart))
			if (line.getAdvertisement().equals(ad))
				result += line.getAmount();

		return result;
	}

	public ShoppingCart findByPrincipalOrCreate() {
		final User user = this.userService.findByPrincipal();
		return this.findByUserOrCreate(user);
	}

	public Double getTotal() {
		Double result = 0.0;
		final Collection<SaleLine> lines = this.saleLineService.findByPrincipal();

		for (final SaleLine line : lines) {
			final Advertisement ad = line.getAdvertisement();
			if (ad instanceof ExpressAdvertisement)
				result += ((ExpressAdvertisement) ad).getPrice() * line.getAmount();
			else if (ad instanceof ShopAdvertisement)
				result += ((ShopAdvertisement) ad).getPrice() * line.getAmount();
		}

		return result;
	}

	public void remove() {
		this.delete(this.findByPrincipalOrCreate());
	}
}
