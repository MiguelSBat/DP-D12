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
import domain.ShipmentAddress;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ShipmentAddressTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ShipmentAddressService	shipmentAddressService;


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {
			{	//Creacion ShipmentAddress correcta
				"user1", "España", "Sevilla", "41010", "micasa", null
			}, {//Creacion erronea con un usuario que no exista
				"salmorejo", "España", "Sevilla", "41010", "micasa", IllegalArgumentException.class
			}, {//Creacion erronea ShipmentAddress titulo en blanco
				"user1", "", "Sevilla", "41010", "micasa", ConstraintViolationException.class
			}, {//Creacion erronea ShipmentAddress con ciudad en blanco
				"user1", "Suecia", "", "41010", "micasa", ConstraintViolationException.class
			}, {//Creacion erronea ShipmentAddress  con codigo postal en blanco
				"user1", "Mordor", "Sevilla", "", "micasa", ConstraintViolationException.class
			}, {//Creacion erronea ShipmentAddress direcion en blanco
				"user1", "Zimbabue", "Sevilla", "41010", "", ConstraintViolationException.class
			}, {//Creacion erronea ShipmentAddress con algo en null
				"user1", "Zimbabue", "Sevilla", "41010", null, ConstraintViolationException.class

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
	protected void createAndSaveTemplate(final String beanName, final String country, final String city, final String postalCode, final String address, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			this.authenticate(beanName);

			ShipmentAddress shipmentAddress;

			shipmentAddress = this.shipmentAddressService.create();

			shipmentAddress.setCountry(country);
			shipmentAddress.setPostalCode(postalCode);
			shipmentAddress.setCity(city);
			shipmentAddress.setAddress(address);

			this.shipmentAddressService.save(shipmentAddress);

			this.shipmentAddressService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

}
