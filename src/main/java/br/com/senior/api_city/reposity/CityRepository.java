package br.com.senior.api_city.reposity;

import br.com.senior.api_city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;
=======
>>>>>>> 0e3b600b284db5129031285b42a686205348d129

/**
 *
 * @author eduardo-pinheiro
 *
 */
<<<<<<< HEAD

@NamedQueries({
        @NamedQuery(name = "CityRepository.findByCapital",
                query = "SELECT c FROM City  c WHERE c.capital = :isCapital ORDER BY c.name"),
        @NamedQuery(name = "ICityRepository.countByUf",
                query = "SELECT COUNT(c), c.uf FROM City c WHERE c.uf = : uf GROUP BY c.uf")
})

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    City findByIbgeId(Integer ibgeId);

    List<City> findByCapital(@Param("isCapital") Boolean capital);

    List<City> findByUf(String uf);

    Long countByUf(@Param("uf") String uf);
=======
public interface CityRepository extends JpaRepository<City, Integer> {

>>>>>>> 0e3b600b284db5129031285b42a686205348d129
}
