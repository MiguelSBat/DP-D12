
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

}
