
package controllers.moderator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.BusinessService;
import controllers.AbstractController;
import domain.Business;

@Controller
@RequestMapping("/moderator/business")
public class BusinessModeratorController extends AbstractController {

	//Service -----------------------------------------------------------------
	@Autowired
	private BusinessService	businessService;

	@Autowired
	private ActorService	actorService;


	//Constructor

	public BusinessModeratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Business> businesses;

		businesses = this.businessService.findNotVerified();
		result = new ModelAndView("business/list");
		result.addObject("businesses", businesses);
		result.addObject("requestURI", "moderator/business/list.do");

		return result;
	}

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public ModelAndView verify(@RequestParam final int businessId) {
		ModelAndView result;
		Business business;

		business = this.businessService.findOne(businessId);
		//TODO: Intentional bug: Verify en moderador no verifica el negocio en cuestión
		//business.setVerified(true);
		business.setVerified(false);
		this.businessService.save(business);
		result = new ModelAndView("redirect:/moderator/business/list.do");

		return result;
	}
}
