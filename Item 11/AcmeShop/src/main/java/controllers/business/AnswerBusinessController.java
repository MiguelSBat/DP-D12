
package controllers.business;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnswerService;
import controllers.AbstractController;
import domain.Answer;

@Controller
@RequestMapping("/business/answer")
public class AnswerBusinessController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AnswerService	answerService;


	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int questionId) {
		ModelAndView result;
		Answer answer;

		answer = this.answerService.create(questionId);
		result = this.createEditModelAndView(answer);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Answer answer, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(answer);
		else
			try {
				this.answerService.save(answer);
				result = new ModelAndView("redirect:/question/display.do?questionId=" + answer.getQuestion().getId());
			} catch (final Throwable oops) {
				final String errorMessage = "answer.commit.error";
				result = this.createEditModelAndView(answer, errorMessage);
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Answer answer) {
		ModelAndView result;

		result = this.createEditModelAndView(answer, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Answer answer, final String message) {
		ModelAndView result;

		result = new ModelAndView("answer/edit");
		result.addObject("answer", answer);
		result.addObject("message", message);

		return result;
	}
}
