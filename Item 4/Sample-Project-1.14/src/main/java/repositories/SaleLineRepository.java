
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.SaleLine;

@Repository
public interface SaleLineRepository extends JpaRepository<SaleLine, Integer> {

}
