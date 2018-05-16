
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ExpressAdvertisementRepository;
import domain.ExpressAdvertisement;

@Service
@Transactional
public class ExpressAdvertisementService {

	//Managed Repository ----
	@Autowired
	private ExpressAdvertisementRepository	expressAdvertisementRepository;


	//Constructors
	public ExpressAdvertisementService() {
		super();
	}

	public ExpressAdvertisement create() {
		ExpressAdvertisement result;

		result = new ExpressAdvertisement();

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

		result = this.expressAdvertisementRepository.save(expressAdvertisement);
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
