
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ShippingInfo;

@Repository
public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Integer> {

}
