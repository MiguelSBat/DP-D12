
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SaleLine;

@Repository
public interface SaleLineRepository extends JpaRepository<SaleLine, Integer> {

	//select s from SaleLine s where s.shoppingCart.id=
	@Query("select s from SaleLine s where s.shoppingCart.id=?1")
	Collection<SaleLine> findFromBusinessByShoppingCartId(int id);

	//select s from Advertisement a join a.saleLines s where s.shoppingCart.id=779 AND a.business.id=740
	@Query("select s from Advertisement a join a.saleLines s where s.shoppingCart.id=?1 AND a.business.id=?2")
	Collection<SaleLine> findByShoppingCartAndBusinessId(int shoppingCartId, int businessId);

	//select s from AuctionAdvertisement a join a.saleLines s where s.shoppingCart.id=779 AND a.user.id=737
	@Query("select s from AuctionAdvertisement a join a.saleLines s where s.shoppingCart.id=?1 AND a.user.id=?2")
	Collection<SaleLine> findFromAuctionByShoppingCartAndUserId(int shoppingCartId, int businessId);

	//select s from ExpressAdvertisement a join a.saleLines s where s.shoppingCart.id=779 AND a.user.id=737
	@Query("select s from ExpressAdvertisement a join a.saleLines s where s.shoppingCart.id=?1 AND a.user.id=?2")
	Collection<SaleLine> findFromExpressByShoppingCartAndUserId(int shoppingCartId, int businessId);

	//select s from SaleLine s where s.ticket.id=782
	@Query("select s from SaleLine s where s.ticket.id=?1")
	Collection<SaleLine> findByTicketId(int id);
}
