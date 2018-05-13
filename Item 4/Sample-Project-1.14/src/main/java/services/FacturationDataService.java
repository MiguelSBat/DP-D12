
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

}
