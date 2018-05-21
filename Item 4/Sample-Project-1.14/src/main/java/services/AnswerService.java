
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnswerRepository;
import domain.Answer;
import domain.User;

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


	//Constructors
	public AnswerService() {
		super();
	}

	public Answer create() {
		Answer result;

		result = new Answer();

		return result;
	}

	public Collection<Answer> findAll() {
		Collection<Answer> result;

		result = this.answerRepository.findAll();

		return result;
	}

	public void delete(final Answer answer) {

		this.answerRepository.delete(answer);

	}

	public Answer save(final Answer answer) {
		Answer result;
		User user;

		user = (User) this.actorService.findByPrincipal();
		if (this.configService.isTaboo(answer.getText()))
			user.setSuspicious(true);
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

}
