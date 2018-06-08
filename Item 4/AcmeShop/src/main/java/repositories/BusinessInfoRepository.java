
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.BusinessInfo;

@Repository
public interface BusinessInfoRepository extends JpaRepository<BusinessInfo, Integer> {

}
