
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	//select t from Ticket t where t.business=740
	@Query("select t from Ticket t where t.business.id=?1")
	Collection<Ticket> findByBusinessId(int id);
	//select t from Ticket t where t.seller=738
	@Query("select t from Ticket t where t.seller.id=?1")
	Collection<Ticket> findBySellerId(int id);
	//select t from Ticket t where t.user=737
	@Query("select t from Ticket t where t.user.id=?1")
	Collection<Ticket> findByUserId(int id);

	@Query("select s.ticket from SaleLine s where s.advertisement.id=?1 and s.ticket.user.id=?2")
	Collection<Ticket> findByAdvertisementsAndUserBuyed(int ID, int IDU);

}
