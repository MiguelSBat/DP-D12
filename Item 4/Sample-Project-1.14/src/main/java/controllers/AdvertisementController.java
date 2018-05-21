
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdvertisementService;
import services.BidService;
import domain.Advertisement;
import domain.AuctionAdvertisement;
import domain.Bid;
import domain.ExpressAdvertisement;

@Controller
@RequestMapping("/advertisement")
public class AdvertisementController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AdvertisementService	advertisementService;
	@Autowired
	private BidService				bidService;


	// Constructors -----------------------------------------------------------

	public AdvertisementController() {
		super();
	}

	// Creation ---------------------------------------------------------------
	//
	//	@RequestMapping(value = "/create", method = RequestMethod.GET)
	//	public ModelAndView create() {
	//		ModelAndView result;
	//		Advertisement advertisement;
	//
	//		advertisement = this.advertisementService.create();
	//		result = this.createEditModelAndView(advertisement);
	//
	//		return result;
	//	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final String criteria) {
		ModelAndView result;
		Collection<Advertisement> advertisements;

		advertisements = this.advertisementService.findByCriteria(criteria);
		if (criteria == null || criteria == "")
			advertisements = this.advertisementService.findNotPAst();

		result = new ModelAndView("advertisement/list");
		result.addObject("advertisements", advertisements);

		return result;
	}

	//	 display

	@RequestMapping(value = "display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int advertisementId) {
		ModelAndView result;
		Advertisement advertisement;
		String advertisementType;
		Collection<Bid> bids;

		//		advertisement = this.advertisementService.findOne(advertisementId);

		result = new ModelAndView("advertisement/display");
		advertisement = this.advertisementService.findOne(advertisementId);
		if (advertisement instanceof AuctionAdvertisement) {
			advertisementType = "auction";
			bids = this.bidService.findOrderedByAuction(advertisement.getId());
			result.addObject("bids", bids);
		} else if (advertisement instanceof ExpressAdvertisement)
			advertisementType = "express";
		else
			advertisementType = "shop";

		result.addObject("advertisement", advertisement);
		result.addObject("type", advertisementType);
		return result;
	}
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

	protected ModelAndView createEditModelAndView(final Advertisement advertisement) {
		ModelAndView result;

		result = this.createEditModelAndView(advertisement, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Advertisement advertisement, final String message) {
		ModelAndView result;

		result = new ModelAndView("advertisement/edit");
		result.addObject("advertisement", advertisement);
		result.addObject("message", message);

		return result;
	}
}
