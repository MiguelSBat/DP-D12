
package services;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.SaleLine;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ShoppingCartTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ShoppingCartService	shoppingCartService;

	@Autowired
	private SaleLineService		saleLineService;


	// Tests ------------------------------------------------------------------
	@Test
	public void addItemDriver() {
		final Object testingData[][] = {
			{	//Añadir shopAdvertisement correctamente
				"user1", "shopAdvertisement1", 5, SaleLineService.ADDED
			}, {	//Añadir expressAdvertisement correctamente
				"user1", "expressAdvertisement1", 1, SaleLineService.ADDED
			}, {	//Añadir shopAdvertisement sin stock
				"user1", "shopAdvertisement1", 1000, SaleLineService.OUT_OF_STOCK
			}, {	//Añadir más de un mismo expressAdvertisement
				"user1", "expressAdvertisement1", 2, SaleLineService.INVALID_AMOUNT
			}, {	//Añadir cantidad no válida
				"user1", "shopAdvertisement1", -1, SaleLineService.INVALID_AMOUNT
			}, {	//Añadir auctionAdvertisement
				"user1", "auctionAdvertisement1", 1, SaleLineService.ERROR
			}, {	// Business no puede añadir
				"business1", "shopAdvertisement1", 1, SaleLineService.ERROR
			},
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.addItemTemplate((String) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2], (String) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}
	// Ancillary methods ------------------------------------------------------
	protected void addItemTemplate(final String user, final String advertisement, final Integer amount, final String result) {

		try {
			this.authenticate(user);

			Assert.assertEquals(result, this.saleLineService.create(this.getEntityId(advertisement), amount));

			Boolean exists = false;
			for (final SaleLine line : this.saleLineService.findByPrincipal())
				if (line.getAdvertisement().getId() == this.getEntityId(advertisement))
					exists = true;

			if (result.equals(SaleLineService.ADDED))
				Assert.assertTrue(exists);
			else
				Assert.assertFalse(exists);
		} catch (final Throwable oops) {
			if (!result.equals(SaleLineService.ERROR))
				throw new RuntimeException(oops.getMessage());
		}
	}
}
