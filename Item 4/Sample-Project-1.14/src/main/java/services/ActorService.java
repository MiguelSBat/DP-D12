
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	//Managed Repository ----
	@Autowired
	private ActorRepository	actorRepository;


	//Constructors
	public ActorService() {
		super();
	}

	public Actor create() {
		Actor result;

		result = new Actor();

		return result;
	}

	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = this.actorRepository.findAll();

		return result;
	}

	public void delete(final Actor actor) {

		this.actorRepository.delete(actor);

	}

	public Actor save(final Actor actor) {
		Actor result;

		result = this.actorRepository.save(actor);
		return result;
	}

	public Actor findOne(final int actorId) {
		Actor result;

		result = this.actorRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.actorRepository.flush();
	}

}
