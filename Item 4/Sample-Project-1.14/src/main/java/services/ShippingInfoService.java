
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ShippingInfoRepository;
import domain.ShippingInfo;

@Service
@Transactional
public class ShippingInfoService {

	//Managed Repository ----
	@Autowired
	private ShippingInfoRepository	shippingInfoRepository;


	//Constructors
	public ShippingInfoService() {
		super();
	}

	public ShippingInfo create() {
		ShippingInfo result;

		result = new ShippingInfo();

		return result;
	}

	public Collection<ShippingInfo> findAll() {
		Collection<ShippingInfo> result;

		result = this.shippingInfoRepository.findAll();

		return result;
	}

	public void delete(final ShippingInfo shippingInfo) {

		this.shippingInfoRepository.delete(shippingInfo);

	}

	public ShippingInfo save(final ShippingInfo shippingInfo) {
		ShippingInfo result;

		result = this.shippingInfoRepository.save(shippingInfo);
		return result;
	}

	public ShippingInfo findOne(final int shippingInfoId) {
		ShippingInfo result;

		result = this.shippingInfoRepository.findOne(shippingInfoId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.shippingInfoRepository.flush();
	}
	public ShippingInfo findByTicketId(final int id) {
		ShippingInfo result;

		result = this.shippingInfoRepository.findByTicketId(id);

		return result;
	}

}
