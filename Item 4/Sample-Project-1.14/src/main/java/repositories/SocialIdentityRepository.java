
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialIdentity;

@Repository
public interface SocialIdentityRepository extends JpaRepository<SocialIdentity, Integer> {

	//select s from User u join u.socialIdentities s where u.id=
	@Query("select s from User u join u.socialIdentities s where u.id=?1")
	Collection<SocialIdentity> findByUserId(int id);
}
