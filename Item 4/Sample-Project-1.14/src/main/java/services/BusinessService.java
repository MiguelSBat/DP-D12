
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BusinessRepository;
import domain.Actor;
import domain.Answer;
import domain.Business;
import domain.BusinessInfo;
import forms.ActorForm;

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
		//Tener en cuenta que es llamado desde businessInfoService linea 64
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

	public Collection<Business> findByShoppingCartId(final int id) {
		Collection<Business> result;

		result = this.findByShoppingCartId(id);

		return result;
	}

	public Collection<Business> findBySaleLineId(final int id) {
		Collection<Business> result;

		result = this.findBySaleLineId(id);

		return result;
	}

	public Collection<Business> findNotVerified() {
		Collection<Business> result;

		result = this.businessRepository.findNotVerified();

		return result;
	}

	public Actor create(final ActorForm form) {
		final Business result = new Business();

		result.setAnswers(new HashSet<Answer>());
		result.setBusinessInfos(new HashSet<BusinessInfo>());
		result.setName(form.getName());
		result.setPaypalEmail(form.getPaypalEmail());
		result.setPhotosURL(new HashSet<String>());
		result.setPremium(false);
		result.setReputation(3);
		result.setSuspicious(false);
		result.setVATNumber(form.getVATNumber());
		result.setVerified(false);

		return result;
	}
	public Collection<Business> findBusinessIbuyThings(final int userId) {
		Collection<Business> result;

		result = this.businessRepository.findBusinessIbuyThings(userId);

		return result;
	}

	public Collection<Business> topFiveBusiness() {
		final Collection<Business> res = new ArrayList<>();
		List<Business> aux;
		aux = this.businessRepository.topFiveBusiness();
		int n = aux.size();
		if (n > 0)
			for (int i = 0; i < aux.size(); i++) {
				res.add(aux.get(i));
				n++;
				if (n == 5)
					break;
			}

		return res;
	}

}
