
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

	@Query("select r from Review r where r.shopAdvertisement.id=?1")
	Collection<Review> findByShopAdvertisement(int ID);

	@Query("select avg(r.score) from Review r where r.shopAdvertisement.id=?1")
	Double avgScoreOfShopAd(int ID);

}
