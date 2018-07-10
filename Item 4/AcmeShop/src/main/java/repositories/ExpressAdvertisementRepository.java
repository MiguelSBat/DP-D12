
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ExpressAdvertisement;

@Repository
public interface ExpressAdvertisementRepository extends JpaRepository<ExpressAdvertisement, Integer> {

	@Query("select a from ExpressAdvertisement a where a.endDate>CURRENT_timestamp")
	Collection<ExpressAdvertisement> findNotPast();

	@Query("select a from ExpressAdvertisement a where a.user.id=?1 ")
	Collection<ExpressAdvertisement> findExpressbyUser(int id);

	@Query("select a from ExpressAdvertisement a where a.business.id=?1 ")
	Collection<ExpressAdvertisement> findExpressbyBusiness(int id);

	@Query("select avg(s.price) from ExpressAdvertisement s")
	Double avgPriceExp();

	@Query("select sqrt(sum(s.price*s.price) / count(s.price) - (avg(s.price) * avg(s.price))) from ExpressAdvertisement s")
	Double stdPriceExp();
}
