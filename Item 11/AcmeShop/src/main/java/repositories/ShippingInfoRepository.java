
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ShippingInfo;

@Repository
public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Integer> {

	@Query("select s from ShippingInfo s where s.ticket.id=?1")
	ShippingInfo findByTicketId(int id);
}
