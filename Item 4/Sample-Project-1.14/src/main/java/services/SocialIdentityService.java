
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SocialIdentityRepository;
import domain.Actor;
import domain.SocialIdentity;
import domain.User;

@Service
@Transactional
public class SocialIdentityService {

	//Managed Repository ----
	@Autowired
	private SocialIdentityRepository	socialIdentityRepository;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private UserService					userService;


	//Constructors
	public SocialIdentityService() {
		super();
	}

	public SocialIdentity create() {
		SocialIdentity result;

		result = new SocialIdentity();

		return result;
	}

	public Collection<SocialIdentity> findAll() {
		Collection<SocialIdentity> result;

		result = this.socialIdentityRepository.findAll();

		return result;
	}

	public void delete(final SocialIdentity socialIdentity) {
		Actor principal;
		User user;
		user = this.userService.findBySocialIdentityId(socialIdentity.getId());
		principal = this.actorService.findByPrincipal();
		Assert.isTrue(user.getId() == principal.getId());

		user.removeSocialIdentity(socialIdentity);
		this.userService.save(user);
		this.socialIdentityRepository.delete(socialIdentity);

	}

	public SocialIdentity save(final SocialIdentity socialIdentity) {
		SocialIdentity result;
		Actor principal;
		User user;
		user = this.userService.findBySocialIdentityId(socialIdentity.getId());
		principal = this.actorService.findByPrincipal();
		Assert.isTrue(socialIdentity.getId() == 0 || (user.getId() == principal.getId()));
		result = this.socialIdentityRepository.save(socialIdentity);
		if (socialIdentity.getId() == 0) {
			user = (User) principal;
			user.addSocialIdentity(result);
			this.userService.save(user);
		}

		return result;
	}

	public SocialIdentity findOne(final int socialIdentityId) {
		SocialIdentity result;

		result = this.socialIdentityRepository.findOne(socialIdentityId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.socialIdentityRepository.flush();
	}

	public Collection<SocialIdentity> findByUserId(final int id) {
		Collection<SocialIdentity> result;

		result = this.socialIdentityRepository.findByUserId(id);

		return result;
	}

}
