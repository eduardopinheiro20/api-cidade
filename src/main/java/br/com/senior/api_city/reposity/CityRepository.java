package br.com.senior.api_city.reposity;

import br.com.senior.api_city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eduardo-pinheiro
 *
 */
public interface CityRepository extends JpaRepository<City, Integer> {

}
