
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BusinessRepository;
import domain.Business;

@Service
@Transactional
public class BusinessService {

	//Managed Repository ----
	@Autowired
	private BusinessRepository	businessRepository;


	//Constructors
	public BusinessService() {
		super();
	}

	public Business create() {
		Business result;

		result = new Business();

		return result;
	}

	public Collection<Business> findAll() {
		Collection<Business> result;

		result = this.businessRepository.findAll();

		return result;
	}

	public void delete(final Business business) {

		this.businessRepository.delete(business);

	}

	public Business save(final Business business) {
		Business result;

		result = this.businessRepository.save(business);
		return result;
	}

	public Business findOne(final int businessId) {
		Business result;

		result = this.businessRepository.findOne(businessId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.businessRepository.flush();
	}

}
