
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AuctionAdvertisementRepository;
import domain.Actor;
import domain.Advertisement;
import domain.AuctionAdvertisement;
import domain.Bid;
import domain.Business;
import domain.Config;
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

	@Autowired
	private BidService						bidService;

	@Autowired
	private UserService						userService;

	@Autowired
	private AdvertisementService			advertisementService;


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
		Config config;
		final User user;
		final Business business;
		DateTime aux, dt, max;
		final Collection<Advertisement> advs;

		config = this.configService.findConfiguration();
		dt = new DateTime();
		max = dt.plusMonths(config.getAdvertisementExpirationMonths());
		aux = new DateTime(auctionAdvertisement.getEndDate());

		Assert.isTrue(max.isAfter(aux), "advertisement.maxTimeAllowed");

		Assert.isTrue(this.actorService.isLogged());
		actor = this.actorService.findByPrincipal();
		advs = this.advertisementService.findByActorActive(actor);
		Assert.isTrue(actor instanceof User || actor instanceof Business);
		Assert.isTrue(!actor.getSoftBan(), "advertisement.softBanError");
		date = new Date();
		Assert.isTrue(auctionAdvertisement.getEndDate().after(date));
		Assert.isTrue(!this.hasSpam(auctionAdvertisement));

		auctionAdvertisement.setPublicationDate(date);
		if (actor instanceof User) {
			auctionAdvertisement.setUser((User) actor);
			user = (User) actor;

			if (user.isPremium())
				Assert.isTrue(advs.size() < config.getPremiumMaxAdvertisements(), "advertisement.maxAdvPError");
			else
				Assert.isTrue(advs.size() < config.getUserMaxAdvertisements(), "advertisement.maxAdvError");
			result = this.auctionAdvertisementRepository.save(auctionAdvertisement);

		} else {
			auctionAdvertisement.setBusiness((Business) actor);
			business = (Business) actor;

			if (business.getPremium())
				Assert.isTrue(advs.size() < config.getPremiumMaxAdvertisements(), "advertisement.maxAdvPError");
			else
				Assert.isTrue(advs.size() < config.getBusinessMaxAdvertisements(), "advertisement.maxAdvError");
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

	public boolean isBiddable(final Advertisement advertisement) {
		AuctionAdvertisement auctionAdvertisement;
		boolean biddable;
		Actor actor;
		Collection<Bid> bids;

		Assert.isTrue(advertisement instanceof AuctionAdvertisement);
		auctionAdvertisement = (AuctionAdvertisement) advertisement;
		biddable = false;
		if (this.actorService.isLogged()) {
			actor = this.actorService.findByPrincipal();
			if (auctionAdvertisement.getEndDate().after(new Date()))
				if ((auctionAdvertisement.getUser() != null && actor.getId() != auctionAdvertisement.getUser().getId()) || (auctionAdvertisement.getBusiness() != null && actor.getId() != auctionAdvertisement.getBusiness().getId()))
					if (!auctionAdvertisement.isSecret())
						biddable = true;
					else {
						bids = this.bidService.findByAuctionAndUser(actor.getId(), auctionAdvertisement.getId());
						if (bids.size() == 0)
							biddable = true;
					}
		}

		return biddable;
	}

	public Collection<AuctionAdvertisement> findWonAuctions() {
		final User user = this.userService.findByPrincipal();
		final Collection<AuctionAdvertisement> finished = this.findFinished();
		final Collection<AuctionAdvertisement> result = new HashSet<>();
		for (final AuctionAdvertisement ad : finished) {
			final Bid bid = this.bidService.findHighest(ad.getId());
			if (bid != null && bid.getUser().equals(user))
				result.add(ad);
		}
		return result;
	}

	private Collection<AuctionAdvertisement> findFinished() {
		return this.auctionAdvertisementRepository.findFinished();
	}
}
