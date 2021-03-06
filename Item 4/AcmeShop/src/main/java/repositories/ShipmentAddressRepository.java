
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

	//select s from ShipmentAddress s where s.ticket.id=923
	@Query("select s from ShipmentAddress s where s.ticket.id=?1")
	ShipmentAddress findByTicketId(int id);

	@Query("select s from ShipmentAddress s where s.user.id = ?1 and s.id = (select max(a.id) from ShipmentAddress a where a.user.id = ?1)")
	ShipmentAddress findLatest(int userId);
}
