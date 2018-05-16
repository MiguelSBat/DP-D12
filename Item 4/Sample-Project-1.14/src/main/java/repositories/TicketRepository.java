
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	//select t from Ticket t where t.business=740
	@Query("select t from Ticket t where t.business=?1")
	Collection<Ticket> findByBusinessId(int id);
	//select t from Ticket t where t.seller=738
	@Query("select t from Ticket t where t.seller=?1")
	Collection<Ticket> findBySellerId(int id);
	//select t from Ticket t where t.user=737
	@Query("select t from Ticket t where t.user=?1")
	Collection<Ticket> findByUserId(int id);
}
