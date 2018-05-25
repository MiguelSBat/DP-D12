
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
	Collection<Chat> findByUsersId(int user1Id, int user2Id);
}
