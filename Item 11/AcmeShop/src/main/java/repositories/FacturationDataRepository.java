
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FacturationData;

@Repository
public interface FacturationDataRepository extends JpaRepository<FacturationData, Integer> {

	//select s from ShipmentAddress s where s.ticket.user.id=714 order by s.ticket.date DESC;
	@Query("select s from FacturationData s where s.ticket.user.id=?1 order by s.ticket.date DESC")
	Collection<FacturationData> findByUserId(int id);

	@Query("select s from FacturationData s where s.ticket.id=?1")
	FacturationData findByTicketId(int id);

	@Query("select f from FacturationData f where f.user.id = ?1 and f.id = (select max(a.id) from FacturationData a where a.user.id = ?1)")
	FacturationData findLatest(int userId);

}
