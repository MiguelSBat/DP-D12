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
import domain.Actor;
import forms.ActorForm;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ActorTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ActorService	actorService;


	// Auxiliary methods ------------------------------------------------------

	// Tests ------------------------------------------------------------------

	@Test
	public void createAndSaveDriverUser() {
		final Object testingData[][] = {
			{	//Creacion correcta de un usuario
				"userAccountTest", "password", "password", "Name1", "surname", "email@email.email", "123456789", "USER", true, null
			}, {	//user sin telefono
				"userAccountTest", "password", "password", "Name3", "surname", "email@email.email", "", "USER", true, ConstraintViolationException.class
			}, {	//useraccount con nombre vacio
				"", "password", "password", "Name4", "surname", "email@email.email", "123456789", "USER", true, ConstraintViolationException.class
			}, {	//user con nombre vacio
				"userAccountTest", "password", "password", "", "surname", "email@email.email", "123456789", "USER", true, ConstraintViolationException.class
			}, {	//user con nombre nulo
				"userAccountTest", "password", "password", null, "surname", "email@email.email", "123456789", "USER", true, ConstraintViolationException.class
			}, {	//user con apellido vacio
				"userAccountTest", "password", "password", "Name", "", "email@email.email", "123456789", "USER", true, ConstraintViolationException.class
			}, {	//user con apellido nulo
				"userAccountTest", "password", "password", "Name", null, "email@email.email", "123456789", "USER", true, ConstraintViolationException.class
			}, {	//user con correo vacio
				"userAccountTest", "password", "password", "Name", "Surname", "", "123456789", "USER", true, ConstraintViolationException.class
			}, {	//user con correo nulo
				"userAccountTest", "password", "password", "Name", "Surname", null, "123456789", "USER", true, ConstraintViolationException.class
			}, {	//user con correo incorrecto
				"userAccountTest", "password", "password", "Name", "Surname", "jdkshf", "123456789", "USER", true, ConstraintViolationException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplateUser((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
					(String) testingData[i][7], (Boolean) testingData[i][8], (Class<?>) testingData[i][9]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	@Test
	public void createAndSaveDriverBusiness() {
		final Object testingData[][] = {
			{	//Creacion correcta de un usuario
				"userAccountTest", "password", "password", "email@email.email", "name", "paypal@email.com", "123456789", "BUSINESS", true, null
			}, {	//user VatNumber Vacio
				"userAccountTest", "password", "password", "email@email.email", "name", "paypal@email.com", "", "BUSINESS", true, ConstraintViolationException.class
			}, {	//useraccount con nombre vacio
				"", "password", "password", "email@email.email", "name", "paypal@email.com", "123456789", "BUSINESS", true, ConstraintViolationException.class
			}, {	//user con nombre vacio
				"userAccountTest", "password", "password", "email@email.email", "", "paypal@email.com", "123456789", "BUSINESS", true, ConstraintViolationException.class
			}, {	//user con nombre nulo
				"userAccountTest", "password", "password", "email@email.email", null, "paypal@email.com", "123456789", "BUSINESS", true, ConstraintViolationException.class
			}, {	//user con paypalEmail vacio
				"userAccountTest", "password", "password", "email@email.email", "name", "", "123456789", "BUSINESS", true, ConstraintViolationException.class
			}, {	//user con apellido nulo
				"userAccountTest", "password", "password", "email@email.email", "name", null, "123456789", "BUSINESS", true, ConstraintViolationException.class
			}, {	//user con correo vacio
				"userAccountTest", "password", "password", "", "name", "paypal@email.com", "123456789", "BUSINESS", true, ConstraintViolationException.class
			}, {	//user con correo nulo
				"userAccountTest", "password", "password", null, "name", "paypal@email.com", "123456789", "BUSINESS", true, ConstraintViolationException.class
			}, {	//user con correo incorrecto
				"userAccountTest", "password", "password", "dadsa", "name", "paypal@email.com", "123456789", "BUSINESS", true, ConstraintViolationException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplateBusiness((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
					(String) testingData[i][7], (Boolean) testingData[i][8], (Class<?>) testingData[i][9]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}
	// Ancillary methods ------------------------------------------------------
	protected void createAndSaveTemplateUser(final String username, final String password, final String password2, final String name, final String surname, final String email, final String phone, final String authority, final Boolean agree,
		final Class<?> expected) {
		Class<?> caught;
		ActorForm actorForm;
		Actor actor;
		caught = null;
		try {

			actorForm = new ActorForm();
			actorForm.setUsername(username);
			actorForm.setPassword(password);
			actorForm.setPassword2(password2);
			actorForm.setName(name);
			actorForm.setSurname(surname);
			actorForm.setEmail(email);
			actorForm.setPhone(phone);
			actorForm.setAuthority(authority);
			actorForm.setAgree(true);

			actor = this.actorService.create(actorForm);
			this.actorService.register(actor);
			this.actorService.flush();

		} catch (final Throwable oops) {
			//			Para el debugueo activar la siguiente linea
			//			System.out.println(name + " " + oops.getClass());
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void createAndSaveTemplateBusiness(final String username, final String password, final String password2, final String email, final String name, final String paypalEmail, final String VATnumber, final String authority, final Boolean agree,
		final Class<?> expected) {
		Class<?> caught;
		ActorForm actorForm;
		Actor actor;
		caught = null;
		try {

			actorForm = new ActorForm();
			actorForm.setUsername(username);
			actorForm.setPassword(password);
			actorForm.setPassword2(password2);
			actorForm.setName(name);
			actorForm.setVATNumber(VATnumber);
			actorForm.setEmail(email);
			actorForm.setPaypalEmail(paypalEmail);
			actorForm.setAuthority(authority);
			actorForm.setAgree(true);

			actor = this.actorService.create(actorForm);
			this.actorService.register(actor);
			this.actorService.flush();

		} catch (final Throwable oops) {
			//			Para el debugueo activar la siguiente linea
			//			System.out.println(name + " " + oops.getClass());
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
