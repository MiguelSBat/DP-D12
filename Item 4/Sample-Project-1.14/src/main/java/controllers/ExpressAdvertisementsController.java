
package controllers;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdvertisementService;
import services.ExpressAdvertisementService;
import services.ItemService;
import services.ShopAdvertisementService;
import services.UserService;
import domain.Actor;
import domain.Business;
import domain.ExpressAdvertisement;
import domain.Item;
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
	private ItemService					itemService;

	@Autowired
	private ExpressAdvertisementService	expressAdvertisementService;
	@Autowired
	private ShopAdvertisementService	shopAdvertisementService;


	// Constructors -----------------------------------------------------------

	public ExpressAdvertisementsController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ExpressAdvertisement eAdvertisement;

		eAdvertisement = this.expressAdvertisementService.create();
		result = this.createEditModelAndView(eAdvertisement);

		return result;
	}

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
		boolean delete=true;
		result.addObject("delete",delete);
		return result;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public ModelAndView delete(@RequestParam final int expressAdvertisementId) {
			ModelAndView result;
	
			ExpressAdvertisement expressAdvertisement=expressAdvertisementService.findOne(expressAdvertisementId);
		
			try {
				this.expressAdvertisementService.delete(expressAdvertisement);
				result = new ModelAndView("redirect:MyList.do");
			} catch (final Throwable oops) {
				String errorMessage = "advertisement.commit.error";
	
				if (oops.getMessage().contains("message.error"))
					errorMessage = oops.getMessage();
				result = this.createEditModelAndView(expressAdvertisement, errorMessage);
			}
			return result;
		}

	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ExpressAdvertisement expressAdvertisement, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()){
			String message= "advertisement.commit.error";
			
			result = this.createEditModelAndView(expressAdvertisement,message);
		}else
			try {
				this.expressAdvertisementService.save(expressAdvertisement);
				result = new ModelAndView("redirect:MyList.do");
			} catch (final Throwable oops) {
				String errorMessage = "advertisement.commit.error";

				if (oops.getMessage().contains("message.error"))
					errorMessage = oops.getMessage();
				if(expressAdvertisementService.isTabooThisExpressAdvertisement(expressAdvertisement)){
					errorMessage= "ExpressAdvertisement.tabuError";
				}
				result = this.createEditModelAndView(expressAdvertisement, errorMessage);
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final  ExpressAdvertisement expressAdvertisement) {
		ModelAndView result;

		result = this.createEditModelAndView(expressAdvertisement, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final  ExpressAdvertisement expressAdvertisement, final String message) {
		ModelAndView result;
		Collection<Item> items;
		Boolean tabu =false;
	
	
		result = new ModelAndView("expressAdvertisement/edit");
		result.addObject("expressAdvertisement", expressAdvertisement);
		result.addObject("message", message);
		items = this.itemService.findByPrincipal();
		result.addObject("items", items);
	

		return result;
		
	}
}
