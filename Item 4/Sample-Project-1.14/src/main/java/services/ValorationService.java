
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ValorationRepository;
import domain.Business;
import domain.User;
import domain.Valoration;

@Service
@Transactional
public class ValorationService {

	//Managed Repository ----
	@Autowired
	private ValorationRepository	valorationRepository;

	@Autowired
	private UserService				userService;

	@Autowired
	private BusinessService			businessService;


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
	public Double getValorations(final int actorId) {
		Double result;

		result = this.valorationRepository.getAverageValorations(actorId);

		return result;
	}

	public Double getAverageValorationByUser() {
		final Collection<User> aux = this.userService.findAll();

		Double res = 0.0;
		Double s = 0.0;

		for (final User u : aux) {
			if (this.getValorations(u.getId()) != null)
				s = this.getValorations(u.getId());
			res = res + s;
		}
		return res / aux.size();
	}

	public Double getAverageValorationByBusiness() {
		final Collection<Business> aux = this.businessService.findAll();
		Double res = 0.0;

		Double s = 0.0;
		for (final Business u : aux) {
			if (this.getValorations(u.getId()) != null)
				s = this.getValorations(u.getId());
			res = res + s;
		}
		return res / aux.size();
	}
}
