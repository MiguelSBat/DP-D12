
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SocialIdentityRepository;
import domain.SocialIdentity;

@Service
@Transactional
public class SocialIdentityService {

	//Managed Repository ----
	@Autowired
	private SocialIdentityRepository	socialIdentityRepository;


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

		this.socialIdentityRepository.delete(socialIdentity);

	}

	public SocialIdentity save(final SocialIdentity socialIdentity) {
		SocialIdentity result;

		result = this.socialIdentityRepository.save(socialIdentity);
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
