
package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Actor;
import domain.Valoration;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ValorationTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ValorationService	valorationService;

	@Autowired
	private ActorService		actorService;

	Date						fechaValida	= new Date();
	Date						fechaNula	= null;


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {
			{	//Creacion Valoration correcta
				"user1", this.fechaValida, 3, "user3", IllegalArgumentException.class
			}
		/*
		 * , { //Creacion Incorrecta, score nulo
		 * / "user1", this.fechaValida, null,"user2", ConstraintViolationException.class
		 * }, { //Creacion Incorrecta, score demasiado bajo
		 * "user1", this.fechaValida, -1,"user2", ConstraintViolationException.class
		 * }, { //Creacion Incorrecta, score demasiado alto
		 * "user1", this.fechaValida, 100,"user2", ConstraintViolationException.class
		 * }
		 */
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplate((String) testingData[i][0], (Date) testingData[i][1], (Integer) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}
	// Ancillary methods ------------------------------------------------------
	protected void createAndSaveTemplate(final String beanName, final Date date, final Integer score, final String valorated, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			this.authenticate(beanName);
			final Actor actor = this.actorService.findByPrincipal();
			Valoration valoration;
			final int actorId = this.getEntityId(valorated);
			valoration = this.valorationService.create();
			valoration.setDate(date);
			valoration.setScore(score);
			valoration.setActor(actor);

			this.valorationService.save(valoration, actorId);

			this.valorationService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
