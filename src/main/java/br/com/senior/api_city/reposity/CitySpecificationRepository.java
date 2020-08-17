package br.com.senior.api_city.reposity;

import br.com.senior.api_city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author eduardo-pinheiro
 *
 */

public interface CitySpecificationRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
}
