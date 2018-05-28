
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.QuestionRepository;
import domain.Business;
import domain.Question;
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


	//Constructors
	public QuestionService() {
		super();
	}

	public Question create() {
		Question result;

		result = new Question();

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

		user = (User) this.actorService.findByPrincipal();
		if (this.configService.isTaboo(question.getText()))
			user.setSuspicious(true);
		result = this.questionRepository.save(question);
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
