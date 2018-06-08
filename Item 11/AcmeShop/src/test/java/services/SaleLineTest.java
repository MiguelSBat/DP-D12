
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Advertisement;
import domain.SaleLine;
import domain.ShoppingCart;
import domain.Ticket;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SaleLineTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private SaleLineService			saleLineService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdvertisementService	advertisementService;

	@Autowired
	private ShoppingCartService		shoppingCartService;

	@Autowired
	private TicketService			ticketService;


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {
			{	//Creacion Correcta Sale Line
				"user1", "expressAdvertisement1", 1, "shoppingCart1", "ticket2", null
			}, {//Creacion Incorrecta, Advertisement nulo
				"user2", null, 1, "shoppingCart2", "ticket1", NullPointerException.class
			}, {//Creacion Incorrecta, Cantidad Nula
				"user1", "expressAdvertisement1", null, "shoppingCart1", "ticket2", ConstraintViolationException.class
			}, {//Creacion Incorrecta, Carrito Nulo
				"user1", "expressAdvertisement1", 1, null, "ticket2", NullPointerException.class
			}, {//Creacion Incorrecta, Ticket NUlo
				"user1", "expressAdvertisement1", 1, "shoppingCart1", null, NullPointerException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.createAndSaveTemplate((String) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}
	// Ancillary methods ------------------------------------------------------
	protected void createAndSaveTemplate(final String beanName, final String advertisementBeanName, final Integer amount, final String shoppingCartBeanName, final String ticketBeanName, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		int advertisementId, shoppingCartId, ticketId;
		Advertisement advertisement;
		ShoppingCart shoppingCart;
		Ticket ticket;
		try {

			this.authenticate(beanName);
			SaleLine saleLine;
			saleLine = this.saleLineService.create();
			advertisementId = this.getEntityId(advertisementBeanName);
			advertisement = this.advertisementService.findOne(advertisementId);
			saleLine.setAdvertisement(advertisement);
			saleLine.setAmount(amount);
			shoppingCartId = this.getEntityId(shoppingCartBeanName);
			shoppingCart = this.shoppingCartService.findOne(shoppingCartId);
			saleLine.setShoppingCart(shoppingCart);
			ticketId = this.getEntityId(ticketBeanName);
			ticket = this.ticketService.findOne(ticketId);
			saleLine.setTicket(ticket);
			this.saleLineService.save(saleLine);

			this.saleLineService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
