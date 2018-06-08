
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BusinessInfoService;
import domain.BusinessInfo;
import domain.Folder;

@Controller
@RequestMapping("/businessInfo")
public class businessInfoController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private BusinessInfoService	businessInfoService;
			


	// Constructors -----------------------------------------------------------

	public businessInfoController() {
		super();
	}

	// Creation ---------------------------------------------------------------
	
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			BusinessInfo businessInfo;
	
			businessInfo = this.businessInfoService.create();
			result = this.createEditModelAndView(businessInfo);
	
			return result;
		}
		//edition
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam final int businessInfoId) {
			ModelAndView result;
			BusinessInfo bi;

			bi = this.businessInfoService.findOne(businessInfoId);
			Assert.notNull(bi);
			result = this.createEditModelAndView(bi);
			return result;
		}
		// Edition ----------------------------------------------------------------
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final BusinessInfo businessInfo, final BindingResult binding) {
			ModelAndView result;

			if (binding.hasErrors()){
				String message= "advertisement.commit.error";
				
				result = this.createEditModelAndView(businessInfo,message);
			}else
				try {
					this.businessInfoService.save(businessInfo);
					result = new ModelAndView("redirect:/");
				} catch (final Throwable oops) {
					String errorMessage = "advertisement.commit.error";

					if (oops.getMessage().contains("message.error"))
						errorMessage = oops.getMessage();
				
					result = this.createEditModelAndView(businessInfo, errorMessage);
				}

			return result;
		}

		// Ancillary methods ------------------------------------------------------

		protected ModelAndView createEditModelAndView(final  BusinessInfo businessInfo) {
			ModelAndView result;

			result = this.createEditModelAndView(businessInfo, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(final  BusinessInfo businessInfo, final String message) {
			ModelAndView result;
			
		
		
			result = new ModelAndView("businessInfo/edit");
			result.addObject("businessInfo", businessInfo);
			
		

			return result;
			
		}
}
