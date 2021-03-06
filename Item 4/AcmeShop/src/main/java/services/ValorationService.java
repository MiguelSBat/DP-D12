
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ValorationRepository;
import domain.Actor;
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

	@Autowired
	private ActorService			actorService;

	@Autowired
	private ConfigService			ConfigService;


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

	public Valoration save(final Valoration valoration, final int actorId) {
		final Actor valorated = this.actorService.findOne(actorId);
		Assert.isTrue(this.AntiHacking(valorated));
		Valoration result;
		valoration.setDate(new Date());
		result = this.valorationRepository.save(valoration);

		final Collection<Valoration> valorations = valorated.getValorations();
		valorations.add(result);
		valorated.setValorations(valorations);

		if (valorated instanceof User)
			if (this.getValorations(valorated.getId()) <= this.ConfigService.findConfiguration().getReputationTreshold())
				((User) valorated).setSuspicious(true);
		if (valorated instanceof Business)
			if (this.getValorations(valorated.getId()) <= this.ConfigService.findConfiguration().getReputationTreshold())
				((Business) valorated).setSuspicious(true);
		this.actorService.save(valorated);
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

	public Double getstdValorationByUser() {
		Collection<User> aux;

		aux = this.userService.findAll();
		Double avg, s, sum, count, result;
		avg = this.getAverageValorationByUser();
		s = 0.0;
		sum = 0.0;
		count = 0.0;

		for (final User u : aux) {
			if (this.getValorations(u.getId()) != null)
				s = this.getValorations(u.getId());
			sum = sum + s * s;
			count += 1;
		}
		result = Math.sqrt(sum / count - avg * avg);
		return result;
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

	public Double getstdValorationByBusiness() {
		Collection<Business> aux;

		aux = this.businessService.findAll();
		Double avg, s, sum, count, result;
		avg = this.getAverageValorationByUser();
		s = 0.0;
		sum = 0.0;
		count = 0.0;

		for (final Business u : aux) {
			if (this.getValorations(u.getId()) != null)
				s = this.getValorations(u.getId());
			sum = sum + s * s;
			count += 1;
		}
		result = Math.sqrt(sum / count - avg * avg);
		return result;
	}

	public Boolean AntiHacking(final Actor valorado) {
		final boolean result = false;
		Collection<User> usuarios = new HashSet<User>();
		Collection<Business> business = new HashSet<Business>();
		final Actor a = this.actorService.findByPrincipal();
		if (a instanceof Business) {
			final Business businessActor = (Business) a;
			usuarios = this.userService.findUsersISoldThingsAndIAmABussiness(businessActor.getId());

		}
		if (a instanceof User) {
			final User u = (User) a;
			usuarios = this.userService.findUsersISoldThingsToThey(u.getId());
			usuarios.addAll(this.userService.findUsersTheySoldThingsToMy(u.getId()));
			business = this.businessService.findBusinessIbuyThings(u.getId());

		}
		//los meto en set para evitar repetidos
		final Set<User> usuariosNoRepetidos = new HashSet<User>();
		final Set<Business> businessNoRepetidos = new HashSet<Business>();
		usuariosNoRepetidos.addAll(usuarios);
		businessNoRepetidos.addAll(business);

		//complejo sistema para que no puedas valorar 2 veces al mismo
		//aunque parezca que es poco optimo, solo hace for sobre cada columna  de la lista y compara si ya valoro a dicho actor para no mostrarlo
		final Collection<Valoration> valorationsYaValoradas = this.findByActor(a.getId());
		for (final User u : usuariosNoRepetidos)
			for (final Valoration v : valorationsYaValoradas)
				if (v.getActor().equals(a)) {
					usuariosNoRepetidos.remove(u);
					break;
				}
		for (final Business b : businessNoRepetidos)
			for (final Valoration v : valorationsYaValoradas)
				if (v.getActor().equals(a)) {

					businessNoRepetidos.remove(b);

					break;
				}
		//comprobacion final
		if (valorado instanceof User)
			if (usuariosNoRepetidos.contains(valorado))
				return true;
		if (valorado instanceof Business)
			if (businessNoRepetidos.contains(valorado))
				return true;
		return result;
	}

}
