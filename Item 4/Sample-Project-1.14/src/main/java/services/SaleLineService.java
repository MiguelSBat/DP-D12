
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SaleLineRepository;
import domain.Advertisement;
import domain.AuctionAdvertisement;
import domain.ExpressAdvertisement;
import domain.SaleLine;
import domain.ShopAdvertisement;
import domain.ShoppingCart;

@Service
@Transactional
public class SaleLineService {

	static final String				ADDED			= "added";
	static final String				OUT_OF_STOCK	= "outOfStock";
	static final String				INVALID_AMOUNT	= "invalidAmount";
	static final String				ERROR			= "error";

	//Managed Repository ----
	@Autowired
	private SaleLineRepository		saleLineRepository;

	@Autowired
	private AdvertisementService	advertisementService;

	@Autowired
	private ShoppingCartService		shoppingCartService;

	@Autowired
	private UserService				userService;


	//Constructors
	public SaleLineService() {
		super();
	}

	public SaleLine create() {
		SaleLine result;

		result = new SaleLine();

		return result;
	}

	public String create(final int adId, final Integer amount) {
		SaleLine line;

		final Advertisement ad = this.advertisementService.findOne(adId);
		final ShoppingCart cart = this.shoppingCartService.findByUserOrCreate(this.userService.findByPrincipal());
		if (ad instanceof ExpressAdvertisement) {
			if (amount != 1)
				return SaleLineService.INVALID_AMOUNT;
			if (this.shoppingCartService.getAmountInCart(cart, ad) > 0)
				return SaleLineService.OUT_OF_STOCK;
		}
		if (ad instanceof ShopAdvertisement) {
			if (amount < 1)
				return SaleLineService.INVALID_AMOUNT;
			if ((amount + this.shoppingCartService.getAmountInCart(cart, ad)) > ((ShopAdvertisement) ad).getStock())
				return SaleLineService.OUT_OF_STOCK;
		}
		if (ad instanceof AuctionAdvertisement)
			return SaleLineService.ERROR;

		line = new SaleLine();
		line.setAdvertisement(ad);
		line.setAmount(amount);
		line.setShoppingCart(cart);

		line = this.save(line);

		return SaleLineService.ADDED;
	}
	public Collection<SaleLine> findAll() {
		Collection<SaleLine> result;

		result = this.saleLineRepository.findAll();

		return result;
	}

	public void delete(final SaleLine saleLine) {

		this.saleLineRepository.delete(saleLine);

	}

	public SaleLine save(final SaleLine saleLine) {
		SaleLine result;

		result = this.saleLineRepository.save(saleLine);
		return result;
	}

	public SaleLine findOne(final int saleLineId) {
		SaleLine result;

		result = this.saleLineRepository.findOne(saleLineId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.saleLineRepository.flush();
	}

	public Collection<SaleLine> findFromBusinessByShoppingCartId(final int id) {
		Collection<SaleLine> result;

		result = this.saleLineRepository.findFromBusinessByShoppingCartId(id);

		return result;
	}
	public Collection<SaleLine> findByShoppingCartAndBusinessId(final int shoppingCartId, final int businessId) {
		Collection<SaleLine> result;

		result = this.saleLineRepository.findByShoppingCartAndBusinessId(shoppingCartId, businessId);

		return result;
	}
	public Collection<SaleLine> findFromAuctionByShoppingCartAndUserId(final int shoppingCartId, final int userId) {
		Collection<SaleLine> result;

		result = this.saleLineRepository.findFromAuctionByShoppingCartAndUserId(shoppingCartId, userId);

		return result;
	}
	public Collection<SaleLine> findFromExpressByShoppingCartAndUserId(final int shoppingCartId, final int userId) {
		Collection<SaleLine> result;

		result = this.saleLineRepository.findFromExpressByShoppingCartAndUserId(shoppingCartId, userId);

		return result;
	}
	public Collection<SaleLine> findFromUserByShoppingCartAndUserId(final int shoppingCartId, final int userId) {
		Collection<SaleLine> result;

		result = this.findFromAuctionByShoppingCartAndUserId(shoppingCartId, userId);
		result.addAll(this.findFromExpressByShoppingCartAndUserId(shoppingCartId, userId));

		return result;
	}
	public Collection<SaleLine> findByTicketId(final int id) {
		Collection<SaleLine> result;

		result = this.saleLineRepository.findByTicketId(id);

		return result;
	}

	public Collection<SaleLine> findByShoppingCart(final ShoppingCart shoppingCart) {
		return this.findByShoppingCartId(shoppingCart.getId());
	}

	private Collection<SaleLine> findByShoppingCartId(final int id) {
		return this.saleLineRepository.findByShoppingCartId(id);
	}

	public Collection<SaleLine> findByPrincipal() {
		return this.findByShoppingCart(this.shoppingCartService.findByPrincipalOrCreate());
	}

	public Double getTotalAmount(final Collection<SaleLine> saleLines) {
		Double result = 0.0;

		for (final SaleLine line : saleLines)
			if (line.getAdvertisement() instanceof ExpressAdvertisement)
				result += ((ExpressAdvertisement) line.getAdvertisement()).getPrice() * line.getAmount();
			else if (line.getAdvertisement() instanceof ShopAdvertisement)
				result += ((ShopAdvertisement) line.getAdvertisement()).getPrice() * line.getAmount();

		return result;
	}

	public void deleteAll() {
		final Collection<SaleLine> lines = this.findByPrincipal();
		for (final SaleLine line : lines)
			this.delete(line);
	}

	public void delete(final int saleLine) {
		this.delete(this.findOne(saleLine));
	}

}
