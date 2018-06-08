
package controllers.business;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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
import domain.Actor;
import domain.FacturationData;
import domain.SaleLine;
import domain.ShipmentAddress;
import domain.ShippingInfo;
import domain.Ticket;

@Controller
@RequestMapping("/business/ticket")
public class TicketBusinessController extends AbstractController {

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

	public TicketBusinessController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/mySales", method = RequestMethod.GET)
	public ModelAndView sales() {
		ModelAndView result;
		Actor principal;
		Collection<Ticket> tickets;

		principal = this.actorService.findByPrincipal();
		tickets = this.ticketService.findByBusinessId(principal.getId());
		result = new ModelAndView("ticket/list");
		result.addObject("tickets", tickets);
		result.addObject("listType", "mySales");
		result.addObject("requestURI", "business/ticket/mySales.do");

		return result;
	}
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public ModelAndView received(final String status, final int ticketId) {
		ModelAndView result;
		Assert.isTrue(status != null);

		this.ticketService.changeStatus(ticketId, status);
		result = new ModelAndView("redirect:/business/ticket/display.do?ticketId=" + ticketId);

		return result;
	}
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int ticketId) {
		ModelAndView result;
		Ticket ticket;
		Actor principal;
		Collection<SaleLine> saleLines;
		ShipmentAddress shipmentAddress;
		ShippingInfo shippingInfo;
		FacturationData facturationData;

		result = new ModelAndView("ticket/display");
		principal = this.actorService.findByPrincipal();
		ticket = this.ticketService.findOne(ticketId);
		saleLines = this.saleLineService.findByTicketId(ticketId);
		shipmentAddress = this.shipmentAddressService.findByTicketId(ticketId);
		shippingInfo = this.shippingInfoService.findByTicketId(ticketId);
		facturationData = this.facturationDataService.findByTicketId(ticketId);

		result.addObject("ticket", ticket);
		result.addObject("saleLines", saleLines);
		result.addObject("shipmentAddress", shipmentAddress);
		result.addObject("shippingInfo", shippingInfo);
		result.addObject("facturationData", facturationData);
		result.addObject("principal", principal);
		result.addObject("requestURI", "business/ticket/display.do?ticketId=" + ticketId);
		return result;
	}

}
