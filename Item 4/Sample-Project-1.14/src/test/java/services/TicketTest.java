
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
import domain.Business;
import domain.Ticket;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TicketTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private TicketService	ticketService;

	@Autowired
	private UserService		userService;

	@Autowired
	private BusinessService	businessService;

	Date					fechaValida	= new Date();
	Date					fechaNula	= null;


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {
			{	//Creacion ticket correcta, con vendedor
				"user1", this.fechaValida, "En Progreso", false, true, null
			}, { //Creacion ticket correcta, con negocio
				"user1", this.fechaValida, "En Progreso", true, false, null
			}, { //Creacion ticket incorrecta, status nulo
				"user1", this.fechaValida, null, true, false, ConstraintViolationException.class
			}, { //Creacion ticket incorrecta, fecha nula
				"user1", this.fechaNula, "En Progreso", true, false, ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplate((String) testingData[i][0], (Date) testingData[i][1], (String) testingData[i][2], (boolean) testingData[i][3], (boolean) testingData[i][4], (Class<?>) testingData[i][5]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}
	// Ancillary methods ------------------------------------------------------
	protected void createAndSaveTemplate(final String beanName, final Date date, final String status, final boolean business, final boolean seller, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			final User user;
			this.authenticate(beanName);

			user = this.userService.findByPrincipal();
			Ticket ticket;

			ticket = this.ticketService.create();
			ticket.setDate(date);
			ticket.setStatus(status);
			ticket.setUser(user);
			if (business) {
				final List<Business> businesses = (List<Business>) this.businessService.findAll();
				ticket.setBusiness(businesses.get(0));
			}
			if (seller) {
				final List<User> users = (List<User>) this.userService.findAll();
				ticket.setSeller(users.get(0));
			}
			this.ticketService.save(ticket);

			this.ticketService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
