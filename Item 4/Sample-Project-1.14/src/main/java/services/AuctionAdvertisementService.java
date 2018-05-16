
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.AuctionAdvertisementRepository;
import domain.AuctionAdvertisement;

@Service
@Transactional
public class AuctionAdvertisementService {

	//Managed Repository ----
	@Autowired
	private AuctionAdvertisementRepository	auctionAdvertisementRepository;


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

		result = this.auctionAdvertisementRepository.save(auctionAdvertisement);
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

}
