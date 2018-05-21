
package controllers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdvertisementService;
import services.BusinessService;
import services.ValorationService;
import services.ItemService;
import services.ShopAdvertisementService;
import services.UserService;
import domain.Actor;
import domain.Business;
import domain.Valoration;
import domain.Item;
import domain.User;

@Controller
@RequestMapping("/valoration")
public class ValorationController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService					userService;
	@Autowired
	private ActorService				actorService;

	@Autowired
	private BusinessService				businessService;

	@Autowired
	private ValorationService	valorationService;



	// Constructors -----------------------------------------------------------

	public ValorationController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/valorate", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer actorId,@RequestParam Integer rating) {
		ModelAndView result;
		Valoration valoration;
		Actor actor =this.actorService.findByPrincipal();
		valoration = this.valorationService.create();
		valoration.setScore(rating);
		valoration.setActor(actor);
		
		valoration=this.valorationService.save(valoration);
		Actor valorated = this.actorService.findOne(actorId);
		Collection<Valoration> valorations=valorated.getValorations();
		valorations.add(valoration);
		valorated.setValorations(valorations);
		this.actorService.valorate(valorated.getId());
	
		result =  list();

		return result;
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<User> usuarios=new HashSet<User>();
		Collection<Business> business=new HashSet<Business>();
		Actor a=actorService.findByPrincipal();
		if(a instanceof Business){
			Business businessActor= (Business) a;
			usuarios = this.userService.findUsersISoldThingsAndIAmABussiness(businessActor.getId());

		}
		if(a instanceof User){
			User u= (User) a;
			usuarios = this.userService.findUsersISoldThingsToThey(u.getId());
			usuarios.addAll(this.userService.findUsersTheySoldThingsToMy(u.getId()));
			business=this.businessService.findBusinessIbuyThings(u.getId());

		}
//los meto en set para evitar repetidos
		Set<User> usuariosNoRepetidos= new HashSet<User>();
		Set<Business> businessNoRepetidos = new HashSet<Business>();
		usuariosNoRepetidos.addAll(usuarios);
		businessNoRepetidos.addAll(business);
		
		
		
		//complejo sistema para que no puedas valorar 2 veces al mismo
		//aunque parezca que es poco optimo, solo hace for sobre cada columna  de la lista y compara si ya valoro a dicho actor para no mostrarlo
		Collection<Valoration> valorationsYaValoradas=valorationService.findByActor(a.getId());
		for(User u: usuariosNoRepetidos){
			for(Valoration v:valorationsYaValoradas){
				if(v.getActor().equals(a)){
					usuariosNoRepetidos.remove(u);
					break;
				}
			}
					
		}
			for(Business b: businessNoRepetidos){
				for(Valoration v:valorationsYaValoradas){
					if(v.getActor().equals(a)){
					
						businessNoRepetidos.remove(b);
						
						break;
					}
				}
						
			}
		//fin del complejo sistema
		
		
		result = new ModelAndView("valoration/list");
		result.addObject("usuariosNoRepetidos", usuariosNoRepetidos);
		result.addObject("businessNoRepetidos", businessNoRepetidos);

		return result;
	}







}
