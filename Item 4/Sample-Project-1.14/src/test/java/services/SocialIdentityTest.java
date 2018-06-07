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
import domain.SocialIdentity;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SocialIdentityTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private SocialIdentityService		socialIdentityService;
	@Autowired
	private ActorService				actorService;
	@Autowired
	private AuctionAdvertisementService	auctionAdvertisementService;
	@Autowired
	private BusinessService				businessService;


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {
			{	//Creacion SocialIdentity correcta
				"user1", "http://www.url.com", "nick", "website", null
			}, {	//Business crea SocialIdentity
				"business1", "http://www.url.com", "nick", "website", ClassCastException.class
			}, {	//SocialIdentity con error
				"user1", "", "nick", "website", ConstraintViolationException.class
			}, {	//SocialIdentity con error
				"user1", "http://www.url.com", "", "website", ConstraintViolationException.class
			}, {	//SocialIdentity con error
				"user1", "http://www.url.com", "nick", "", ConstraintViolationException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}
	// Ancillary methods ------------------------------------------------------
	protected void createAndSaveTemplate(final String beanName, final String accountURL, final String nick, final String website, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(beanName);
			final SocialIdentity socialIdentity = this.socialIdentityService.create();
			socialIdentity.setAccountURL(accountURL);
			socialIdentity.setNick(nick);
			socialIdentity.setWebsite(website);
			this.socialIdentityService.save(socialIdentity);
			this.socialIdentityService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
