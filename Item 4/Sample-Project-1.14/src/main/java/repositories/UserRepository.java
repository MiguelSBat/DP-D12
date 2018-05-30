
package repositories;

import java.util.Collection;
import java.util.List;

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

	@Query("select t.user from Ticket t where t.seller.id=?1")
	Collection<User> findUsersISoldThingsToThey(int id);
	@Query("select t.seller from Ticket t where t.user.id=?1")
	Collection<User> findUsersTheySoldThingsToMy(int id);
	@Query("select t.user from Ticket t where t.business.id=?1")
	Collection<User> findUsersISoldThingsAndIAmABussiness(int id);
	//select u from User u join u.socialIdentities s where s.id=
	@Query("select u from User u join u.socialIdentities s where s.id=?1")
	User findBySocialIdentity(int id);

	@Query("select distinct t.user from SaleLine s join s.ticket t where s.advertisement.id=?1")
	Collection<User> findByAdvertisementsBuyed(int ID);

	@Query("select t.seller from Ticket t group by t.seller order by count(t.seller) DESC")
	List<User> topFiveSellers();

	@Query("select 100.0*count(u)/(select count(b) from Business b) from User u")
	Double ratioUserVsBusiness();

}
