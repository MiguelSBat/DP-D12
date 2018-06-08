
package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.UserService;
import controllers.AbstractController;
import domain.Actor;
import domain.User;

@Controller
@RequestMapping("/user/profile")
public class ProfileUserController extends AbstractController {

	//Services

	@Autowired
	private ActorService	actorService;

	@Autowired
	private UserService		userService;


	//Constructor

	public ProfileUserController() {
		super();
	}

	//Methods

	@RequestMapping(value = "/premium", method = RequestMethod.GET)
	public ModelAndView premium() {
		ModelAndView result;
		Actor actor;
		User user;

		//TODO Añadir la parte de Paypal
		actor = this.actorService.findByPrincipal();
		user = (User) actor;
		user.setPremium(true);
		this.userService.save(user);

		result = new ModelAndView("redirect:/");
		return result;
	}
}
