
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ExpressAdvertisement;

@Repository
public interface ExpressAdvertisementRepository extends JpaRepository<ExpressAdvertisement, Integer> {

}
