
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BidRepository;
import domain.Bid;

@Service
@Transactional
public class BidService {

	//Managed Repository ----
	@Autowired
	private BidRepository	bidRepository;


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

}
