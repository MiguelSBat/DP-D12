
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

}
