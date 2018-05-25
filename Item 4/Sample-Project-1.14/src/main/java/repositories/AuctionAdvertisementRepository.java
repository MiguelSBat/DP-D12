
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.AuctionAdvertisement;

@Repository
public interface AuctionAdvertisementRepository extends JpaRepository<AuctionAdvertisement, Integer> {

	@Query("select a from AuctionAdvertisement a where a.user.id = ?1")
	Collection<AuctionAdvertisement> findByPrincipalUser(int userId);

	@Query("select a from AuctionAdvertisement a where a.business.id = ?1")
	Collection<AuctionAdvertisement> findByPrincipalBusiness(int userId);

	@Query("select a from AuctionAdvertisement a where a.endDate>CURRENT_timestamp")
	Collection<AuctionAdvertisement> findNotPast();

	@Query("select b.auctionAdvertisement from Bid b where b.user.id=?1 group by b.auctionAdvertisement")
	Collection<AuctionAdvertisement> findByUser(int userId);
}
