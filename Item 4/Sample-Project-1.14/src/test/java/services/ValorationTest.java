
package services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

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
				"user1", this.fechaValida, 3, null
			}, {	//Creacion Incorrecta, fecha nula
				"user1", this.fechaNula, 2, null
			}, {	//Creacion Incorrecta, score nulo
				"user1", this.fechaValida, null, ConstraintViolationException.class
			}, {	//Creacion Incorrecta, score demasiado bajo
				"user1", this.fechaValida, -1, ConstraintViolationException.class
			}, {	//Creacion Incorrecta, score demasiado alto
				"user1", this.fechaValida, 100, ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplate((String) testingData[i][0], (Date) testingData[i][1], (Integer) testingData[i][2], (Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}
	// Ancillary methods ------------------------------------------------------
	protected void createAndSaveTemplate(final String beanName, final Date date, final Integer score, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			this.authenticate(beanName);

			Valoration valoration;

			valoration = this.valorationService.create();
			valoration.setDate(date);
			valoration.setScore(score);
			final List<Actor> actores = (List<Actor>) this.actorService.findAll();

			valoration.setActor(actores.get(0));

			this.valorationService.save(valoration);

			this.valorationService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
