
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.SocialIdentityService;
import services.ValorationService;
import domain.Actor;
import domain.Business;
import domain.BusinessInfo;
import domain.SocialIdentity;
import domain.User;
import forms.ActorForm;

@Controller
@RequestMapping("/actor")
public class ActorController {

	@Autowired
	private ActorService			actorService;
	@Autowired
	private ValorationService		valorationService;
	@Autowired
	private SocialIdentityService	socialIdentityService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final String actorType) throws Exception {
		ModelAndView result = null;
		ActorForm actorForm;

		actorForm = new ActorForm();
		actorForm.setAuthority(actorType);

		result = this.createRegisterModelAndView(actorForm);
		return result;
	}
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int actorId) throws Exception {
		ModelAndView result;
		Collection<SocialIdentity> socialIdentities;
		int principalId;
		Actor actor;
		result = new ModelAndView("actor/display");
		//valoraciones 
		Double sum = 0.0;
		if (this.valorationService.getValorations(actorId) != null) {
			sum = this.valorationService.getValorations(actorId);
			result.addObject("sum", sum);
		}
		//Fin valoraciones
		principalId = 0;
		if (this.actorService.isLogged())
			principalId = this.actorService.findByPrincipal().getId();
		if (actorId == 0)
			actor = this.actorService.findByPrincipal();
		else
			actor = this.actorService.findOne(actorId);

		//mostrar businessInfos
		if (actor instanceof Business) {
			final Business b = (Business) actor;
			final Collection<BusinessInfo> businessInfos = b.getBusinessInfos();
			final boolean premium = b.getPremium();
			result.addObject("premium", premium);
			result.addObject("info", businessInfos);
		} else if (actor instanceof User) {
			socialIdentities = this.socialIdentityService.findByUserId(actorId);
			final boolean premium = ((User) actor).isPremium();
			result.addObject("premium", premium);
			result.addObject("socialIdentities", socialIdentities);
		}

		result.addObject("requestURI", "actor/display.do?actorId=" + actorId);
		result.addObject("actor", actor);
		result.addObject("principalId", principalId);

		return result;
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView register(@Valid final ActorForm actorForm, final BindingResult binding) {
		ModelAndView result;
		Actor actor;
		if (binding.hasErrors())
			result = this.createRegisterModelAndView(actorForm);
		else
			try {
				actor = this.actorService.create(actorForm);
				if (binding.hasErrors())
					result = this.createRegisterModelAndView(actorForm);
				else {
					actor = this.actorService.register(actor);
					result = new ModelAndView("redirect:/welcome/index.do");

				}
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createRegisterModelAndView(actorForm, "actor.commit.error");

			}
		return result;
	}

	protected ModelAndView createRegisterModelAndView(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createRegisterModelAndView(actorForm, null);

		return result;
	}
	protected ModelAndView createRegisterModelAndView(final ActorForm actorForm, final String message) {
		ModelAndView result;

		final String requestURI = "actor/register.do";

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", actorForm);
		result.addObject("message", message);
		result.addObject("requestURI", requestURI);
		return result;
	}

}
