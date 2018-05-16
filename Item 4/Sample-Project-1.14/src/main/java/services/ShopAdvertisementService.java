
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ShopAdvertisementRepository;
import domain.ShopAdvertisement;

@Service
@Transactional
public class ShopAdvertisementService {

	//Managed Repository ----
	@Autowired
	private ShopAdvertisementRepository	shopAdvertisementRepository;


	//Constructors
	public ShopAdvertisementService() {
		super();
	}

	public ShopAdvertisement create() {
		ShopAdvertisement result;

		result = new ShopAdvertisement();

		return result;
	}

	public Collection<ShopAdvertisement> findAll() {
		Collection<ShopAdvertisement> result;

		result = this.shopAdvertisementRepository.findAll();

		return result;
	}

	public void delete(final ShopAdvertisement shopAdvertisement) {

		this.shopAdvertisementRepository.delete(shopAdvertisement);

	}

	public ShopAdvertisement save(final ShopAdvertisement shopAdvertisement) {
		ShopAdvertisement result;

		result = this.shopAdvertisementRepository.save(shopAdvertisement);
		return result;
	}

	public ShopAdvertisement findOne(final int shopAdvertisementId) {
		ShopAdvertisement result;

		result = this.shopAdvertisementRepository.findOne(shopAdvertisementId);

		return result;
	}

	public void flush() {
		this.shopAdvertisementRepository.flush();
	}

}
