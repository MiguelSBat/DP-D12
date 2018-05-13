
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ModeratorRepository;
import domain.Moderator;

@Service
@Transactional
public class ModeratorService {

	//Managed Repository ----
	@Autowired
	private ModeratorRepository	moderatorRepository;


	//Constructors
	public ModeratorService() {
		super();
	}

	public Moderator create() {
		Moderator result;

		result = new Moderator();

		return result;
	}

	public Collection<Moderator> findAll() {
		Collection<Moderator> result;

		result = this.moderatorRepository.findAll();

		return result;
	}

	public void delete(final Moderator moderator) {

		this.moderatorRepository.delete(moderator);

	}

	public Moderator save(final Moderator moderator) {
		Moderator result;

		result = this.moderatorRepository.save(moderator);
		return result;
	}

	public Moderator findOne(final int moderatorId) {
		Moderator result;

		result = this.moderatorRepository.findOne(moderatorId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.moderatorRepository.flush();
	}

}
