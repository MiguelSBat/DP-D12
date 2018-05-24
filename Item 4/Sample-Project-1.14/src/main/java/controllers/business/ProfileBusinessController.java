
package controllers.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.BusinessService;
import controllers.AbstractController;
import domain.Actor;
import domain.Business;

@Controller
@RequestMapping("/business/profile")
public class ProfileBusinessController extends AbstractController {

	//Services

	@Autowired
	private ActorService	actorService;

	@Autowired
	private BusinessService	businessService;


	//Constructor

	public ProfileBusinessController() {
		super();
	}

	//Methods

	@RequestMapping(value = "/premium", method = RequestMethod.GET)
	public ModelAndView premium() {
		ModelAndView result;
		Actor actor;
		Business business;

		//TODO Añadir la parte de Paypal
		actor = this.actorService.findByPrincipal();
		business = (Business) actor;
		business.setPremium(true);
		this.businessService.save(business);

		result = new ModelAndView("redirect:/");
		return result;
	}
}
