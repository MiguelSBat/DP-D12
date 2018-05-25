
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.UserRepository;
import domain.Actor;
import domain.Review;
import domain.SocialIdentity;
import domain.User;
import forms.ActorForm;

@Service
@Transactional
public class UserService {

	//Managed Repository ----
	@Autowired
	private UserRepository	userRepository;


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
		result.setReviews(new HashSet<Review>());
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
	public User findBySocialIdentityId(final int id) {
		User result;

		result = this.userRepository.findBySocialIdentity(id);

		return result;
	}

}
