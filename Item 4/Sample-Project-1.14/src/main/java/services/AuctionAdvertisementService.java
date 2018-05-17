
package services;

import java.util.Collection;

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


	//Constructors
	public AuctionAdvertisementService() {
		super();
	}

	public AuctionAdvertisement create() {
		AuctionAdvertisement result;

		result = new AuctionAdvertisement();

		return result;
	}

	public Collection<AuctionAdvertisement> findAll() {
		Collection<AuctionAdvertisement> result;

		result = this.auctionAdvertisementRepository.findAll();

		return result;
	}

	public void delete(final AuctionAdvertisement auctionAdvertisement) {

		this.auctionAdvertisementRepository.delete(auctionAdvertisement);

	}

	public AuctionAdvertisement save(final AuctionAdvertisement auctionAdvertisement) {
		AuctionAdvertisement result;
		Actor actor;

		Assert.isTrue(this.actorService.isLogged());
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof User || actor instanceof Business);
		if (actor instanceof User) {
			auctionAdvertisement.setUser((User) actor);
			result = this.auctionAdvertisementRepository.save(auctionAdvertisement);
		} else {
			auctionAdvertisement.setBusiness((Business) actor);
			result = this.auctionAdvertisementRepository.save(auctionAdvertisement);
		}

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

}
