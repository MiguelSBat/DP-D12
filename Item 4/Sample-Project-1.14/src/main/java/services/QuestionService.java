
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.QuestionRepository;
import domain.Question;

@Service
@Transactional
public class QuestionService {

	//Managed Repository ----
	@Autowired
	private QuestionRepository	questionRepository;


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

	public void delete(final Question question) {

		this.questionRepository.delete(question);

	}

	public Question save(final Question question) {
		Question result;

		result = this.questionRepository.save(question);
		return result;
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

}
