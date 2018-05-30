
package controllers.user;

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
@RequestMapping("/user/item")
public class ItemUserController extends AbstractController {

	//Service -----------------------------------------------------------------
	@Autowired
	private ItemService		itemService;

	@Autowired
	private ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public ItemUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Item> items;
		Actor principal;

		principal = this.actorService.findByPrincipal();
		items = this.itemService.findByUser(principal.getId());
		result = new ModelAndView("item/list");
		result.addObject("items", items);
		result.addObject("requestURI", "user/item/list.do");

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
				//TODO: Cuando se pueda, cambiarlo para activar el bug intencional
				//				final Item bug = new Item();
				//				bug.setName("Es un bug intencional");
				//				bug.setDescription("Esto es un Bug Intencionado, Felicidades por encontrarme");
				//				bug.setPhoto("https://thumb7.shutterstock.com/display_pic_with_logo/1699708/422011357/stock-vector-stick-insect-or-phasmids-or-ghost-insects-or-walking-sticks-isolated-on-white-stick-bugs-engraved-422011357.jpg");
				//				this.itemService.save(bug);

				result = new ModelAndView("redirect:/user/item/list.do");

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
