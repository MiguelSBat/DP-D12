
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	//select a.user from AuctionAdvertisement a join a.saleLines s where s.shoppingCart.id=
	@Query("select a.user from AuctionAdvertisement a join a.saleLines s where s.shoppingCart.id=?1")
	Collection<User> findFromAuctionByShoppingCartId(int id);
	//select a.user from ExpressAdvertisement a join a.saleLines s where s.shoppingCart.id=
	@Query("select a.user from ExpressAdvertisement a join a.saleLines s where s.shoppingCart.id=?1")
	Collection<User> findFromExpressByShoppingCartId(int id);
}
