
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ConfigService;
import controllers.AbstractController;
import domain.Config;
import forms.SpamWordForm;

@Controller
@RequestMapping("/administrator/config")
public class ConfigAdministratorController extends AbstractController {

	//Service -----------------------------------------------------------------
	@Autowired
	private ConfigService	configService;

	@Autowired
	private ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public ConfigAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Config config;

		config = this.configService.findConfiguration();

		result = new ModelAndView("config/display");
		result.addObject("config", config);
		result.addObject("requestURI", "administrator/config/display.do");

		return result;
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		final ModelAndView result;
		Config config;

		config = this.configService.findConfiguration();
		result = this.createEditModelAndView(config);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Config config, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(config);
		else
			try {
				this.configService.save(config);
				result = new ModelAndView("redirect:/administrator/config/display.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(config, "config.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/removeSpam", method = RequestMethod.GET)
	public ModelAndView removeSpam(final String spamWord) {
		ModelAndView result;

		result = new ModelAndView("redirect:/administrator/config/display.do");

		this.configService.removeSpamWord(spamWord);

		return result;
	}
	@RequestMapping(value = "/addSpam", method = RequestMethod.GET)
	public ModelAndView addSpam() {
		ModelAndView result;
		SpamWordForm spamWordForm;

		spamWordForm = new SpamWordForm();
		result = this.createEditModelAndView(spamWordForm);

		return result;
	}

	@RequestMapping(value = "/addSpam", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SpamWordForm spamWordForm, final BindingResult binding) {
		ModelAndView result;
		Config config;
		Collection<String> spamWords;

		if (binding.hasErrors())
			result = this.createEditModelAndView(spamWordForm);
		else
			try {
				config = this.configService.findConfiguration();
				spamWords = config.getSpamWords();
				spamWords.add(spamWordForm.getSpamWord());
				config.setSpamWords(spamWords);
				this.configService.save(config);
				result = new ModelAndView("redirect:/administrator/config/display.do");
			} catch (final Throwable oops) {
				String errorMessage = "config.commit.error";

				if (oops.getMessage().contains("message.error"))
					errorMessage = oops.getMessage();
				result = this.createEditModelAndView(spamWordForm, errorMessage);
			}

		return result;
	}
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final SpamWordForm spamWordForm) {
		ModelAndView result;

		result = this.createEditModelAndView(spamWordForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final SpamWordForm spamWordForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("config/addSpam");
		result.addObject("spamWordForm", spamWordForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Config config) {
		ModelAndView result;

		result = this.createEditModelAndView(config, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Config config, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("config/edit");
		result.addObject("config", config);
		result.addObject("message", messageCode);

		return result;
	}
}
