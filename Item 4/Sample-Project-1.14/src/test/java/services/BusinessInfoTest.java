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
import domain.BusinessInfo;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BusinessInfoTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private BusinessInfoService			businessInfoService;
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
			{	//Creacion BusinessInfo correcta
				"business1", "String1", "String2", "String3", "String3", null

			}, {	//Creacion BusinessInfo sin address
				"business1", null, "String2", "String3", "String3", ConstraintViolationException.class

			}, {	//Creacion BusinessInfo sin city
				"business1", "String1", null, "String3", "String3", ConstraintViolationException.class

			}, {	//Creacion BusinessInfo sin country
				"business1", "String1", "String2", null, "String3", ConstraintViolationException.class

			}, {	//Creacion BusinessInfo sin additionalInfo
				"business1", "String1", "String2", "String3", null, ConstraintViolationException.class

			}
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}
	// Ancillary methods ------------------------------------------------------
	protected void createAndSaveTemplate(final String beanName, final String address, final String city, final String country, final String additionalInfo, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		BusinessInfo businessInfo;

		try {

			this.authenticate(beanName);
			businessInfo = this.businessInfoService.create();
			businessInfo.setAdditionalInfo(additionalInfo);
			businessInfo.setAddress(address);
			businessInfo.setCity(city);
			businessInfo.setCountry(country);
			this.businessInfoService.save(businessInfo);
			this.businessInfoService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
