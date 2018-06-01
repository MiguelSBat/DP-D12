
package controllers;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdvertisementService;
import services.ItemService;
import services.ShopAdvertisementService;
import services.UserService;
import domain.Actor;
import domain.Business;
import domain.Item;
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
	private ShopAdvertisementService	shopAdvertisementService;


	// Constructors -----------------------------------------------------------

	public ShopAdvertisementsController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ShopAdvertisement shopAdvertisement;

		shopAdvertisement = this.shopAdvertisementService.create();
		result = this.createEditModelAndView(shopAdvertisement);

		return result;
	}

	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ShopAdvertisement shopAdvertisement, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final String message = "advertisement.commit.error";

			result = this.createEditModelAndView(shopAdvertisement, message);
		} else
			try {
				this.shopAdvertisementService.save(shopAdvertisement);
				result = new ModelAndView("redirect:myList.do");
			} catch (final Throwable oops) {
				String errorMessage = "advertisement.commit.error";
				errorMessage = this.error(oops.toString());
				if (oops.getMessage().contains("message.error"))
					errorMessage = oops.getMessage();
				result = this.createEditModelAndView(shopAdvertisement, errorMessage);
			}

		return result;
	}
	private String error(final String s) {
		String result;

		if (s.contains("shopAdvertisement.tabuError"))
			result = "shopAdvertisement.tabuError";
		else if (s.contains("shopAdvertisement.stockError"))
			result = "shopAdvertisement.stockError";
		else
			result = "advertisement.commit.error";

		return result;
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<ShopAdvertisement> advertisements;

		advertisements = this.shopAdvertisementService.findNotPast();

		result = new ModelAndView("shopAdvertisement/list");
		result.addObject("advertisements", advertisements);
		result.addObject("requestURI", "shopAdvertisement/list.do");

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

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(final int shopAdvertisementId) {
		ModelAndView result;

		try {
			this.shopAdvertisementService.delete(shopAdvertisementId);

		} catch (final Throwable oops) {

		}
		result = new ModelAndView("redirect:myList.do");

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
		Collection<Item> items;
		final Boolean tabu = false;

		result = new ModelAndView("shopAdvertisement/edit");
		result.addObject("shopAdvertisement", shopAdvertisement);
		result.addObject("message", message);
		items = this.itemService.findByPrincipal();
		result.addObject("items", items);

		return result;

	}
}
