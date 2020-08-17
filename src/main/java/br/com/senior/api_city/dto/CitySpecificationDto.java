package br.com.senior.api_city.dto;

import br.com.senior.api_city.model.City;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author eduardo-pinheiro
 *
 */
public class CitySpecificationDto implements Specification <City> {

    private String randomColumnName; // Column name
    private String valueToSearchFor; // Value of column

    public CitySpecificationDto() {

    }
    public CitySpecificationDto(String randomColumnName, String valueToSearchFor) {
        super();
        this.randomColumnName = randomColumnName;
        this.valueToSearchFor = valueToSearchFor;
    }

    public String getRandomColumnName() {
        return randomColumnName;
    }

    public void setRandomColumnName(String randomColumnName) {
        this.randomColumnName = randomColumnName;
    }

    public String getValueToSearchFor() {
        return valueToSearchFor;
    }

    public void setValueToSearchFor(String valueToSearchFor) {
        this.valueToSearchFor = valueToSearchFor;
    }

    @Override
    public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.and(cb.equal(root.<String>get(this.randomColumnName), this.valueToSearchFor));
    }
}
