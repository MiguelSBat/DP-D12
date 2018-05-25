
package controllers.business;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ItemService;
import controllers.AbstractController;
import domain.Actor;
import domain.Item;

@Controller
@RequestMapping("/business/item")
public class ItemBusinessController extends AbstractController {

	//Service -----------------------------------------------------------------
	@Autowired
	private ItemService		itemService;

	@Autowired
	private ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public ItemBusinessController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Item> items;
		Actor principal;

		principal = this.actorService.findByPrincipal();
		items = this.itemService.findByBusiness(principal.getId());
		result = new ModelAndView("item/list");
		result.addObject("items", items);
		result.addObject("requestURI", "business/item/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Item item;

		item = this.itemService.create();
		result = this.createEditModelAndView(item);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Item item, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(item);
		else
			try {
				this.itemService.save(item);

				result = new ModelAndView("redirect:/business/item/list.do");

			} catch (final Throwable oops) {
				String errorMessage = "item.commit.error";

				if (oops.getMessage().contains("message.error"))
					errorMessage = oops.getMessage();
				result = this.createEditModelAndView(item, errorMessage);
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Item item) {
		ModelAndView result;

		result = this.createEditModelAndView(item, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Item item, final String message) {
		ModelAndView result;

		result = new ModelAndView("item/edit");
		result.addObject("item", item);
		result.addObject("message", message);

		return result;
	}

}
