
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Advertisement;
import domain.Question;
import domain.Review;
import domain.User;
import domain.Valoration;

@Service
@Transactional
public class ActorService {

	//Managed Repository ----
	@Autowired
	private ActorRepository			actorRepository;

	@Autowired
	private AdvertisementService	advertisementService;

	@Autowired
	private ValorationService		valorationService;

	@Autowired
	private QuestionService			questionService;

	@Autowired
	private ReviewService			reviewService;


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

	public Actor findByUserAccount(final UserAccount userAccount) {
		Actor result;
		result = this.actorRepository.findByUserAccount(userAccount.getId());

		return result;
	}

	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.findByUserAccount(userAccount);
		return result;
	}

	public boolean isLogged() {
		boolean result = false;
		Object principal;

		principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserAccount)
			result = true;
		return result;
	}

	public Collection<Actor> findSuspicious() {
		Collection<Actor> result;

		result = this.actorRepository.findSuspicious();

		return result;
	}

	public void softBan(final int actorId) {
		Actor result;

		result = this.actorRepository.findOne(actorId);
		result.setSoftBan(true);
		this.save(result);
	}

	public void hardBan(final int actorId) {
		Actor result;
		User user;
		Collection<Advertisement> advertisements;
		Collection<Valoration> valorations;
		Collection<Question> questions;
		Collection<Review> reviews;

		result = this.actorRepository.findOne(actorId);
		result.setHardBan(true);
		this.save(result);
		advertisements = this.advertisementService.findByBusinessORUser(actorId);
		valorations = this.valorationService.findByActor(actorId);
		if (result instanceof User) {
			user = (User) this.findOne(actorId);
			questions = this.questionService.findByUser(actorId);
			reviews = user.getReviews();
			for (final Question q : questions)
				this.questionService.delete(q);
			for (final Review r : reviews)
				this.reviewService.delete(r);
		}
		for (final Valoration v : valorations)
			this.valorationService.delete(v);
		for (final Advertisement a : advertisements)
			this.advertisementService.delete(a);
	}

}
