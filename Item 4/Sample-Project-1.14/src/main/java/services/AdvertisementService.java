
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.AdvertisementRepository;
import domain.Advertisement;

@Service
@Transactional
public class AdvertisementService {

	//Managed Repository ----
	@Autowired
	private AdvertisementRepository	advertisementRepository;


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
}
