
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.AuctionAdvertisement;

@Repository
public interface AuctionAdvertisementRepository extends JpaRepository<AuctionAdvertisement, Integer> {

}
