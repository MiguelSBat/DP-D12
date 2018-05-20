
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Admin;
import domain.Folder;
import domain.Message;
import domain.Report;
import domain.Valoration;
import domain.Advertisement;
import domain.Question;
import domain.Review;
import domain.User;
import domain.Valoration;
import forms.ActorForm;

@Service
@Transactional
public class ActorService {

	//Managed Repository ----
	@Autowired
	private ActorRepository		actorRepository;

	@Autowired
	private AdvertisementService	advertisementService;

	@Autowired
	private ValorationService		valorationService;

	@Autowired
	private QuestionService			questionService;

	@Autowired
	private ReviewService			reviewService;

	@Autowired
	private AdminService		adminService;

	@Autowired
	private BusinessService		businessService;

	@Autowired
	private ModeratorService	moderatorService;

	@Autowired
	private UserService			userService;


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
	public Actor create(final ActorForm actorForm) {
		Actor result;
		UserAccount userAccount;
		Authority authority;

		Assert.isTrue(actorForm.getPassword().equals(actorForm.getPassword2()));

		userAccount = new UserAccount();
		authority = new Authority();
		final String actorType = actorForm.getAuthority();

		if (actorType.equals(Authority.ADMIN)) {
			Assert.isTrue(this.findByPrincipal() instanceof Admin);
			authority.setAuthority(Authority.ADMIN);
			result = this.adminService.create();
		} else if (actorType.equals(Authority.BUSINESS)) {
			authority.setAuthority(Authority.BUSINESS);
			result = this.businessService.create(actorForm);
		} else if (actorType.equals(Authority.MODERATOR)) {
			Assert.isTrue(this.findByPrincipal() instanceof Admin);
			authority.setAuthority(Authority.MODERATOR);
			result = this.moderatorService.create();
		} else if (actorType.equals(Authority.USER)) {
			authority.setAuthority(Authority.USER);
			result = this.userService.create(actorForm);
		} else
			throw new ServiceException("Invalid actor type parameter");

		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		result.setEmailAddress(actorForm.getEmail());
		result.getUserAccount().setUsername(actorForm.getUsername());
		result.getUserAccount().setPassword(actorForm.getPassword());
		final Collection<Folder> folders = new HashSet<>();
		final Collection<Message> messagesSent = new HashSet<>();
		final Collection<Message> messagesReceived = new HashSet<>();
		final Collection<Report> reports = new HashSet<>();
		final Collection<Valoration> valorations = new HashSet<>();
		result.setFolders(folders);
		result.setMessagesReceived(messagesReceived);
		result.setMessagesSent(messagesSent);
		result.setReports(reports);
		result.setValorations(valorations);
		return result;
	}

	public Actor register(final Actor actor) {
		Assert.notNull(actor);
		final Actor result;

		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String pass = encoder.encodePassword(actor.getUserAccount().getPassword(), null);
		actor.getUserAccount().setPassword(pass);

		result = this.save(actor);

		return result;
	}
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
