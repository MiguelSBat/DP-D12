
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ExpressAdvertisementRepository;
import domain.Actor;
import domain.Advertisement;
import domain.Business;
import domain.Config;
import domain.ExpressAdvertisement;
import domain.User;

@Service
@Transactional
public class ExpressAdvertisementService {

	//Managed Repository ----
	@Autowired
	private ExpressAdvertisementRepository	expressAdvertisementRepository;

	@Autowired
	private ActorService					actorService;

	@Autowired
	private ConfigService					configService;

	@Autowired
	private AdvertisementService			advertisementService;


	//Constructors
	public ExpressAdvertisementService() {
		super();
	}

	public ExpressAdvertisement create() {
		ExpressAdvertisement result;
		result = new ExpressAdvertisement();
		result.setPublicationDate(new Date(System.currentTimeMillis()));

		return result;
	}

	public Collection<ExpressAdvertisement> findAll() {
		Collection<ExpressAdvertisement> result;

		result = this.expressAdvertisementRepository.findAll();

		return result;
	}
	//el delete solo cambia la fecha

	public void delete(final ExpressAdvertisement expressAdvertisement) {

		final Actor a = this.actorService.findByPrincipal();
		if (a instanceof Business) {
			final Business b = (Business) a;
			Assert.isTrue(expressAdvertisement.getBusiness().equals(b));

		}
		if (a instanceof User) {
			final User u = (User) a;
			Assert.isTrue(expressAdvertisement.getUser().equals(u));

		}

		expressAdvertisement.setEndDate(new Date(System.currentTimeMillis()));
		this.expressAdvertisementRepository.save(expressAdvertisement);

	}
	public Boolean isTabooThisExpressAdvertisement(final ExpressAdvertisement expressAdvertisement) {
		boolean tabu = false;
		tabu = (this.configService.isTaboo(expressAdvertisement.getItem().getName()) || this.configService.isTaboo(expressAdvertisement.getItem().getDescription()));

		final Collection<String> tags = expressAdvertisement.getTags();
		for (final String t : tags)
			if (this.configService.isTaboo(t) == true) {
				tabu = true;
				break;
			}

		return tabu;

	}

	public ExpressAdvertisement save(final ExpressAdvertisement expressAdvertisement) {
		ExpressAdvertisement result;
		Actor actor;
		Date date;
		Config config;
		DateTime aux, dt, max;
		User user;
		Business business;
		Collection<Advertisement> advs;

		config = this.configService.findConfiguration();
		dt = new DateTime();
		max = dt.plusMonths(config.getAdvertisementExpirationMonths());
		aux = new DateTime(expressAdvertisement.getEndDate());

		Assert.isTrue(max.isAfter(aux), "advertisement.maxTimeAllowed");

		Assert.isTrue(!this.isTabooThisExpressAdvertisement(expressAdvertisement), "ExpressAdvertisement.tabuError");
		Assert.isTrue(this.actorService.isLogged());
		actor = this.actorService.findByPrincipal();
		advs = this.advertisementService.findByActorActive(actor);
		Assert.isTrue(actor instanceof User || actor instanceof Business);
		Assert.isTrue(!actor.getSoftBan(), "advertisement.softBanError");
		date = new Date();
		Assert.isTrue(expressAdvertisement.getEndDate().after(date));
		expressAdvertisement.setPublicationDate(new Date(System.currentTimeMillis()));

		expressAdvertisement.setPublicationDate(date);
		if (actor instanceof User) {
			expressAdvertisement.setUser((User) actor);
			user = (User) actor;
			Assert.isTrue(expressAdvertisement.getItem().getUser() != null && expressAdvertisement.getItem().getUser().getId() == actor.getId());
			if (user.isPremium())
				Assert.isTrue(advs.size() < config.getPremiumMaxAdvertisements(), "advertisement.maxAdvPError");
			else
				Assert.isTrue(advs.size() < config.getUserMaxAdvertisements(), "advertisement.maxAdvError");
			;
			result = this.expressAdvertisementRepository.save(expressAdvertisement);
		} else {
			expressAdvertisement.setBusiness((Business) actor);
			business = (Business) actor;
			Assert.isTrue(expressAdvertisement.getItem().getBusiness() != null && expressAdvertisement.getItem().getBusiness().getId() == actor.getId());
			if (business.getPremium())
				Assert.isTrue(advs.size() < config.getPremiumMaxAdvertisements(), "advertisement.maxAdvPError");
			else
				Assert.isTrue(advs.size() < config.getBusinessMaxAdvertisements(), "advertisement.maxAdvError");
			result = this.expressAdvertisementRepository.save(expressAdvertisement);
		}

		return result;
	}
	public ExpressAdvertisement findOne(final int expressAdvertisementId) {
		ExpressAdvertisement result;

		result = this.expressAdvertisementRepository.findOne(expressAdvertisementId);

		return result;
	}

	public void flush() {
		this.expressAdvertisementRepository.flush();
	}
	public Collection<ExpressAdvertisement> findNotPast() {
		Collection<ExpressAdvertisement> result;

		result = this.expressAdvertisementRepository.findNotPast();

		return result;
	}
	public Collection<ExpressAdvertisement> findExpressByBussiness(final int businessId) {
		Collection<ExpressAdvertisement> result;

		result = this.expressAdvertisementRepository.findExpressbyBusiness(businessId);

		return result;
	}
	public Collection<ExpressAdvertisement> findExpressByUser(final int UserId) {
		Collection<ExpressAdvertisement> result;

		result = this.expressAdvertisementRepository.findExpressbyUser(UserId);

		return result;
	}

	public Double avgPriceExp() {
		Double result;
		result = this.expressAdvertisementRepository.avgPriceExp();
		return result;
	}

}
