
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import controllers.AbstractController;
import domain.Actor;

@Controller
@RequestMapping("/administrator/actor")
public class ActorAdministratorController extends AbstractController {

	//Service -----------------------------------------------------------------
	@Autowired
	private ActorService	actorService;


	//Constructor

	public ActorAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Actor> actors;

		actors = this.actorService.findSuspicious();
		result = new ModelAndView("actor/list");
		result.addObject("actors", actors);
		result.addObject("requestURI", "administrator/actor/list.do");

		return result;
	}

	@RequestMapping(value = "/softBan", method = RequestMethod.GET)
	public ModelAndView softBan(@RequestParam final int actorId) {
		ModelAndView result;

		this.actorService.softBan(actorId);
		result = new ModelAndView("redirect:/administrator/actor/list.do");

		return result;
	}

	@RequestMapping(value = "/hardBan", method = RequestMethod.GET)
	public ModelAndView hardBan(@RequestParam final int actorId) {
		ModelAndView result;

		this.actorService.hardBan(actorId);
		result = new ModelAndView("redirect:/administrator/actor/list.do");

		return result;
	}

}
