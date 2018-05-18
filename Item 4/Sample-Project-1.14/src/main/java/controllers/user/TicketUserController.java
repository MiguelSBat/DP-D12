
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdvertisementService;
import services.TicketService;
import services.UserService;
import controllers.AbstractController;
import domain.Actor;
import domain.Advertisement;
import domain.Ticket;

@Controller
@RequestMapping("/user/ticket")
public class TicketUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService				userService;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private TicketService			ticketService;
	@Autowired
	private AdvertisementService	advertisementService;


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
		result.addObject("listType", "myTickets");

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
		result.addObject("listType", "mySales");

		return result;
	}
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public ModelAndView received(final String status, final int ticketId) {
		ModelAndView result;
		Assert.isTrue(status != null);

		this.ticketService.changeStatus(ticketId, status);
		result = new ModelAndView("redirect:advertisement/list.do");

		return result;
	}
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int ticketId) {
		ModelAndView result;
		Ticket ticket;
		Collection<Advertisement> advertisements;

		result = new ModelAndView("ticket/display");
		ticket = this.ticketService.findOne(ticketId);
		advertisements = this.advertisementService.findByTicketId(ticketId);
		result.addObject("ticket", ticket);
		result.addObject("advertisements", advertisements);
		return result;
	}

}
