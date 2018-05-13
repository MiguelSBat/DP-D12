
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ShipmentAddress;

@Repository
public interface ShipmentAddressRepository extends JpaRepository<ShipmentAddress, Integer> {

}
