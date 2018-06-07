
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.ShippingInfo;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class IcesaShippingInfoTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ShippingInfoService	shippingInfoService;

	@Autowired
	private TicketService		ticketService;


	// Tests ------------------------------------------------------------------
	@Test
	public void createAndSaveDriver() {
		final Object testingData[][] = {
			{	//Creacion ShippingInfo correcta
				"business1", "ticket2", 123456789, "Company", "AdditionalInfo", null
			}, {	//Business crea ShippingInfo en ticket de otro business
				"business2", "ticket2", 123456789, "Company", "AdditionalInfo", IllegalArgumentException.class
			}, {	//Business crea shippingInfo en ticket que ya tiene
				"business1", "ticket1", 123456789, "Company", "AdditionalInfo", IncorrectResultSizeDataAccessException.class
			}, {	//ShippingInfo con errores
				"business1", "ticket2", null, "Company", "AdditionalInfo", IllegalArgumentException.class
			}, {	//ShippingInfo con errores
				"business1", "ticket2", 123456789, "", "AdditionalInfo", IllegalArgumentException.class
			}, {	//ShippingInfo con errores
				"business1", "ticket2", 123456789, "Company", "", null
			},
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
	protected void createAndSaveTemplate(final String business, final String ticket, final Integer trackingNumber, final String company, final String additionalInfo, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(business);
			final ShippingInfo shippingInfo = new ShippingInfo();

			shippingInfo.setTicket(this.ticketService.findOne(this.getEntityId(ticket)));
			shippingInfo.setTrackingNumber(trackingNumber);
			shippingInfo.setCompany(company);
			shippingInfo.setAdditionalInfo(additionalInfo);

			this.shippingInfoService.save(shippingInfo);
			this.shippingInfoService.flush();

			Assert.notNull(this.shippingInfoService.findByTicketId(this.getEntityId(ticket)));

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

}
