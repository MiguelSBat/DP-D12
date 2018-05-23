
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {

	@Query("select b from Bid b where b.auctionAdvertisement.id=?1 order by b.amount DESC")
	Collection<Bid> findOrderedByAuction(int auctionAdvertisementId);

	@Query("select b from Bid b where b.auctionAdvertisement.id=?2 and b.user.id=?1")
	Collection<Bid> findByAuctionAndUser(int userId, int auctionId);

}
