
package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import services.SaleLineService;
import services.ShoppingCartService;

@Controller
@RequestMapping("/user/advertisement")
public class AdvertisementUserController {

	@Autowired
	ShoppingCartService	shoppingCartService;

	@Autowired
	SaleLineService		saleLineService;


	@ResponseBody
	@RequestMapping(value = "/addToCart")
	public ResponseEntity<String> addToCart(@RequestParam final int adId, @RequestParam final Integer amount) {
		ResponseEntity<String> result;

		try {
			final String added = this.saleLineService.create(adId, amount);
			result = new ResponseEntity<String>(added, HttpStatus.OK);
		} catch (final Exception e) {
			result = new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}
}
