
package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ReviewService;
import services.ShopAdvertisementService;
import services.UserService;
import controllers.AbstractController;
import domain.Review;
import domain.ShopAdvertisement;
import domain.User;

@Controller
@RequestMapping("/user/review")
public class ReviewUserController extends AbstractController {

	//Service -----------------------------------------------------------------
	@Autowired
	private ReviewService				reviewService;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private ShopAdvertisementService	shopAdvertisementService;

	@Autowired
	private UserService					userService;


	// Constructors -----------------------------------------------------------

	public ReviewUserController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int advertisementId) {
		ModelAndView result;
		Review review;
		ShopAdvertisement shopAdvertisement;
		User user;
		Collection<User> aux;
		aux = this.userService.findByAdvertisementsBuyed(advertisementId);
		shopAdvertisement = this.shopAdvertisementService.findOne(advertisementId);
		user = this.userService.findByPrincipal();

		Assert.isTrue(aux.contains(user));

		review = this.reviewService.create();
		review.setShopAdvertisement(shopAdvertisement);
		review.setUser(user);
		result = this.createEditModelAndView(review);
		//	result.addObject("shopAdvertisementId", shopAdvertisementId);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Review review, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(review);
		else
			try {
				this.reviewService.save(review);

				result = new ModelAndView("redirect:/advertisement/display.do?advertisementId=" + review.getShopAdvertisement().getId());

			} catch (final Throwable oops) {
				String errorMessage = "review.commit.error";

				if (oops.getMessage().contains("message.error"))
					errorMessage = oops.getMessage();
				result = this.createEditModelAndView(review, errorMessage);
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Review review) {
		ModelAndView result;

		result = this.createEditModelAndView(review, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Review review, final String message) {
		ModelAndView result;

		result = new ModelAndView("review/edit");
		result.addObject("review", review);
		result.addObject("message", message);

		return result;
	}

}
