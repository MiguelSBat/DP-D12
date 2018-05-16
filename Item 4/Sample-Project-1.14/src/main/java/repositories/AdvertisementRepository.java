
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Advertisement;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

	@Query("select a from Advertisement a where a.item.name like CONCAT('%',?1,'%') and a.endDate>CURRENT_DATE")
	Collection<Advertisement> findByCriteria(String criteria);

	@Query("select a from Advertisement a where a.endDate>CURRENT_DATE")
	Collection<Advertisement> findNotPast();

	@Query("select a from Advertisement a join a.saleLines s where s.id=?1")
	Collection<Advertisement> findBySaleLineId(int id);

	@Query("select a from Advertisement a where a.business.id=?1")
	Collection<Advertisement> findByBusinessId(int id);
}
