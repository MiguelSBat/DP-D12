
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AnswerService;
import services.BidService;
import services.BusinessService;
import services.ItemService;
import services.QuestionService;
import domain.Answer;
import domain.Question;

@Controller
@RequestMapping("/question")
public class QuestionController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private QuestionService	questionService;

	@Autowired
	private BusinessService	businessService;

	@Autowired
	private AnswerService	answerService;

	@Autowired
	private ItemService		itemService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private BidService		bidService;


	// Constructors -----------------------------------------------------------

	public QuestionController() {
		super();
	}

	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int questionId) {
		ModelAndView result;
		Collection<Answer> answers;
		Question question;

		answers = this.answerService.findByQuestion(questionId);
		question = this.questionService.findOne(questionId);
		result = new ModelAndView("question/display");
		result.addObject("answers", answers);
		result.addObject("question", question);

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Question question) {
		ModelAndView result;

		result = this.createEditModelAndView(question, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Question question, final String message) {
		ModelAndView result;

		result = new ModelAndView("question/edit");
		result.addObject("question", question);
		result.addObject("message", message);

		return result;
	}
}
