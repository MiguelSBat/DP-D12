
package repositories;

import java.util.Collection;

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
}
