
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.BidService;
import services.ItemService;
import services.QuestionService;
import domain.Item;
import domain.Question;

@Controller
@RequestMapping("/question")
public class QuestionController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private QuestionService	questionService;

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

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int businessId) {
		ModelAndView result;
		Collection<Question> questions;

		questions = this.questionService.findByBusiness(businessId);

		result = new ModelAndView("question/list");
		result.addObject("questions", questions);

		return result;
	}

	//	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	//	public ModelAndView myList(final String criteria) {
	//		ModelAndView result;
	//		Collection<Question> questions;
	//		Actor actor;
	//
	//		questions = this.questionService.findByPrincipal();
	//		result = new ModelAndView("question/list");
	//		result.addObject("questions", questions);
	//		if (this.actorService.isLogged()) {
	//			actor = this.actorService.findByPrincipal();
	//			if (actor instanceof User)
	//				result.addObject("userId", actor.getId());
	//			else if (actor instanceof Business)
	//				result.addObject("businessId", actor.getId());
	//		}
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/bidded", method = RequestMethod.GET)
	//	public ModelAndView bidded() {
	//		ModelAndView result;
	//		Collection<Question> questions;
	//
	//		questions = this.questionService.findByprincipal();
	//
	//		result = new ModelAndView("question/list");
	//		result.addObject("questions", questions);
	//
	//		return result;
	//	}
	//
	//	// Creation ---------------------------------------------------------------
	//
	//	@RequestMapping(value = "/create", method = RequestMethod.GET)
	//	public ModelAndView create() {
	//		ModelAndView result;
	//		Question question;
	//
	//		question = this.questionService.create();
	//		result = this.createEditModelAndView(question);
	//
	//		return result;
	//	}
	//
	//	// Edition ----------------------------------------------------------------
	//
	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	//	public ModelAndView save(@Valid final Question question, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		if (binding.hasErrors())
	//			result = this.createEditModelAndView(question);
	//		else
	//			try {
	//				this.questionService.save(question);
	//				result = new ModelAndView("redirect:myList.do");
	//			} catch (final Throwable oops) {
	//				String errorMessage = "advertisement.commit.error";
	//
	//				if (oops.getMessage().contains("message.error"))
	//					errorMessage = oops.getMessage();
	//
	//				result = this.createEditModelAndView(question, errorMessage);
	//			}
	//
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	//	public ModelAndView delete(final int questionId) {
	//		ModelAndView result;
	//
	//		try {
	//			this.questionService.delete(questionId);
	//
	//		} catch (final Throwable oops) {
	//
	//		}
	//		result = new ModelAndView("redirect:myList.do");
	//
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/bid", method = RequestMethod.GET)
	//	public ModelAndView bid(@RequestParam final int questionId, @RequestParam final Double amount) {
	//		ModelAndView result;
	//		Bid bid;
	//
	//		//TODO cambiar el check en el jsp
	//		result = new ModelAndView("redirect:/advertisement/display.do?advertisementId=" + questionId);
	//		try {
	//			bid = this.bidService.createAndSave(questionId, amount);
	//			result.addObject("bided", true);
	//		} catch (final Throwable oops) {
	//			if (oops.getMessage().contains("error.minimumBid"))
	//				result.addObject("minimumBid", true);
	//			result.addObject("bidError", true);
	//		}
	//
	//		return result;
	//	}
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Question question) {
		ModelAndView result;

		result = this.createEditModelAndView(question, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Question question, final String message) {
		ModelAndView result;
		final Collection<Item> items;

		result = new ModelAndView("question/edit");
		result.addObject("question", question);
		result.addObject("message", message);

		return result;
	}
}
