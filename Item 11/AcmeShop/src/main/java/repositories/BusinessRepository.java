
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {

	@Query("select a.business from Advertisement a join a.saleLines s where s.id=?1")
	Collection<Business> findBySaleLineId(int id);

	@Query("select a.business from Advertisement a join a.saleLines s where s.shoppingCart.id=?1")
	Collection<Business> findByShoppingCartId(int id);

	@Query("select b from Business b where b.verified=0")
	Collection<Business> findNotVerified();

	@Query("select t.business from Ticket t where t.user.id=?1")
	Collection<Business> findBusinessIbuyThings(int id);

	@Query("select t.business from Ticket t group by t.business order by count(t.business) DESC")
	List<Business> topFiveBusiness();
}
