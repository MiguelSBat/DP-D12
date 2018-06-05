
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.AdvertisementRepository;
import domain.Advertisement;
import domain.ExpressAdvertisement;
import domain.SaleLine;
import domain.ShopAdvertisement;
import domain.Ticket;

@Service
@Transactional
public class AdvertisementService {

	//Managed Repository ----
	@Autowired
	private AdvertisementRepository	advertisementRepository;

	@Autowired
	private SaleLineService			saleLineService;


	//Constructors
	public AdvertisementService() {
		super();
	}

	public Advertisement create() {
		Advertisement result;

		result = new Advertisement();

		return result;
	}

	public Collection<Advertisement> findAll() {
		Collection<Advertisement> result;

		result = this.advertisementRepository.findAll();

		return result;
	}

	public void delete(final Advertisement advertisement) {

		this.advertisementRepository.delete(advertisement);

	}

	public Advertisement save(final Advertisement advertisement) {
		Advertisement result;

		result = this.advertisementRepository.save(advertisement);
		return result;
	}

	public Advertisement findOne(final int advertisementId) {
		Advertisement result;

		result = this.advertisementRepository.findOne(advertisementId);

		return result;
	}
	public Collection<Advertisement> findNotPAst() {
		Collection<Advertisement> result;

		result = this.advertisementRepository.findNotPast();

		return result;
	}

	public void flush() {
		this.advertisementRepository.flush();
	}
	public Collection<Advertisement> findByCriteria(final String criteria) {
		Collection<Advertisement> advertisements;
		advertisements = this.advertisementRepository.findByCriteria(criteria);
		return advertisements;
	}
	public Collection<Advertisement> findBySaleLineId(final int id) {
		Collection<Advertisement> result;

		result = this.advertisementRepository.findBySaleLineId(id);

		return result;
	}
	public Collection<Advertisement> findByBusinessId(final int id) {
		Collection<Advertisement> result;

		result = this.advertisementRepository.findByBusinessId(id);

		return result;
	}
	public Collection<Advertisement> findByTicketId(final int id) {
		Collection<Advertisement> result;

		result = this.advertisementRepository.findByTicketId(id);

		return result;
	}
	public Collection<Advertisement> findByBusinessORUser(final int id) {
		Collection<Advertisement> result;

		result = this.advertisementRepository.findByBusinessORUser(id);

		return result;
	}

	public Collection<Advertisement> findByPremium() {
		Collection<Advertisement> result;

		result = this.advertisementRepository.findByPremium();

		return result;
	}

	public Collection<Advertisement> findByPremiumBusiness() {
		Collection<Advertisement> result;

		result = this.advertisementRepository.findByPremiumBusiness();

		return result;
	}

	public Collection<Advertisement> findByPremiumUser() {
		Collection<Advertisement> result;

		result = this.advertisementRepository.findByPremiumUser();

		return result;
	}

	public void executeBuy(final Ticket ticket) {
		Double amount = 0.0;
		for (final SaleLine line : this.saleLineService.findByTicketId(ticket.getId())) {
			final Advertisement ad = line.getAdvertisement();
			if (ad instanceof ExpressAdvertisement) {
				ad.setEndDate(new Date());
				this.save(ad);
				amount += ((ExpressAdvertisement) ad).getPrice();
			}
			if (ad instanceof ShopAdvertisement) {
				((ShopAdvertisement) ad).setStock(((ShopAdvertisement) ad).getStock() - line.getAmount());
				this.save(ad);
				amount += ((ShopAdvertisement) ad).getPrice() * line.getAmount();
			}
		}

	}
}
