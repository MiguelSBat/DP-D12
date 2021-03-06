
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	@Query("select q from Question q where q.user.id=?1")
	Collection<Question> findByUser(int id);

	@Query("select q from Question q where q.shopAdvertisement.business.id=?1")
	Collection<Question> findByBusiness(int businessId);

	@Query("select avg(s.questions.size) from ShopAdvertisement s")
	Double avgQuestionsPerShopAdvertisement();

	@Query("select sqrt(sum(s.questions.size*s.questions.size) / count(s.questions.size) - (avg(s.questions.size) * avg(s.questions.size))) from ShopAdvertisement s")
	Double stdQuestionsPerShopAdvertisement();
}
