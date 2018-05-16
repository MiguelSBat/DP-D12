
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.TicketService;
import services.UserService;
import controllers.AbstractController;
import domain.Actor;
import domain.Ticket;

@Controller
@RequestMapping("/user/ticket")
public class TicketUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService		userService;
	@Autowired
	private ActorService	actorService;
	@Autowired
	private TicketService	ticketService;


	// Constructors -----------------------------------------------------------

	public TicketUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/myTickets", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Actor principal;
		Collection<Ticket> tickets;

		principal = this.actorService.findByPrincipal();
		tickets = this.ticketService.findByUserId(principal.getId());
		result = new ModelAndView("ticket/list");
		result.addObject("tickets", tickets);

		return result;
	}
	@RequestMapping(value = "/mySales", method = RequestMethod.GET)
	public ModelAndView sales() {
		ModelAndView result;
		Actor principal;
		Collection<Ticket> tickets;

		principal = this.actorService.findByPrincipal();
		tickets = this.ticketService.findBySellerId(principal.getId());
		result = new ModelAndView("ticket/list");
		result.addObject("tickets", tickets);

		return result;
	}

}
