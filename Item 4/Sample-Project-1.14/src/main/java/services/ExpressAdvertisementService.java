
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ExpressAdvertisementRepository;
import domain.Actor;
import domain.Business;
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
		Collection<ExpressAdvertisement> advertisements = new HashSet<>();
		final Actor a = this.actorService.findByPrincipal();
		if (a instanceof Business) {
			final Business b = (Business) a;
			advertisements = this.findExpressByBussiness(b.getId());

		}
		if (a instanceof User) {
			final User u = (User) a;
			advertisements = this.findExpressByUser(u.getId());

		}
		Assert.isTrue(advertisements.contains(expressAdvertisement));
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
		//TODO: bug Intencional
		//return tabu;
		return false;
	}

	public ExpressAdvertisement save(final ExpressAdvertisement expressAdvertisement) {
		ExpressAdvertisement result;
		Actor actor;
		Date date;

		Assert.isTrue(!this.isTabooThisExpressAdvertisement(expressAdvertisement), "ExpressAdvertisement.tabuError");
		Assert.isTrue(this.actorService.isLogged());
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof User || actor instanceof Business);
		date = new Date();
		Assert.isTrue(expressAdvertisement.getEndDate().after(date));
		expressAdvertisement.setPublicationDate(new Date(System.currentTimeMillis()));

		expressAdvertisement.setPublicationDate(date);
		if (actor instanceof User) {
			expressAdvertisement.setUser((User) actor);
			result = this.expressAdvertisementRepository.save(expressAdvertisement);
		} else {
			expressAdvertisement.setBusiness((Business) actor);
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
