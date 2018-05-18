
package services;

import java.util.Collection;
import java.util.Date;

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

	public void delete(final ExpressAdvertisement expressAdvertisement) {

		this.expressAdvertisementRepository.delete(expressAdvertisement);

	}

	public ExpressAdvertisement save(final ExpressAdvertisement expressAdvertisement) {
		ExpressAdvertisement result;
		Actor actor;
		Date date;
		Assert.isTrue(!this.configService.isTaboo(expressAdvertisement.getItem().getName()) || !this.configService.isTaboo(expressAdvertisement.getItem().getDescription()), "tabú! >:c ");
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

}
