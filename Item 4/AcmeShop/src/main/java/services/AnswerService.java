
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnswerRepository;
import domain.Answer;
import domain.Business;
import domain.Question;

@Service
@Transactional
public class AnswerService {

	//Managed Repository ----
	@Autowired
	private AnswerRepository	answerRepository;

	@Autowired
	private ConfigService		configService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private BusinessService		businessService;

	@Autowired
	private QuestionService		questionService;


	//Constructors
	public AnswerService() {
		super();
	}

	public Answer create(final int questionId) {
		Answer result;
		Question question;

		question = this.questionService.findOne(questionId);
		result = new Answer();
		result.setDate(new Date());
		result.setQuestion(question);

		return result;
	}

	public Collection<Answer> findAll() {
		Collection<Answer> result;

		result = this.answerRepository.findAll();

		return result;
	}

	public void delete(final Answer answer) {
		Business business;

		business = (Business) this.actorService.findByPrincipal();
		business.removeAnswer(answer);
		this.businessService.save(business);
		this.answerRepository.delete(answer);

	}

	public Answer save(final Answer answer) {
		Answer result;
		Business business;

		business = (Business) this.actorService.findByPrincipal();
		Assert.isTrue(business.getId() == answer.getQuestion().getShopAdvertisement().getBusiness().getId());

		if (this.configService.isTaboo(answer.getText()))
			business.setSuspicious(true);
		answer.setDate(new Date());
		result = this.answerRepository.save(answer);
		return result;
	}

	public Answer findOne(final int answerId) {
		Answer result;

		result = this.answerRepository.findOne(answerId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.answerRepository.flush();
	}

	public Collection<Answer> findByQuestion(final int questionId) {
		Collection<Answer> result;

		result = this.answerRepository.findByQuestion(questionId);

		return result;
	}

	public void deleteByQuestion(final Question question) {
		Collection<Answer> answers;

		answers = this.findByQuestion(question.getId());

		for (final Answer e : answers)
			this.delete(e);
	}

}
