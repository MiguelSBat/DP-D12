
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
import domain.AuctionAdvertisement;
import domain.Item;

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

		advertisements = this.auctionAdvertisementService.findByPrincipal();
		result = new ModelAndView("advertisement/list");
		result.addObject("advertisements", advertisements);

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
	//	 display

	// Edition ----------------------------------------------------------------

	//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	//	public ModelAndView edit(@RequestParam final int auctionAdvertisementId) {
	//		ModelAndView result;
	//		AuctionAdvertisement auctionAdvertisement;
	//
	//		auctionAdvertisement = this.auctionAdvertisementService.findOne(auctionAdvertisementId);
	//		result = this.createEditModelAndView(auctionAdvertisement);
	//
	//		return result;
	//	}
	//
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
	//
	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	//	public ModelAndView delete(final Advertisement advertisement, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		try {
	//			this.advertisementService.delete(advertisement);
	//			result = new ModelAndView("redirect:list.do");
	//		} catch (final Throwable oops) {
	//			String errorMessage = "category.commit.error";
	//
	//			if (oops.getMessage().contains("message.error"))
	//				errorMessage = oops.getMessage();
	//			result = this.createEditModelAndView(advertisement, errorMessage);
	//		}
	//		return result;
	//	}

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
