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
import domain.Question;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class QuestionTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private QuestionService				QuestionService;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private ShopAdvertisementService	shopAdvertisementService;


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {

			{	//Creacion Question correcta
				"user1", "test text 1", "shopAdvertisement1", null

			}, {	//question con texto en blanco
				"user1", "", "shopAdvertisement1", ConstraintViolationException.class

			}, {	//question sin shopadvertisement
				"user1", "test text 1", null, NullPointerException.class

			}, {	//Creacion fallida sin loguear
				null, "test text 1", "shopAdvertisement1", IllegalArgumentException.class

			}, {	//Creacion fallida Question sin un shopadvertisement
				"user1", "test text 1", "auctionAdvertisement1", IllegalArgumentException.class
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
	protected void createAndSaveTemplate(final String userBean, final String text, final String shopAdvertisementBean, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		Question Question;
		int shopAdvertisemenId;

		try {

			this.authenticate(userBean);
			shopAdvertisemenId = this.getEntityId(shopAdvertisementBean);

			Question = this.QuestionService.create(shopAdvertisemenId);
			Question.setText(text);

			this.QuestionService.save(Question);
			this.QuestionService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
