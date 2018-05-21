
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findByUserAccount(int id);

	@Query("select a from Actor a where a.suspicious=1")
	Collection<Actor> findSuspicious();

	@Query("select r.actor from Report r group by r.actor having sum(r.weight)>?1")
	Collection<Actor> findByReportWeight(Long weight);
}
