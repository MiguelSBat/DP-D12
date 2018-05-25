
package controllers;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdvertisementService;
import services.ExpressAdvertisementService;
import services.ItemService;
import services.ShopAdvertisementService;
import services.UserService;
import domain.Actor;
import domain.Business;
import domain.ShopAdvertisement;

@Controller
@RequestMapping("/shopAdvertisement")
public class ShopAdvertisementsController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService					userService;
	@Autowired
	private ActorService				actorService;
	@Autowired
	private AdvertisementService		advertisementService;

	@Autowired
	private ItemService					itemService;

	@Autowired
	private ExpressAdvertisementService	expressAdvertisementService;
	@Autowired
	private ShopAdvertisementService	shopAdvertisementService;


	// Constructors -----------------------------------------------------------

	public ShopAdvertisementsController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<ShopAdvertisement> advertisements;

		advertisements = this.shopAdvertisementService.findNotPast();

		result = new ModelAndView("shopAdvertisement/list");
		result.addObject("advertisements", advertisements);
		result.addObject("requestURI", "shopAdvertisement/myList.do");

		return result;
	}

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView MyList() {
		ModelAndView result;
		Collection<ShopAdvertisement> advertisements = new HashSet<>();
		final Actor a = this.actorService.findByPrincipal();
		final int aID = a.getId();
		if (a instanceof Business) {
			final Business b = (Business) a;
			advertisements = this.shopAdvertisementService.findShopByBussiness(b.getId());

		}
		result = new ModelAndView("shopAdvertisement/list");
		result.addObject("advertisements", advertisements);
		result.addObject("requestURI", "business/shopAdvertisement/myList.do");

		result.addObject("aID", aID);
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final ShopAdvertisement shopAdvertisement) {
		ModelAndView result;

		result = this.createEditModelAndView(shopAdvertisement, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ShopAdvertisement shopAdvertisement, final String message) {
		ModelAndView result;

		result = new ModelAndView("shopAdvertisement/edit");
		result.addObject("shopAdvertisement", shopAdvertisement);
		result.addObject("message", message);

		return result;

	}
}
