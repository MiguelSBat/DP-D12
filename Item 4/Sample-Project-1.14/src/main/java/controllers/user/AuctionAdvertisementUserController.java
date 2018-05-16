
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuctionAdvertisementService;
import controllers.AbstractController;
import domain.AuctionAdvertisement;

@Controller
@RequestMapping("/auctionAdvertisement")
public class AuctionAdvertisementUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AuctionAdvertisementService	auctionAdvertisementService;


	// Constructors -----------------------------------------------------------

	public AuctionAdvertisementUserController() {
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
	//	public ModelAndView edit(@RequestParam final int advertisementId) {
	//		ModelAndView result;
	//		Advertisement advertisement;
	//
	//		advertisement = this.advertisementService.findOne(advertisementId);
	//
	//		result = this.createEditModelAndView(advertisement);
	//
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	//	public ModelAndView save(@Valid final Advertisement advertisement, final BindingResult binding) {
	//		ModelAndView result;
	//		System.out.println(advertisement.getPublicity());
	//		if(binding.getFieldError("publicity")!=null){
	//			result = createEditModelAndView(advertisement,"advertisement.publicityFail");
	//			return result;
	//		}
	//		if (binding.hasErrors())
	//			result = this.createEditModelAndView(advertisement);
	//		else
	//			try {
	//				this.advertisementService.save(advertisement);
	//				result = new ModelAndView("redirect:list.do");
	//			} catch (final Throwable oops) {
	//				String errorMessage = "advertisement.commit.error";
	//
	//				if (oops.getMessage().contains("message.error"))
	//					errorMessage = oops.getMessage();
	//				
	//				result = this.createEditModelAndView(advertisement, errorMessage);
	//			}
	//
	//		return result;
	//	}
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

		result = new ModelAndView("auctionAdvertisement/edit");
		result.addObject("auctionAdvertisement", auctionAdvertisement);
		result.addObject("message", message);

		return result;
	}
}
