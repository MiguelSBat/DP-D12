
package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FacturationDataService;
import services.SaleLineService;
import services.ShipmentAddressService;
import services.ShippingInfoService;
import services.TicketService;
import services.UserService;
import controllers.AbstractController;
import domain.ShippingInfo;
import domain.Ticket;

@Controller
@RequestMapping("/user/shippingInfo")
public class ShippingInfoUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService				userService;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private TicketService			ticketService;
	@Autowired
	private SaleLineService			saleLineService;
	@Autowired
	private ShipmentAddressService	shipmentAddressService;
	@Autowired
	private ShippingInfoService		shippingInfoService;
	@Autowired
	private FacturationDataService	facturationDataService;


	// Constructors -----------------------------------------------------------

	public ShippingInfoUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int ticketId) {
		ModelAndView result;
		ShippingInfo shippingInfo;
		Ticket ticket;

		ticket = this.ticketService.findOne(ticketId);
		shippingInfo = this.shippingInfoService.create();
		shippingInfo.setTicket(ticket);
		result = this.createEditModelAndView(shippingInfo);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ShippingInfo shippingInfo, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(shippingInfo);
		else
			try {
				this.shippingInfoService.save(shippingInfo);

				result = new ModelAndView("redirect:/user/ticket/display.do?ticketId=" + shippingInfo.getticket().getId());

			} catch (final Throwable oops) {
				final String errorMessage = "shippingInfo.commit.error";
				result = this.createEditModelAndView(shippingInfo, errorMessage);
			}

		return result;
	}
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final ShippingInfo shippingInfo) {
		ModelAndView result;

		result = this.createEditModelAndView(shippingInfo, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ShippingInfo shippingInfo, final String message) {
		ModelAndView result;

		result = new ModelAndView("shippingInfo/edit");
		result.addObject("shippingInfo", shippingInfo);
		result.addObject("message", message);

		return result;
	}
}
