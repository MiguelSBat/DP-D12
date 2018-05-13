
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ShopAdvertisement;

@Repository
public interface ShopAdvertisementRepository extends JpaRepository<ShopAdvertisement, Integer> {

}
