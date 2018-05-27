
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SaleLineService;
import domain.SaleLine;

@Controller
@RequestMapping("/user/shoppingCart")
public class ShoppingCartUserController {

	@Autowired
	SaleLineService	saleLineService;


	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view() {
		ModelAndView result;
		Collection<SaleLine> saleLines;
		Double total;

		saleLines = this.saleLineService.findByPrincipal();
		total = this.saleLineService.getTotalAmount(saleLines);

		result = new ModelAndView("shoppingCart/view");
		result.addObject("lines", saleLines);
		result.addObject("total", total);
		return result;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView remove(@RequestParam final SaleLine line) {
		ModelAndView result;

		this.saleLineService.delete(line);

		result = new ModelAndView("redirect:/user/shoppingCart/view.do");
		return result;
	}

}
