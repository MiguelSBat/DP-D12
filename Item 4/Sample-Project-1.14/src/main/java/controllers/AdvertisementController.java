
package controllers;

import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdvertisementService;
import services.AuctionAdvertisementService;
import services.BidService;
import services.ReviewService;
import services.ShopAdvertisementService;
import domain.Actor;
import domain.Advertisement;
import domain.AuctionAdvertisement;
import domain.Bid;
import domain.Business;
import domain.ExpressAdvertisement;
import domain.Question;
import domain.Review;
import domain.ShopAdvertisement;
import domain.User;

@Controller
@RequestMapping("/advertisement")
public class AdvertisementController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AdvertisementService		advertisementService;
	@Autowired
	private BidService					bidService;
	@Autowired
	private AuctionAdvertisementService	auctionAdvertisementService;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private ReviewService				reviewService;

	@Autowired
	private ShopAdvertisementService	shopAdvertisementService;


	// Constructors -----------------------------------------------------------

	public AdvertisementController() {
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
		if (criteria == null || criteria == "")
			advertisements = this.advertisementService.findNotPAst();

		result = new ModelAndView("advertisement/list");
		result.addObject("advertisements", advertisements);

		return result;
	}

	//	 display

	@RequestMapping(value = "display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int advertisementId, final boolean minimumBid) {
		ModelAndView result;
		Advertisement advertisement, premium;
		String advertisementType;
		Collection<Bid> bids;
		Collection<Advertisement> premiums, premiumUsers;
		Collection<Review> reviews;
		Collection<Question> questions;
		Double score;
		ShopAdvertisement shopAdvertisement;
		Boolean isPremiumUser = true;
		Boolean isPremiumBusiness = true;
		User user;
		Business business;
		final Actor actor;
		final Boolean Verdadero = true;
		boolean biddable;
		final Random rnd;
		//		advertisement = this.advertisementService.findOne(advertisementId);

		biddable = false;
		result = new ModelAndView("advertisement/display");
		advertisement = this.advertisementService.findOne(advertisementId);
		premiums = this.advertisementService.findByPremiumBusiness();
		premiumUsers = this.advertisementService.findByPremiumUser();
		premiums.addAll(premiumUsers);

		if (this.actorService.isLogged()) {
			actor = this.actorService.findByPrincipal();
			if (actor instanceof User) {
				user = (User) actor;
				isPremiumUser = user.isPremium();
				if (!isPremiumUser)
					result.addObject("isPremium", isPremiumUser);
			}
			if (actor instanceof Business) {
				business = (Business) actor;
				isPremiumBusiness = business.isPremium();
				if (!isPremiumBusiness)
					result.addObject("isPremium", isPremiumBusiness);
			}
		}
		if (advertisement instanceof AuctionAdvertisement) {
			advertisementType = "auction";
			bids = this.bidService.findOrderedByAuction(advertisement.getId());
			result.addObject("bids", bids);
			biddable = this.auctionAdvertisementService.isBiddable(advertisement);
			if (minimumBid == true)
				result.addObject("minimumBid", true);
		} else if (advertisement instanceof ExpressAdvertisement)
			advertisementType = "express";
		else {
			advertisementType = "shop";
			reviews = this.reviewService.findByShopAdvertisement(advertisement.getId());
			result.addObject("reviews", reviews);
			shopAdvertisement = this.shopAdvertisementService.findOne(advertisementId);
			questions = shopAdvertisement.getQuestions();
			result.addObject("questions", questions);
			score = this.reviewService.avgScoreOfShopAd(advertisementId);
			result.addObject("score", score);

		}
		if (advertisement.getBusiness() == null)
			result.addObject("user", Verdadero);
		else
			result.addObject("business", Verdadero);

		if (premiums.size() > 0) {
			rnd = new Random();
			premium = (Advertisement) premiums.toArray()[rnd.nextInt(premiums.size())];
			result.addObject("premium", premium);
		}

		result.addObject("advertisement", advertisement);
		result.addObject("type", advertisementType);
		result.addObject("biddable", biddable);
		return result;
	}
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
