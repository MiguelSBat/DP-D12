
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.BusinessService;
import services.ReportService;
import services.UserService;
import domain.Actor;
import domain.Report;

@Controller
@RequestMapping("/report")
public class ReportController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService		userService;
	@Autowired
	private ActorService	actorService;

	@Autowired
	private BusinessService	businessService;

	@Autowired
	private ReportService	reportService;


	// Constructors -----------------------------------------------------------

	public ReportController() {
		super();
	}
	// Listing ----------------------------------------------------------------
	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int actorId) {
		ModelAndView result;
		Report report;
		Actor actor;

		actor = this.actorService.findOne(actorId);
		report = this.reportService.create();
		report.setActor(actor);
		result = this.createEditModelAndView(report);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Report report, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(report);
		else
			try {
				this.reportService.save(report);

				result = new ModelAndView("redirect:/actor/display.do?actorId=" + report.getActor().getId());

			} catch (final Throwable oops) {
				String errorMessage = "report.commit.error";

				if (oops.getMessage().contains("message.error"))
					errorMessage = oops.getMessage();
				result = this.createEditModelAndView(report, errorMessage);
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Report report) {
		ModelAndView result;

		result = this.createEditModelAndView(report, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Report report, final String message) {
		ModelAndView result;

		result = new ModelAndView("report/edit");
		result.addObject("report", report);
		result.addObject("message", message);

		return result;
	}

}
