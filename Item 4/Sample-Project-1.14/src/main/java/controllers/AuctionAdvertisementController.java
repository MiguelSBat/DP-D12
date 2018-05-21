
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AuctionAdvertisementService;
import services.ItemService;
import domain.Actor;
import domain.AuctionAdvertisement;
import domain.Business;
import domain.Item;
import domain.User;

@Controller
@RequestMapping("/auctionAdvertisement")
public class AuctionAdvertisementController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AuctionAdvertisementService	auctionAdvertisementService;

	@Autowired
	private ItemService					itemService;

	@Autowired
	private ActorService				actorService;


	// Constructors -----------------------------------------------------------

	public AuctionAdvertisementController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView list(final String criteria) {
		ModelAndView result;
		Collection<AuctionAdvertisement> advertisements;
		Actor actor;

		advertisements = this.auctionAdvertisementService.findByPrincipal();
		result = new ModelAndView("auctionAdvertisement/list");
		result.addObject("advertisements", advertisements);
		if (this.actorService.isLogged()) {
			actor = this.actorService.findByPrincipal();
			if (actor instanceof User)
				result.addObject("userId", actor.getId());
			else if (actor instanceof Business)
				result.addObject("businessId", actor.getId());
		}
		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		AuctionAdvertisement auctionAdvertisement;

		auctionAdvertisement = this.auctionAdvertisementService.create();
		result = this.createEditModelAndView(auctionAdvertisement);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final AuctionAdvertisement auctionAdvertisement, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(auctionAdvertisement);
		else
			try {
				this.auctionAdvertisementService.save(auctionAdvertisement);
				result = new ModelAndView("redirect:myList.do");
			} catch (final Throwable oops) {
				String errorMessage = "advertisement.commit.error";

				if (oops.getMessage().contains("message.error"))
					errorMessage = oops.getMessage();

				result = this.createEditModelAndView(auctionAdvertisement, errorMessage);
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(final int auctionAdvertisementId) {
		ModelAndView result;

		try {
			this.auctionAdvertisementService.delete(auctionAdvertisementId);

		} catch (final Throwable oops) {

		}
		result = new ModelAndView("redirect:myList.do");

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final AuctionAdvertisement auctionAdvertisement) {
		ModelAndView result;

		result = this.createEditModelAndView(auctionAdvertisement, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final AuctionAdvertisement auctionAdvertisement, final String message) {
		ModelAndView result;
		Collection<Item> items;

		result = new ModelAndView("auctionAdvertisement/edit");
		result.addObject("auctionAdvertisement", auctionAdvertisement);
		result.addObject("message", message);
		items = this.itemService.findByPrincipal();
		result.addObject("items", items);

		return result;
	}
}
