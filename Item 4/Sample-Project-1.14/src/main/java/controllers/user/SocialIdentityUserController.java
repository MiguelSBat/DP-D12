
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.BusinessService;
import services.SocialIdentityService;
import services.UserService;
import controllers.AbstractController;
import domain.Actor;
import domain.SocialIdentity;

@Controller
@RequestMapping("/user/socialIdentity")
public class SocialIdentityUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService				userService;
	@Autowired
	private ActorService			actorService;

	@Autowired
	private BusinessService			businessService;

	@Autowired
	private SocialIdentityService	socialIdentityService;


	// Constructors -----------------------------------------------------------

	public SocialIdentityUserController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Actor principal;
		Collection<SocialIdentity> socialIdentities;

		principal = this.actorService.findByPrincipal();
		socialIdentities = this.socialIdentityService.findByUserId(principal.getId());
		result = new ModelAndView("socialIdentity/list");
		result.addObject("socialIdentities", socialIdentities);

		return result;
	}

}
