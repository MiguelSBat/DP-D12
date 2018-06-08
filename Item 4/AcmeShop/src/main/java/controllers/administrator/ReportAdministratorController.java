
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.BusinessService;
import services.ReportService;
import controllers.AbstractController;
import domain.Report;

@Controller
@RequestMapping("/administrator/report")
public class ReportAdministratorController extends AbstractController {

	//Service -----------------------------------------------------------------
	@Autowired
	private BusinessService	businessService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private ReportService	reportService;


	//Constructor

	public ReportAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int actorId) {
		ModelAndView result;
		Collection<Report> reports;

		reports = this.reportService.getReportsByReportedActor(actorId);
		result = new ModelAndView("report/list");
		result.addObject("reports", reports);
		result.addObject("requestURI", "administrator/report/list.do");

		return result;
	}

}
