
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.FacturationData;

@Repository
public interface FacturationDataRepository extends JpaRepository<FacturationData, Integer> {

}
