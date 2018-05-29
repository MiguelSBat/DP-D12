
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.QuestionRepository;
import domain.Actor;
import domain.Advertisement;
import domain.Business;
import domain.Question;
import domain.ShopAdvertisement;
import domain.User;

@Service
@Transactional
public class QuestionService {

	//Managed Repository ----
	@Autowired
	private QuestionRepository			questionRepository;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private ConfigService				configService;

	@Autowired
	private AnswerService				answerService;

	@Autowired
	private ShopAdvertisementService	shopAdvertisementService;

	@Autowired
	private AdvertisementService		advertisementService;


	//Constructors
	public QuestionService() {
		super();
	}

	public Question create(final int shopAdvertisementId) {
		Question result;
		Advertisement advertisement;
		Actor actor;

		advertisement = this.advertisementService.findOne(shopAdvertisementId);
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(advertisement != null && advertisement instanceof ShopAdvertisement);
		result = new Question();
		result.setShopAdvertisement((ShopAdvertisement) advertisement);
		result.setUser((User) actor);
		result.setDate(new Date());

		return result;
	}

	public Collection<Question> findAll() {
		Collection<Question> result;

		result = this.questionRepository.findAll();

		return result;
	}

	public Question save(final Question question) {
		Question result;
		User user;
		final ShopAdvertisement shopAdvertisement;

		user = (User) this.actorService.findByPrincipal();
		if (this.configService.isTaboo(question.getText()))
			user.setSuspicious(true);
		question.setDate(new Date());
		result = this.questionRepository.save(question);
		this.shopAdvertisementService.addQuestion(result);

		return result;
	}

	public void delete(final int questionId) {
		Question question;
		Business business;

		business = (Business) this.actorService.findByPrincipal();
		question = this.findOne(questionId);
		Assert.isTrue(question.getShopAdvertisement().getBusiness().getId() == business.getId());
		this.answerService.deleteByQuestion(question);
		this.shopAdvertisementService.deleteQuestion(question);
		this.questionRepository.delete(question);

	}
	public Question findOne(final int questionId) {
		Question result;

		result = this.questionRepository.findOne(questionId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Question> findByUser(final int actorId) {
		Collection<Question> result;

		result = this.questionRepository.findByUser(actorId);

		return result;
	}

	public void flush() {
		this.questionRepository.flush();
	}

	public Collection<Question> findByBusiness(final int businessId) {
		Collection<Question> result;

		result = this.questionRepository.findByBusiness(businessId);

		return result;
	}

}
