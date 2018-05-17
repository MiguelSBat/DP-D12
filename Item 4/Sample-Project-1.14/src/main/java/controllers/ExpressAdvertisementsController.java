
package controllers;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdvertisementService;
import services.AuctionAdvertisementService;
import services.ExpressAdvertisementService;
import services.ShopAdvertisementService;
import services.UserService;
import domain.Actor;
import domain.Advertisement;
import domain.Business;
import domain.ExpressAdvertisement;
import domain.User;

@Controller
@RequestMapping("/expressAdvertisement")
public class ExpressAdvertisementsController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService					userService;
	@Autowired
	private ActorService				actorService;
	@Autowired
	private AdvertisementService		advertisementService;
	@Autowired
	private AuctionAdvertisementService	auctionAdvertisementService;
	@Autowired
	private ExpressAdvertisementService	expressAdvertisementService;
	@Autowired
	private ShopAdvertisementService	shopAdvertisementService;


	// Constructors -----------------------------------------------------------

	public ExpressAdvertisementsController() {
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
		Collection<ExpressAdvertisement> advertisements;

		advertisements = this.expressAdvertisementService.findNotPast();

		result = new ModelAndView("expressAdvertisement/list");
		result.addObject("advertisements", advertisements);

		return result;
	}

	//	 display

	@RequestMapping(value = "/MyList", method = RequestMethod.GET)
	public ModelAndView MyList() {
		ModelAndView result;
		Collection<ExpressAdvertisement> advertisements=new HashSet<>();
		Actor a=actorService.findByPrincipal();
		
		
		if(a instanceof Business){
			Business b= (Business) a;
			advertisements = this.expressAdvertisementService.findExpressByBussiness(b.getId());

		}
		if(a instanceof User){
			User u= (User) a;
			advertisements = this.expressAdvertisementService.findExpressByUser(u.getId());

		}
		result = new ModelAndView("expressAdvertisement/list");
		result.addObject("advertisements", advertisements);

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

		result = new ModelAndView("expressAdvertisement/edit");
		result.addObject("advertisement", advertisement);
		result.addObject("message", message);

		return result;
	}
}