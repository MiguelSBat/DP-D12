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
import domain.Item;
import domain.ShopAdvertisement;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ShopAdvertisementTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ShopAdvertisementService	shopAdvertisementService;
	@Autowired
	private ItemService					itemService;


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {
			{	//Creacion ShopAd correcta
				"business1", "item1", 2018, 6, 20, "tags", 10, 10.0, null
			}, {	//Creacion ShopAd con item incorrecto
				"business1", "item2", 2018, 6, 20, "tags", 10, 10.0, IllegalArgumentException.class
			}, {	//Creacion con fecha por encima del limite
				"business1", "item1", 2019, 6, 20, "tags", 10, 10.0, IllegalArgumentException.class
			}, {	//Creacion con fecha en pasado
				"business1", "item1", 2000, 6, 20, "tags", 10, 10.0, IllegalArgumentException.class
			}, {	//User intenta crear un ShopAd
				"user1", "item1", 2019, 6, 20, "tags", 10, 10.0, IllegalArgumentException.class
			}, {	//Creacion con precio en 0
				"business1", "item1", 2018, 6, 20, "tags", 10, 0.0, null
			}, {	//Creacion con precio negativo
				"business1", "item1", 2018, 6, 20, "tags", 10, -10.0, ConstraintViolationException.class
			}, {	//Creacion con stock 0
				"business1", "item1", 2018, 6, 20, "tags", 0, 10.0, IllegalArgumentException.class
			}, {	//Creacion con stock negativo
				"business1", "item1", 2018, 6, 20, "tags", -10, 10.0, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplate((String) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2], (Integer) testingData[i][3], (Integer) testingData[i][4], (String) testingData[i][5], (Integer) testingData[i][6],
					(Double) testingData[i][7], (Class<?>) testingData[i][8]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	@Test
	public void deleteDriver() {
		final Object testingData[][] = {
			{	//Delete ShopAd correcto
				"business1", "shopAdvertisement1", null
			}, {	//Delete ShopAd correcto
				"business1", "shopAdvertisement2", null
			}, {	//Delete ShopAd correcto
				"business2", "shopAdvertisement1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.deleteTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}
	// Ancillary methods ------------------------------------------------------
	protected void createAndSaveTemplate(final String beanName, final String beanItem, final Integer year, final Integer monthOfYear, final Integer dayOfMonth, final String tag, final Integer stock, final Double price, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		ShopAdvertisement shopAdvertisement;
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
			shopAdvertisement = this.shopAdvertisementService.create();
			shopAdvertisement.setItem(item);
			shopAdvertisement.setPrice(price);
			shopAdvertisement.setTags(tags);
			shopAdvertisement.setStock(stock);
			shopAdvertisement.setEndDate(jodaDate.toDate());
			this.shopAdvertisementService.save(shopAdvertisement);
			this.shopAdvertisementService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void deleteTemplate(final String beanName, final String beanAd, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		int shopAdvertisementId;

		try {
			this.authenticate(beanName);
			shopAdvertisementId = this.getEntityId(beanAd);
			this.shopAdvertisementService.delete(shopAdvertisementId);
			this.shopAdvertisementService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
