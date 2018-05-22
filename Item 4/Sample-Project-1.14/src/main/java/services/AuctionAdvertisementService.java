
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AuctionAdvertisementRepository;
import domain.Actor;
import domain.AuctionAdvertisement;
import domain.Business;
import domain.User;

@Service
@Transactional
public class AuctionAdvertisementService {

	//Managed Repository ----
	@Autowired
	private AuctionAdvertisementRepository	auctionAdvertisementRepository;

	@Autowired
	private ActorService					actorService;

	@Autowired
	private ConfigService					configService;


	//Constructors
	public AuctionAdvertisementService() {
		super();
	}

	public AuctionAdvertisement create() {
		AuctionAdvertisement result;

		result = new AuctionAdvertisement();
		result.setPublicationDate(new Date());

		return result;
	}

	public Collection<AuctionAdvertisement> findAll() {
		Collection<AuctionAdvertisement> result;

		result = this.auctionAdvertisementRepository.findAll();

		return result;
	}

	public void delete(final int auctionAdvertisementId) {
		Actor actor;
		AuctionAdvertisement auctionAdvertisement;

		auctionAdvertisement = this.findOne(auctionAdvertisementId);
		Assert.isTrue(auctionAdvertisement != null);
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof User || actor instanceof Business);
		if (actor instanceof User) {
			Assert.isTrue(auctionAdvertisement.getUser().getId() == actor.getId());
			this.auctionAdvertisementRepository.delete(auctionAdvertisement);
		} else {
			Assert.isTrue(auctionAdvertisement.getBusiness().getId() == actor.getId());
			this.auctionAdvertisementRepository.delete(auctionAdvertisement);
		}

	}

	public AuctionAdvertisement save(final AuctionAdvertisement auctionAdvertisement) {
		AuctionAdvertisement result;
		Actor actor;
		Date date;

		Assert.isTrue(this.actorService.isLogged());
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof User || actor instanceof Business);
		date = new Date();
		Assert.isTrue(auctionAdvertisement.getEndDate().after(date));
		Assert.isTrue(!this.hasSpam(auctionAdvertisement));

		auctionAdvertisement.setPublicationDate(date);
		if (actor instanceof User) {
			auctionAdvertisement.setUser((User) actor);
			result = this.auctionAdvertisementRepository.save(auctionAdvertisement);
		} else {
			auctionAdvertisement.setBusiness((Business) actor);
			result = this.auctionAdvertisementRepository.save(auctionAdvertisement);
		}

		return result;
	}

	private boolean hasSpam(final AuctionAdvertisement auctionAdvertisement) {
		boolean result;

		result = false;
		result = (this.configService.isTaboo(auctionAdvertisement.getItem().getName()) || this.configService.isTaboo(auctionAdvertisement.getItem().getDescription()));
		for (final String e : auctionAdvertisement.getTags())
			if (this.configService.isTaboo(e))
				result = true;

		return result;
	}

	public AuctionAdvertisement findOne(final int auctionAdvertisementId) {
		AuctionAdvertisement result;

		result = this.auctionAdvertisementRepository.findOne(auctionAdvertisementId);

		return result;
	}

	public void flush() {
		this.auctionAdvertisementRepository.flush();
	}

	public Collection<AuctionAdvertisement> findByPrincipal() {
		Actor actor;
		Collection<AuctionAdvertisement> result;

		Assert.isTrue(this.actorService.isLogged());
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof User || actor instanceof Business);

		if (actor instanceof User)
			result = this.auctionAdvertisementRepository.findByPrincipalUser(actor.getId());
		else
			result = this.auctionAdvertisementRepository.findByPrincipalBusiness(actor.getId());

		return result;
	}

	public Collection<AuctionAdvertisement> findNotPast() {
		Collection<AuctionAdvertisement> result;

		result = this.auctionAdvertisementRepository.findNotPast();

		return result;
	}

	public Collection<AuctionAdvertisement> findByprincipal() {
		Collection<AuctionAdvertisement> result;
		Actor actor;

		actor = this.actorService.findByPrincipal();
		result = this.auctionAdvertisementRepository.findByUser(actor.getId());

		return result;
	}
}
