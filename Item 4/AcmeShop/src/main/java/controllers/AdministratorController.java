/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.BusinessService;
import services.ExpressAdvertisementService;
import services.QuestionService;
import services.ReportService;
import services.ShopAdvertisementService;
import services.UserService;
import services.ValorationService;
import domain.Business;
import domain.User;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	//Services

	@Autowired
	private ShopAdvertisementService	shopAdvertisementService;
	@Autowired
	private ExpressAdvertisementService	expressAdvertisementService;
	@Autowired
	private UserService					userService;
	@Autowired
	private BusinessService				businessService;
	@Autowired
	private ReportService				reportService;
	@Autowired
	private ValorationService			valorationService;
	@Autowired
	private QuestionService				questionService;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-1");

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-2");

		return result;
	}

	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView result;

		Double avgStockShop = 0.0;
		Double stdStockShop = 0.0;

		Double avgPriceExp = 0.0;
		Double stdPriceExp = 0.0;

		Double avgValoUser = 0.0;
		Double stdValoUser = 0.0;

		Double avgValoBusiness = 0.0;
		Double stdValoBusiness = 0.0;

		Double avgQuestionsShop = 0.0;
		Double stdQuestionsShop = 0.0;

		Double avgReportBusiness = 0.0;
		final Double stdReportBusiness = 0.0;

		Double avgReportUsers = 0.0;
		final Double stdReportUsers = 0.0;

		Collection<Business> topFiveBusiness = new ArrayList<Business>();
		Collection<User> topFiveSellers = new ArrayList<User>();

		Double ratioUservsBusiness = 0.0;

		if (this.userService.findAll().size() > 0 && this.businessService.findAll().size() > 0) {

			ratioUservsBusiness = this.userService.ratioUserVsBusiness();

			topFiveBusiness = this.businessService.topFiveBusiness();
			topFiveSellers = this.userService.topFiveUser();

			avgValoUser = this.valorationService.getAverageValorationByUser();
			stdValoUser = this.valorationService.getstdValorationByUser();
			avgValoBusiness = this.valorationService.getAverageValorationByBusiness();
			stdValoBusiness = this.valorationService.getstdValorationByBusiness();

			if (this.shopAdvertisementService.findAll().size() > 0)
				avgStockShop = this.shopAdvertisementService.avgStockShop();
			stdStockShop = this.shopAdvertisementService.stdStockshop();

			if (this.expressAdvertisementService.findAll().size() > 0)
				avgPriceExp = this.expressAdvertisementService.avgPriceExp();
			stdPriceExp = this.expressAdvertisementService.stdPriceExp();

			if (this.questionService.findAll().size() > 0)
				avgQuestionsShop = this.questionService.avgQuestionsPerShopAdvertisement();
			stdQuestionsShop = this.questionService.stdQuestionsPerShopAdvertisement();

			avgReportBusiness = this.reportService.avgReportsPerBusiness();
			//stdReportBusiness = this.reportService.stdReportsPerBusiness();
			avgReportUsers = this.reportService.avgReportsPerUser();
			//stdReportUsers = this.reportService.stdReportsPerUser();
		}

		result = new ModelAndView("administrator/dashboard");

		result.addObject("avgStockShop", avgStockShop);
		result.addObject("avgPriceExp", avgPriceExp);
		result.addObject("stdPriceExp", stdPriceExp);

		result.addObject("avgValoUser", avgValoUser);
		result.addObject("stdValoUser", stdValoUser);

		result.addObject("avgValoBusiness", avgValoBusiness);
		result.addObject("stdValoBusiness", stdValoBusiness);

		result.addObject("avgQuestionsShop", avgQuestionsShop);
		result.addObject("stdQuestionsShop", stdQuestionsShop);

		result.addObject("avgReportBusiness", avgReportBusiness);
		//result.addObject("stdReportBusiness", stdReportBusiness);

		result.addObject("avgReportUsers", avgReportUsers);
		//result.addObject("stdReportUsers", stdReportUsers);

		result.addObject("topFiveBusiness", topFiveBusiness);
		result.addObject("topFiveSellers", topFiveSellers);

		result.addObject("ratioUservsBusiness", ratioUservsBusiness);

		result.addObject("stdStockShop", stdStockShop);

		result.addObject("requestURI", "administrator/dashboard.do");

		return result;
	}

}
