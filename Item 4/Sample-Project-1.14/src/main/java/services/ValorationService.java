
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ValorationRepository;
import domain.Valoration;

@Service
@Transactional
public class ValorationService {

	//Managed Repository ----
	@Autowired
	private ValorationRepository	valorationRepository;


	//Constructors
	public ValorationService() {
		super();
	}

	public Valoration create() {
		Valoration result;

		result = new Valoration();

		return result;
	}

	public Collection<Valoration> findAll() {
		Collection<Valoration> result;

		result = this.valorationRepository.findAll();

		return result;
	}

	public void delete(final Valoration valoration) {

		this.valorationRepository.delete(valoration);

	}

	public Valoration save(final Valoration valoration) {
		Valoration result;
		valoration.setDate(new Date());
		result = this.valorationRepository.save(valoration);
		return result;
	}

	public Valoration findOne(final int valorationId) {
		Valoration result;

		result = this.valorationRepository.findOne(valorationId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Valoration> findByActor(final int actorId) {
		Collection<Valoration> result;

		result = this.valorationRepository.findByActor(actorId);

		return result;
	}

	public void flush() {
		this.valorationRepository.flush();
	}

}
