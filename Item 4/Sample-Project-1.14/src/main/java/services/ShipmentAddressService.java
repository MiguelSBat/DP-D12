
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ShipmentAddressRepository;
import domain.ShipmentAddress;

@Service
@Transactional
public class ShipmentAddressService {

	//Managed Repository ----
	@Autowired
	private ShipmentAddressRepository	shipmentAddressRepository;


	//Constructors
	public ShipmentAddressService() {
		super();
	}

	public ShipmentAddress create() {
		ShipmentAddress result;

		result = new ShipmentAddress();

		return result;
	}

	public Collection<ShipmentAddress> findAll() {
		Collection<ShipmentAddress> result;

		result = this.shipmentAddressRepository.findAll();

		return result;
	}

	public void delete(final ShipmentAddress shipmentAddress) {

		this.shipmentAddressRepository.delete(shipmentAddress);

	}

	public ShipmentAddress save(final ShipmentAddress shipmentAddress) {
		ShipmentAddress result;

		result = this.shipmentAddressRepository.save(shipmentAddress);
		return result;
	}

	public ShipmentAddress findOne(final int shipmentAddressId) {
		ShipmentAddress result;

		result = this.shipmentAddressRepository.findOne(shipmentAddressId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.shipmentAddressRepository.flush();
	}

}
