
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {

}
