
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ShopAdvertisement;

@Repository
public interface ShopAdvertisementRepository extends JpaRepository<ShopAdvertisement, Integer> {

	@Query("select a from ShopAdvertisement a where a.endDate>CURRENT_timestamp")
	Collection<ShopAdvertisement> findNotPast();

	@Query("select a from ShopAdvertisement a where a.business.id=?1 ")
	Collection<ShopAdvertisement> findShopbyBusiness(int id);

	@Query("select avg(s.stock) from ShopAdvertisement s")
	Double avgStockShop();

	@Query("select sqrt(sum(s.stock*s.stock) / count(s.stock) - (avg(s.stock) * avg(s.stock))) from ShopAdvertisement s")
	Double stdStockShop();

}
