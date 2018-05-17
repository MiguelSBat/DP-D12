
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ShipmentAddress;

@Repository
public interface ShipmentAddressRepository extends JpaRepository<ShipmentAddress, Integer> {

	//select s from ShipmentAddress s where s.ticket.user.id=714 order by s.ticket.date DESC;
	@Query("select s from ShipmentAddress s where s.ticket.user.id=?1 order by s.ticket.date DESC")
	Collection<ShipmentAddress> findByUserId(int id);
}
