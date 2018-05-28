
package controllers.business;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.QuestionService;
import controllers.AbstractController;
import domain.Actor;
import domain.Business;
import domain.Question;

@Controller
@RequestMapping("/business/question")
public class QuestionBusinessController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private QuestionService	questionService;

	@Autowired
	private ActorService	actorService;


	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Question> questions;
		Business business;

		business = (Business) this.actorService.findByPrincipal();
		questions = this.questionService.findByBusiness(business.getId());
		result = new ModelAndView("question/list");
		result.addObject("questions", questions);
		result.addObject("business", business);
		result.addObject("businessId", business.getId());

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(final int questionId) {
		ModelAndView result;
		Actor actor;

		try {
			this.questionService.delete(questionId);
		} catch (final Throwable oops) {

		}

		actor = this.actorService.findByPrincipal();
		result = new ModelAndView("redirect:/business/question/list.do?businessId=" + actor.getId());

		return result;
	}

}
