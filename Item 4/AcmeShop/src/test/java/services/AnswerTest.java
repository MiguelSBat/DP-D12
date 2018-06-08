/*
 * SampleTest.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Answer;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AnswerTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private QuestionService	QuestionService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private AnswerService	answerService;


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {

			{	//Creacion Answer correcta
				"business1", "test text 1", "question1", null

			}, {	//creacion fallida texto en blanco
				"business1", "", "question1", ConstraintViolationException.class

			}, {	//creacion fallida sin question
				"business1", "test text 1", null, NullPointerException.class

			}, {	//Creacion fallida sin loguear
				null, "test text 1", "question1", IllegalArgumentException.class

			}, {	//Contestar a la question de otro business fallido
				"business2", "test text 1", "question1", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}
	// Ancillary methods ------------------------------------------------------
	protected void createAndSaveTemplate(final String userBean, final String text, final String questionBean, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		Answer answer;
		int questionId;

		try {

			this.authenticate(userBean);
			questionId = this.getEntityId(questionBean);

			answer = this.answerService.create(questionId);
			answer.setText(text);

			this.answerService.save(answer);
			this.answerService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
