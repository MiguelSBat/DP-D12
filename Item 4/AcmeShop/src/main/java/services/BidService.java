
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BidRepository;
import domain.Actor;
import domain.AuctionAdvertisement;
import domain.Bid;
import domain.User;

@Service
@Transactional
public class BidService {

	//Managed Repository ----
	@Autowired
	private BidRepository				bidRepository;

	@Autowired
	private AuctionAdvertisementService	auctionAdvertisementService;

	@Autowired
	private ActorService				actorService;


	//Constructors
	public BidService() {
		super();
	}

	public Bid create() {
		Bid result;

		result = new Bid();

		return result;
	}

	public Collection<Bid> findAll() {
		Collection<Bid> result;

		result = this.bidRepository.findAll();

		return result;
	}

	public void delete(final Bid bid) {

		this.bidRepository.delete(bid);

	}

	public Bid save(final Bid bid) {
		Bid result;

		result = this.bidRepository.save(bid);
		return result;
	}

	public Bid findOne(final int bidId) {
		Bid result;

		result = this.bidRepository.findOne(bidId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.bidRepository.flush();
	}

	public Collection<Bid> findOrderedByAuction(final int auctionAdvertisementId) {
		Collection<Bid> result;

		result = this.bidRepository.findOrderedByAuction(auctionAdvertisementId);

		return result;
	}

	public Bid createAndSave(final int auctionAdvertisementId, final Double amount) {
		Bid bid, result;
		AuctionAdvertisement auctionAdvertisement;
		Actor actor;

		auctionAdvertisement = this.auctionAdvertisementService.findOne(auctionAdvertisementId);
		Assert.isTrue(auctionAdvertisement != null && auctionAdvertisement.getEndDate().after(new Date()));
		Assert.isTrue(this.actorService.isLogged());
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof User);
		Assert.isTrue(auctionAdvertisement.getStartingPrice() < amount, "error.minimumBid");

		bid = this.create();
		bid.setAmount(amount);
		bid.setAuctionAdvertisement(auctionAdvertisement);
		bid.setUser((User) actor);
		result = this.bidRepository.save(bid);

		return result;
	}

	public Collection<Bid> findByAuctionAndUser(final int userId, final int auctionId) {
		final Collection<Bid> result;

		result = this.bidRepository.findByAuctionAndUser(userId, auctionId);

		return result;
	}

	public void deleteFromAuction(final AuctionAdvertisement auction) {
		for (final Bid b : this.findByAuction(auction.getId()))
			this.delete(b);
	}

	private Collection<Bid> findByAuction(final int id) {
		return this.bidRepository.findByAuction(id);
	}

	public Bid findHighest(final int auctionId) {
		return this.bidRepository.findHighest(auctionId);
	}
}
