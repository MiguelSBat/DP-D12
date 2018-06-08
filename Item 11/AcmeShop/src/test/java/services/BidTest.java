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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BidTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private BidService					bidService;
	@Autowired
	private ActorService				actorService;
	@Autowired
	private AuctionAdvertisementService	auctionAdvertisementService;
	@Autowired
	private UserService					userService;


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {
			{	//Creacion Bid correcta
				"user1", "auctionAdvertisement1", 300.0, null

			}, {	//Creacion Bid por debajo del mínimo
				"user1", "auctionAdvertisement1", 1.0, IllegalArgumentException.class

			}, {	//Creacion Bid de un auction terminado
				"user1", "auctionAdvertisement2", 300.0, IllegalArgumentException.class

			}
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplate((String) testingData[i][0], (String) testingData[i][1], (Double) testingData[i][2], (Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}
	// Ancillary methods ------------------------------------------------------
	protected void createAndSaveTemplate(final String beanName, final String beanAuction, final Double amount, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		Integer auctionId;

		try {

			this.authenticate(beanName);
			auctionId = this.getEntityId(beanAuction);
			this.bidService.createAndSave(auctionId, amount);

			this.bidService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

}
