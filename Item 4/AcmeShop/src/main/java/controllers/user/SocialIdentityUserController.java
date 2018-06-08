
package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		SocialIdentity socialIdentity;

		socialIdentity = this.socialIdentityService.create();
		result = this.createEditModelAndView(socialIdentity);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int socialIdentityId) {
		ModelAndView result;
		SocialIdentity socialIdentity;

		socialIdentity = this.socialIdentityService.findOne(socialIdentityId);
		result = this.createEditModelAndView(socialIdentity);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(final int socialIdentityId) {
		ModelAndView result;
		SocialIdentity socialIdentity;
		Actor principal;

		principal = this.actorService.findByPrincipal();
		socialIdentity = this.socialIdentityService.findOne(socialIdentityId);
		this.socialIdentityService.delete(socialIdentity);
		result = new ModelAndView("redirect:/actor/display.do?actorId=" + principal.getId());
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SocialIdentity socialIdentity, final BindingResult binding) {
		ModelAndView result;
		Actor principal;

		if (binding.hasErrors())
			result = this.createEditModelAndView(socialIdentity);
		else
			try {
				this.socialIdentityService.save(socialIdentity);
				principal = this.actorService.findByPrincipal();
				result = new ModelAndView("redirect:/actor/display.do?actorId=" + principal.getId());

			} catch (final Throwable oops) {
				final String errorMessage = "socialIdentity.commit.error";
				result = this.createEditModelAndView(socialIdentity, errorMessage);
			}

		return result;
	}

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
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final SocialIdentity socialIdentity) {
		ModelAndView result;

		result = this.createEditModelAndView(socialIdentity, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final SocialIdentity socialIdentity, final String message) {
		ModelAndView result;
		int principalId;

		principalId = this.actorService.findByPrincipal().getId();
		result = new ModelAndView("socialIdentity/edit");
		result.addObject("socialIdentity", socialIdentity);
		result.addObject("message", message);
		result.addObject("principalId", principalId);

		return result;
	}

}
