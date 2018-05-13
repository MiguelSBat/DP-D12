
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SaleLineRepository;
import domain.SaleLine;

@Service
@Transactional
public class SaleLineService {

	//Managed Repository ----
	@Autowired
	private SaleLineRepository	saleLineRepository;


	//Constructors
	public SaleLineService() {
		super();
	}

	public SaleLine create() {
		SaleLine result;

		result = new SaleLine();

		return result;
	}

	public Collection<SaleLine> findAll() {
		Collection<SaleLine> result;

		result = this.saleLineRepository.findAll();

		return result;
	}

	public void delete(final SaleLine saleLine) {

		this.saleLineRepository.delete(saleLine);

	}

	public SaleLine save(final SaleLine saleLine) {
		SaleLine result;

		result = this.saleLineRepository.save(saleLine);
		return result;
	}

	public SaleLine findOne(final int saleLineId) {
		SaleLine result;

		result = this.saleLineRepository.findOne(saleLineId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.saleLineRepository.flush();
	}

}
