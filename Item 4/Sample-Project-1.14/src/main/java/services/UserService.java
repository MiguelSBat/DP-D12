
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.UserRepository;
import domain.Actor;
import domain.FacturationData;
import domain.ShipmentAddress;
import domain.SocialIdentity;
import domain.User;
import forms.ActorForm;
import forms.PaymentForm;

@Service
@Transactional
public class UserService {

	//Managed Repository ----
	@Autowired
	private UserRepository			userRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private ShipmentAddressService	shipmentAddressService;

	@Autowired
	private FacturationDataService	facturationDataService;


	//Constructors
	public UserService() {
		super();
	}

	public User create() {
		User result;

		result = new User();

		return result;
	}

	public Collection<User> findAll() {
		Collection<User> result;

		result = this.userRepository.findAll();

		return result;
	}

	public void delete(final User user) {

		this.userRepository.delete(user);

	}

	public User save(final User user) {
		User result;

		result = this.userRepository.save(user);
		return result;
	}

	public User findOne(final int userId) {
		User result;

		result = this.userRepository.findOne(userId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.userRepository.flush();
	}
	public Collection<User> findFromAuctionByShoppingCartId(final int id) {
		Collection<User> result;

		result = this.findFromAuctionByShoppingCartId(id);

		return result;
	}
	public Collection<User> findFromExpressByShoppingCartId(final int id) {
		Collection<User> result;

		result = this.findFromExpressByShoppingCartId(id);

		return result;
	}
	public Collection<User> findByShoppingCartId(final int id) {
		Collection<User> result;
		result = this.findFromAuctionByShoppingCartId(id);
		result.addAll(this.findFromExpressByShoppingCartId(id));

		return result;
	}

	public Actor create(final ActorForm form) {
		final User result = new User();

		result.setName(form.getName());
		result.setPhone(form.getPhone());
		result.setPhotosURL(new HashSet<String>());
		result.setPremium(false);
		result.setReputation(3);
		result.setSocialIdentities(new HashSet<SocialIdentity>());
		result.setSurname(form.getSurname());
		result.setSuspicious(false);

		return result;
	}
	public Collection<User> findUsersISoldThingsToThey(final int userId) {
		Collection<User> result;

		result = this.userRepository.findUsersISoldThingsToThey(userId);

		return result;
	}
	public Collection<User> findUsersTheySoldThingsToMy(final int userId) {
		Collection<User> result;

		result = this.userRepository.findUsersTheySoldThingsToMy(userId);

		return result;
	}
	public Collection<User> findUsersISoldThingsAndIAmABussiness(final int businessId) {
		Collection<User> result;

		result = this.userRepository.findUsersISoldThingsAndIAmABussiness(businessId);

		return result;
	}

	public User findByPrincipal() {
		User result;

		result = this.findOne(this.actorService.findByPrincipal().getId());
		Assert.notNull(result);

		return result;
	}

	public User findBySocialIdentityId(final int id) {
		User result;

		result = this.userRepository.findBySocialIdentity(id);

		return result;
	}

	public PaymentForm getPaymentForm() {
		final User principal = this.findByPrincipal();
		final ShipmentAddress address = this.shipmentAddressService.findLatest(principal.getId());
		final FacturationData facturation = this.facturationDataService.findLatest(principal.getId());
		final PaymentForm form = new PaymentForm();

		if (address != null) {
			form.setShipmentAdress(address.getAddress());
			form.setShipmentCity(address.getCity());
			form.setShipmentCountry(address.getCountry());
			form.setShipmentPostalCode(address.getPostalCode());
		}

		if (facturation != null) {
			form.setCity(facturation.getCity());
			form.setCountry(facturation.getCountry());
			form.setIDNumber(facturation.getIDNumber());
			form.setName(facturation.getName());
			form.setSurname(facturation.getSurname());
			form.setPostalCode(facturation.getPostalCode());
		} else {
			form.setName(principal.getName());
			form.setSurname(principal.getSurname());
		}

		return form;
	}

	public Collection<User> findByAdvertisementsBuyed(final int ID) {
		Collection<User> result;
		result = this.userRepository.findByAdvertisementsBuyed(ID);
		return result;
	}

	public Collection<User> topFiveUser() {
		final Collection<User> res = new ArrayList<>();
		List<User> aux;
		aux = this.userRepository.topFiveSellers();
		if (aux.size() > 0)
			for (int i = 0; i < aux.size(); i++) {
				if (i == 5)
					break;
				res.add(aux.get(i));

			}

		return res;
	}

	public Double ratioUserVsBusiness() {
		Double result;
		result = this.userRepository.ratioUserVsBusiness();
		return result;
	}
}
