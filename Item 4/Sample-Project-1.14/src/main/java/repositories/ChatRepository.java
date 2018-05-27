
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

	//select c from Chat c where  (receiver.id=878 and sender.id=876)or(receiver.id=876 and sender.id=878)
	@Query("select c from Chat c where  (receiver.id=?1 and sender.id=?2)or(receiver.id=?2 and sender.id=?1) order by date desc")
	Collection<Chat> findByActorsId(int user1Id, int user2Id);

	//select c from Chat c where c.sender=876 or c.receiver=876 group by sender
	@Query("select c from Chat c where c.sender=?1 or c.receiver=?1 group by sender")
	Collection<Chat> findByPrincipalSender(int id);
	//select c from Chat c where c.sender=876 or c.receiver=876 group by receiver
	@Query("select c from Chat c where c.sender=?1 or c.receiver=?1 group by receiver")
	Collection<Chat> findByPrincipalReceiver(int id);
}
