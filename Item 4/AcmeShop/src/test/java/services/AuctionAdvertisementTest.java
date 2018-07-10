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

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.AuctionAdvertisement;
import domain.Item;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AuctionAdvertisementTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private AuctionAdvertisementService	auctionAdvertisementService;
	@Autowired
	private ItemService					itemService;


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {
			{	//Business Creacion ShopAd correcta
				"business1", "item1", 2018, 7, 20, "tags", 10.0, 15.0, false, null
			}, {	//Business Creacion ShopAd correcta secreta
				"business1", "item1", 2018, 7, 20, "tags", 10.0, 15.0, true, null
			}, {	//Business Creacion ShopAd con item incorrecto
				"business1", "item2", 2018, 7, 20, "tags", 10.0, 15.0, false, IllegalArgumentException.class
			}, {	//Business Creacion con fecha por encima del limite
				"business1", "item1", 2019, 6, 20, "tags", 10.0, 15.0, false, IllegalArgumentException.class
			}, {	//Business Creacion con fecha en pasado
				"business1", "item1", 2000, 6, 20, "tags", 10.0, 15.0, false, IllegalArgumentException.class
			}, {	//Business Creacion con precio en 0
				"business1", "item1", 2018, 7, 20, "tags", 0.0, 15.0, false, null
			}, {	//Business Creacion con precio negativo
				"business1", "item1", 2018, 7, 20, "tags", -10.0, 15.0, false, ConstraintViolationException.class
			}, {	//Business Creacion con InstantPrecio 0
				"business1", "item1", 2018, 7, 20, "tags", 10.0, 0.0, false, null
			}, {	//Business Creacion con InstantPrecio negativo
				"business1", "item1", 2018, 7, 20, "tags", 10.0, -150.0, false, ConstraintViolationException.class
			}, {	//User Creacion ShopAd correcta
				"user1", "item2", 2018, 7, 20, "tags", 10.0, 15.0, false, null
			}, {	//User Creacion ShopAd correcta secreta
				"user1", "item2", 2018, 7, 20, "tags", 10.0, 15.0, true, null
			}, {	//User Creacion ShopAd con item incorrecto
				"user1", "item1", 2018, 7, 20, "tags", 10.0, 15.0, false, IllegalArgumentException.class
			}, {	//User Creacion con fecha por encima del limite
				"user1", "item2", 2019, 7, 20, "tags", 10.0, 15.0, false, IllegalArgumentException.class
			}, {	//User Creacion con fecha en pasado
				"user1", "item2", 2000, 7, 20, "tags", 10.0, 15.0, false, IllegalArgumentException.class
			}, {	//User Creacion con precio en 0
				"user1", "item2", 2018, 7, 20, "tags", 0.0, 15.0, false, null
			}, {	//User Creacion con precio negativo
				"user1", "item2", 2018, 7, 20, "tags", -10.0, 15.0, false, ConstraintViolationException.class
			}, {	//User Creacion con InstantPrecio 0
				"user1", "item2", 2018, 7, 20, "tags", 10.0, 0.0, false, null
			}, {	//User Creacion con InstantPrecio negativo
				"user1", "item2", 2018, 7, 20, "tags", 10.0, -150.0, false, ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplate((String) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2], (Integer) testingData[i][3], (Integer) testingData[i][4], (String) testingData[i][5], (Double) testingData[i][6],
					(Double) testingData[i][7], (Boolean) testingData[i][8], (Class<?>) testingData[i][9]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------
	protected void createAndSaveTemplate(final String beanName, final String beanItem, final Integer year, final Integer monthOfYear, final Integer dayOfMonth, final String tag, final Double startingP, final Double Buyprice, final Boolean secret,
		final Class<?> expected) {
		Class<?> caught;
		caught = null;
		AuctionAdvertisement auctionAdvertisement;
		Item item;
		Collection<String> tags;
		DateTime jodaDate;
		String[] splited;
		int itemId;

		try {
			tags = new ArrayList<String>();
			splited = tag.split("-");
			for (final String e : splited)
				tags.add(e);
			jodaDate = new DateTime(year, monthOfYear, dayOfMonth, 0, 0);
			this.authenticate(beanName);
			itemId = this.getEntityId(beanItem);
			item = this.itemService.findOne(itemId);
			auctionAdvertisement = this.auctionAdvertisementService.create();
			auctionAdvertisement.setItem(item);
			auctionAdvertisement.setStartingPrice(startingP);
			auctionAdvertisement.setTags(tags);
			auctionAdvertisement.setInstantBuyPrice(Buyprice);
			auctionAdvertisement.setEndDate(jodaDate.toDate());
			auctionAdvertisement.setSecret(secret);
			this.auctionAdvertisementService.save(auctionAdvertisement);
			this.auctionAdvertisementService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

}
