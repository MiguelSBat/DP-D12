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

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Item;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ItemTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ItemService	itemService;

//	Date					fechaValida	= new Date();


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {
			{	//Creacion Item correcta
				"user1", "item1", "este objeto es muy bonito", "http://foto.es", null
			}, {//Creacion erronea con un usuario que no exista
				"salmorejo", "item1", "este objeto es muy bonito", "http://foto.es",  IllegalArgumentException.class
			}, {//Creacion erronea Item titulo en blanco
				"user1", "", "este objeto es muy bonito", "http://foto.es",  ConstraintViolationException.class
			}, {//Creacion erronea Item titulo null
				"user1", null, "este objeto es muy bonito", "http://foto.es",  NullPointerException.class
			}, {//Creacion erronea descripcion en blanco
				"user1", "item1", "", "http://foto.es",  ConstraintViolationException.class
			}, {//Creacion erronea descripcion null
				"user1", "item1", null, "http://foto.es", ConstraintViolationException.class
			}, {//Creacion con un business
				"business1", "item2", "soy un business", "http://foto.es", null
			}, {//Creacion erronea con un business pero con descripcion en null
				"business1", "item3", null, "http://foto.es", ConstraintViolationException.class
				
			}
			
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
	protected void createAndSaveTemplate(final String beanName, final String name,final String description,final String photo , final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			this.authenticate(beanName);

			Item item;

			item = this.itemService.create();

			item.setName(name);
			item.setDescription(description);
			item.setPhoto(photo);
		

			this.itemService.save(item);

			this.itemService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

}
