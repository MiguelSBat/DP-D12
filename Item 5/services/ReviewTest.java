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
import domain.Review;
import domain.ShopAdvertisement;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ReviewTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ReviewService				reviewService;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private ShopAdvertisementService	shopAdvertisementService;


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {
			{	//Creacion Review correcta
				"user1", "shopAdvertisement1", "test text 1", 5, null

			}, {	//Creacion Review correcta
				"user1", "shopAdvertisement1", "test text 1", 0, null

			}, {	//Creacion Review correcta
				"user1", "shopAdvertisement1", "test text 1", 2, null

			}, {	//Creacion Review con score negativa
				"user1", "shopAdvertisement1", "test text 1", -10, ConstraintViolationException.class

			}, {	//Creacion Review con texto en blanco
				"user1", "shopAdvertisement1", "", 5, ConstraintViolationException.class

			}, {	//Creacion Review con texto null
				"user1", "shopAdvertisement1", null, 5, NullPointerException.class

			}, {	//Creacion Review con score null
				"user1", "shopAdvertisement1", "test text 1", null, ConstraintViolationException.class

			},
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Integer) testingData[i][3], (Class<?>) testingData[i][4]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}
	// Ancillary methods ------------------------------------------------------
	protected void createAndSaveTemplate(final String userBean, final String shopAdvertisementBean, final String text, final Integer score, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		Review review;
		ShopAdvertisement shopAdvertisement;
		int shopAdvertisemenId, userId;
		User user;

		try {

			this.authenticate(userBean);
			userId = this.getEntityId(userBean);
			user = (User) this.actorService.findOne(userId);
			shopAdvertisemenId = this.getEntityId(shopAdvertisementBean);
			shopAdvertisement = this.shopAdvertisementService.findOne(shopAdvertisemenId);
			review = this.reviewService.create();
			review.setUser(user);
			review.setShopAdvertisement(shopAdvertisement);
			review.setScore(score);
			review.setText(text);
			this.reviewService.save(review);
			this.reviewService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
