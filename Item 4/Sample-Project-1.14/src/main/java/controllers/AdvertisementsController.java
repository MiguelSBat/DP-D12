
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;

import services.AdvertisementService;
import services.UserService;

import domain.Advertisement;
import domain.User;

@Controller
@RequestMapping("/advertisement")
public class AdvertisementsController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService			userService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private AdvertisementService		advertisementService;


	// Constructors -----------------------------------------------------------

	public AdvertisementsController() {
		super();
	}

	// Creation ---------------------------------------------------------------
//
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView create() {
//		ModelAndView result;
//		Advertisement advertisement;
//
//		advertisement = this.advertisementService.create();
//		result = this.createEditModelAndView(advertisement);
//
//		return result;
//	}
	

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final String criteria) {
		ModelAndView result;
		Collection<Advertisement> advertisements;


		advertisements = this.advertisementService.findByCriteria(criteria);
		if (criteria == null)
			advertisements = this.advertisementService.findAll();

		result = new ModelAndView("advertisement/list");
		result.addObject("advertisements", advertisements);
		

		return result;
	}

	// display
//
//	@RequestMapping(value = "display", method = RequestMethod.GET)
//	public ModelAndView display(@RequestParam final int advertisementId) {
//		final ModelAndView result;
//		result = new ModelAndView("advertisement/display");
//		final Advertisement advertisement;
//
//		advertisement = this.advertisementService.findOne(advertisementId);
//		result.addObject("advertisement", advertisement);
//		boolean mostrarArticles = true;
//		mostrarArticles = false;
//		User u;
//		if (this.actorService.isLogged())
//			if (this.actorService.findByPrincipal() instanceof User) {
//				final User actual = (User) this.actorService.findByPrincipal();
//				final Boolean EsAutor = actual.getAdvertisements().contains(advertisement);
//				final Boolean NoEstaPublicado = advertisement.getPublicationDate() == null;
//				final Boolean Mostrar = NoEstaPublicado && EsAutor;
//				result.addObject("EsAutor", Mostrar);
//				mostrarArticles = EsAutor;
//			}
//		final Collection<Article> articles = advertisement.getArticles();
//		final TreeMap<Integer, User> mapaMegaComplejo = new TreeMap<>();
//
//		for (final Article a : articles) {
//			u = this.userService.UserByArticle(a.getId());
//
//			mapaMegaComplejo.put(a.getId(), u);
//		}
//		if (advertisement.getPublicity() == false)
//			mostrarArticles = true;
//		if (advertisement.getPublicity() == true)
//			if (this.actorService.isLogged() && this.actorService.findByPrincipal() instanceof Customer) {
//				final Customer c = (Customer) this.actorService.findByPrincipal();
//				final Collection<Advertisement> customerAdvertisements = this.advertisementService.findByCustomerID(c.getId());
//				final Collection<Advertisement> customerAdvertisementVolumes = this.advertisementService.findByCustomerIDAndVolumes(c.getId());
//				if (customerAdvertisements.contains(advertisement) || customerAdvertisementVolumes.contains(advertisement))
//					mostrarArticles = true;
//			}
//
//		result.addObject("mapaMegaComplejo", mapaMegaComplejo);
//		result.addObject("mostrarArticles", mostrarArticles);
//
//		return result;
//	}
	// Edition ----------------------------------------------------------------

//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public ModelAndView edit(@RequestParam final int advertisementId) {
//		ModelAndView result;
//		Advertisement advertisement;
//
//		advertisement = this.advertisementService.findOne(advertisementId);
//
//		result = this.createEditModelAndView(advertisement);
//
//		return result;
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
//	public ModelAndView save(@Valid final Advertisement advertisement, final BindingResult binding) {
//		ModelAndView result;
//		System.out.println(advertisement.getPublicity());
//		if(binding.getFieldError("publicity")!=null){
//			result = createEditModelAndView(advertisement,"advertisement.publicityFail");
//			return result;
//		}
//		if (binding.hasErrors())
//			result = this.createEditModelAndView(advertisement);
//		else
//			try {
//				this.advertisementService.save(advertisement);
//				result = new ModelAndView("redirect:list.do");
//			} catch (final Throwable oops) {
//				String errorMessage = "advertisement.commit.error";
//
//				if (oops.getMessage().contains("message.error"))
//					errorMessage = oops.getMessage();
//				
//				result = this.createEditModelAndView(advertisement, errorMessage);
//			}
//
//		return result;
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
//	public ModelAndView delete(final Advertisement advertisement, final BindingResult binding) {
//		ModelAndView result;
//
//		try {
//			this.advertisementService.delete(advertisement);
//			result = new ModelAndView("redirect:list.do");
//		} catch (final Throwable oops) {
//			String errorMessage = "category.commit.error";
//
//			if (oops.getMessage().contains("message.error"))
//				errorMessage = oops.getMessage();
//			result = this.createEditModelAndView(advertisement, errorMessage);
//		}
//		return result;
//	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Advertisement advertisement) {
		ModelAndView result;

		result = this.createEditModelAndView(advertisement, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Advertisement advertisement, final String message) {
		ModelAndView result;

		result = new ModelAndView("advertisement/edit");
		result.addObject("advertisement", advertisement);
		result.addObject("message", message);

		return result;
	}
}
