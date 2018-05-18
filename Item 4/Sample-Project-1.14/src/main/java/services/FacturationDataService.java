
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FacturationDataRepository;
import domain.FacturationData;

@Service
@Transactional
public class FacturationDataService {

	//Managed Repository ----
	@Autowired
	private FacturationDataRepository	facturationDataRepository;


	//Constructors
	public FacturationDataService() {
		super();
	}

	public FacturationData create() {
		FacturationData result;

		result = new FacturationData();

		return result;
	}
	public FacturationData create(final FacturationData facturationData) {
		FacturationData result;

		result = new FacturationData();
		result.setCity(facturationData.getCity());
		result.setCountry(facturationData.getCountry());
		result.setIDNumber(facturationData.getIDNumber());
		result.setName(facturationData.getName());
		result.setPostalCode(facturationData.getPostalCode());
		result.setSurname(facturationData.getSurname());

		return result;
	}

	public Collection<FacturationData> findAll() {
		Collection<FacturationData> result;

		result = this.facturationDataRepository.findAll();

		return result;
	}

	public void delete(final FacturationData facturationData) {

		this.facturationDataRepository.delete(facturationData);

	}

	public FacturationData save(final FacturationData facturationData) {
		FacturationData result;

		result = this.facturationDataRepository.save(facturationData);
		return result;
	}

	public FacturationData findOne(final int facturationDataId) {
		FacturationData result;

		result = this.facturationDataRepository.findOne(facturationDataId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.facturationDataRepository.flush();
	}
	public Collection<FacturationData> findByUserId(final int id) {
		Collection<FacturationData> result;

		result = this.findByUserId(id);

		return result;
	}
	public FacturationData findByTicketId(final int id) {
		FacturationData result;

		result = this.facturationDataRepository.findByTicketId(id);

		return result;
	}
}
