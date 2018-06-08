
package controllers;

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
import domain.SocialIdentity;

@Controller
@RequestMapping("/socialIdentity")
public class SocialIdentityController extends AbstractController {

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

	public SocialIdentityController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final int userId) {
		ModelAndView result;
		Collection<SocialIdentity> socialIdentities;

		socialIdentities = this.socialIdentityService.findByUserId(userId);
		result = new ModelAndView("socialIdentity/list");
		result.addObject("socialIdentities", socialIdentities);

		return result;
	}

}
