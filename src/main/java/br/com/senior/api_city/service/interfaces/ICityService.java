package br.com.senior.api_city.service.interfaces;

import br.com.senior.api_city.model.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICityService {

    List<City> getCapitalCitiesOrderByName();

//    Integer getNumberCitiesPerState(String uf);
//
//    City findByIbgeId(Integer ibgeId);
//
//    List<City> getCitiesPerState(String uf);
//
//    City saveCity(City city);
//
//    boolean removeCity(Integer ibgeId);
//
//    List<City> filterCsvColumn(String column, String value);
//
//    List<City> findAllCities();
//
//    Integer getQtdeRecords();
//
//    void getDistance();
}
