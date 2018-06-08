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

import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.ExpressAdvertisement;
import domain.Item;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ExpressAdvertisementTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ExpressAdvertisementService	expressAdvertisementService;
	@Autowired
	private ActorService				actorService;
	@Autowired
	private AuctionAdvertisementService	auctionAdvertisementService;
	@Autowired
	private BusinessService				businessService;
	@Autowired
	private ItemService					itemService;


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {
			{	//Creacion ExpressAdvertisement correcta
				"user1", "item2", 10.0, false, null

			}, {	//Creacion ExpressAdvertisement correcta por business
				"business1", "item1", 10.0, false, null

			}, {	//Creacion sin precio
				"business1", "item1", null, false, ConstraintViolationException.class

			}, {	//Creacion precio negativo
				"business1", "item1", -1.0, false, ConstraintViolationException.class

			}, {	//Creacion con EndDate por enicma de config
				"business1", "item1", 10.0, true, IllegalArgumentException.class

			}
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplate((String) testingData[i][0], (String) testingData[i][1], (Double) testingData[i][2], (Boolean) testingData[i][3], (Class<?>) testingData[i][4]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}
	// Ancillary methods ------------------------------------------------------
	protected void createAndSaveTemplate(final String beanName, final String beanItem, final Double price, final Boolean forceFuture, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		ExpressAdvertisement expressAdvertisement;
		Item item;
		int itemId;
		Date endMoment;

		try {

			this.authenticate(beanName);

			itemId = this.getEntityId(beanItem);
			item = this.itemService.findOne(itemId);
			endMoment = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
			if (forceFuture)
				endMoment.setYear(endMoment.getYear() + 100);
			expressAdvertisement = this.expressAdvertisementService.create();
			expressAdvertisement.setItem(item);
			expressAdvertisement.setPrice(price);
			expressAdvertisement.setTags(new HashSet<String>());
			expressAdvertisement.setEndDate(endMoment);
			this.expressAdvertisementService.save(expressAdvertisement);
			this.expressAdvertisementService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
