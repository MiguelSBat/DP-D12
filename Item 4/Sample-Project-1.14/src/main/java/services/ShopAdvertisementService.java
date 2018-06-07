
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ShopAdvertisementRepository;
import domain.Actor;
import domain.Advertisement;
import domain.Business;
import domain.Config;
import domain.Question;
import domain.ShopAdvertisement;

@Service
@Transactional
public class ShopAdvertisementService {

	//Managed Repository ----
	@Autowired
	private ShopAdvertisementRepository	shopAdvertisementRepository;

	@Autowired
	private ActorService				actorService;
	@Autowired
	private ConfigService				configService;
	@Autowired
	private AdvertisementService		advertisementService;


	//Constructors
	public ShopAdvertisementService() {
		super();
	}

	public ShopAdvertisement create() {
		ShopAdvertisement result;

		result = new ShopAdvertisement();
		result.setPublicationDate(new Date(System.currentTimeMillis()));

		return result;
	}

	public Collection<ShopAdvertisement> findAll() {
		Collection<ShopAdvertisement> result;

		result = this.shopAdvertisementRepository.findAll();

		return result;
	}

	//el delete solo cambia la fecha
	public void delete(final int shopAdvertisementId) {
		Actor actor;
		ShopAdvertisement shopAdvertisement;

		shopAdvertisement = this.findOne(shopAdvertisementId);
		Assert.isTrue(shopAdvertisement != null);
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof Business);
		Assert.isTrue(shopAdvertisement.getBusiness().getId() == actor.getId());
		shopAdvertisement.setEndDate(new Date(System.currentTimeMillis()));
		this.shopAdvertisementRepository.save(shopAdvertisement);
	}

	public Boolean isTabooThisShopAdvertisement(final ShopAdvertisement shopAdvertisement) {
		boolean tabu = false;
		tabu = (this.configService.isTaboo(shopAdvertisement.getItem().getName()) || this.configService.isTaboo(shopAdvertisement.getItem().getDescription()));

		final Collection<String> tags = shopAdvertisement.getTags();
		for (final String t : tags)
			if (this.configService.isTaboo(t) == true) {
				tabu = true;
				break;
			}
		return tabu;
	}

	public ShopAdvertisement save(final ShopAdvertisement shopAdvertisement) {
		ShopAdvertisement result;

		Actor actor;
		Date date;
		Config config;
		Business business;
		Collection<Advertisement> advs;
		config = this.configService.findConfiguration();
		final DateTime dt = new DateTime();
		final DateTime max = dt.plusMonths(config.getAdvertisementExpirationMonths());

		final DateTime aux = new DateTime(shopAdvertisement.getEndDate());

		Assert.isTrue(max.isAfter(aux), "advertisement.maxTimeAllowed");

		Assert.isTrue(!this.isTabooThisShopAdvertisement(shopAdvertisement), "shopAdvertisement.tabuError");
		Assert.isTrue(this.actorService.isLogged());
		actor = this.actorService.findByPrincipal();
		advs = this.advertisementService.findByActorActive(actor);
		Assert.isTrue(shopAdvertisement.getItem().getBusiness().getId() == actor.getId());

		Assert.isTrue(actor instanceof Business);
		business = (Business) actor;

		if (business.getPremium())
			Assert.isTrue(advs.size() < config.getPremiumMaxAdvertisements(), "advertisement.maxAdvPError");
		else
			Assert.isTrue(advs.size() < config.getBusinessMaxAdvertisements(), "advertisement.maxAdvError");

		Assert.isTrue(!actor.getSoftBan(), "advertisement.softBanError");
		date = new Date();

		Assert.isTrue(shopAdvertisement.getEndDate().after(date), "advertisement.futureError");
		Assert.isTrue(shopAdvertisement.getStock() > 0, "shopAdvertisement.stockError");
		shopAdvertisement.setPublicationDate(date);
		shopAdvertisement.setBusiness((Business) actor);
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

	public Collection<ShopAdvertisement> findNotPast() {
		Collection<ShopAdvertisement> result;

		result = this.shopAdvertisementRepository.findNotPast();

		return result;
	}
	public Collection<ShopAdvertisement> findShopByBussiness(final int businessId) {
		Collection<ShopAdvertisement> result;

		result = this.shopAdvertisementRepository.findShopbyBusiness(businessId);

		return result;
	}

	public void deleteQuestion(final Question question) {
		ShopAdvertisement shopAdvertisement;

		shopAdvertisement = this.findOne(question.getShopAdvertisement().getId());

		shopAdvertisement.removeQuestion(question);
		this.shopAdvertisementRepository.save(shopAdvertisement);
	}

	public Double avgStockShop() {
		Double result;
		result = this.shopAdvertisementRepository.avgStockShop();
		return result;
	}

	public void addQuestion(final Question question) {
		ShopAdvertisement shopAdvertisement;

		shopAdvertisement = this.findOne(question.getShopAdvertisement().getId());

		shopAdvertisement.addQuestion(question);
		this.shopAdvertisementRepository.save(shopAdvertisement);
	}

}
