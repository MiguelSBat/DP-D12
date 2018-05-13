
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BusinessInfoRepository;
import domain.BusinessInfo;

@Service
@Transactional
public class BusinessInfoService {

	//Managed Repository ----
	@Autowired
	private BusinessInfoRepository	businessInfoRepository;


	//Constructors
	public BusinessInfoService() {
		super();
	}

	public BusinessInfo create() {
		BusinessInfo result;

		result = new BusinessInfo();

		return result;
	}

	public Collection<BusinessInfo> findAll() {
		Collection<BusinessInfo> result;

		result = this.businessInfoRepository.findAll();

		return result;
	}

	public void delete(final BusinessInfo businessInfo) {

		this.businessInfoRepository.delete(businessInfo);

	}

	public BusinessInfo save(final BusinessInfo businessInfo) {
		BusinessInfo result;

		result = this.businessInfoRepository.save(businessInfo);
		return result;
	}

	public BusinessInfo findOne(final int businessInfoId) {
		BusinessInfo result;

		result = this.businessInfoRepository.findOne(businessInfoId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.businessInfoRepository.flush();
	}

}
