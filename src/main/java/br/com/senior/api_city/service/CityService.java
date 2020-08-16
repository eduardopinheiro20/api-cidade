package br.com.senior.api_city.service;


import java.util.ArrayList;
import java.util.List;

import br.com.senior.api_city.dto.CityDto;
import br.com.senior.api_city.model.City;
import br.com.senior.api_city.reposity.CityRepository;
import br.com.senior.api_city.service.interfaces.ICityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


/**
 *
 * @author eduardo-pinheiro
 *
 */
@Service
public class CityService implements ICityService {

    private List<City> listCityMaxDistance;

    private List<City> listCityMinDistance;

    private Double maxDistance;

    private Double minDistance;

//    @Autowired
//    private StateDTO stateMaxCities;
//
//    @Autowired
//    private StateDTO stateMinCities;
//
//    @Autowired
//    private StateDTO stateDto;
//
//    @Autowired
//    private CityDTO cityDto;
//
    @Autowired
    private CityRepository cityRepository;
//
//    @Autowired
//    private ICitySpecificationRepository citySpecificationRepository;

    @Autowired
    private City city1;

    @Autowired
    private City city2;

    private static final Logger log = LoggerFactory.getLogger(CityService.class);

    public CityService() {
        setCity1(new City());
        setCity2(new City());
        this.listCityMaxDistance = new ArrayList<>();
        this.listCityMinDistance = new ArrayList<>();
    }

    public List<City> getCapitalCitiesOrderByName() {
        return this.cityRepository.findByCapital(Boolean.TRUE);
    }

    public Integer getNumberCitiesPerState(String uf) {
        return this.cityRepository.findByUf(uf).size();
    }

//
//    @Override
//    public City findByIbgeId(Integer ibgeId) {
//        return this.cityRepository.findByIbgeId(ibgeId);
//    }
//
//    @Override
//    public List<City> getCitiesPerState(String uf) {
//        return this.cityRepository.findByUf(uf);
//    }
//
//    @Override
//    public City saveCity(City city) {
//        this.cityRepository.save(city);
//        return city;
//    }
//
//    @Override
//    public boolean removeCity(Integer ibgeId) {
//        boolean deleted = false;
//        City city = new City();
//        try {
//            city = cityRepository.findByIbgeId(ibgeId);
//            this.cityRepository.delete(city);
//            deleted = true;
//        }
//        catch(Exception ex) {
//            ex.printStackTrace();
//        }
//        return deleted;
//    }
//
//    @Override
//    public List<City> filterCsvColumn(String column, String value) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public List<City> findAllCities() {
//        return this.cityRepository.findAll();
//    }
//
//    @Override
//    public Integer getQtdeRecords() {
//        return this.cityRepository.findAll().size();
//    }
//
//    @Override
//    public void getDistance() {
//        City city1 = new City();
//        City city2 = new City();
//
//        List<City> cities = this.cityRepository.findAll();
//
//        city1 = cities.get(0);
//
//        for(int i = 0; i < cities.size(); i++) {
//            city1 = cities.get(i);
//            for(int j = (i+1); j < cities.size(); j++) {
//                city2 = cities.get(j);
//                GeoCoordinateDTO cityOne = new GeoCoordinateDTO(city1.getLat(),
//                        city1.getLon());
//                GeoCoordinateDTO cityTwo = new GeoCoordinateDTO(city2.getLat(), city2.getLon());
//                double distance = cityOne.distanceInKm(cityTwo);
//                //Set max distance
//                if(this.maxDistance == null || this.maxDistance == 0) {
//                    this.maxDistance = distance;
//                    if(this.listCityMaxDistance.isEmpty()) {
//                        this.listCityMaxDistance.add(city1);
//                        this.listCityMaxDistance.add(city2);
//                    }
//                }
//                else {
//                    if(distance > this.maxDistance) {
//                        this.maxDistance = distance;
//                        this.listCityMaxDistance.clear();
//                        this.listCityMaxDistance.add(city1);
//                        this.listCityMaxDistance.add(city2);
//                    }
//                }
//                //Set min distance
//                if(this.minDistance == null || this.minDistance == 0) {
//                    this.minDistance = distance;
//                    if(this.listCityMinDistance.isEmpty()) {
//                        this.listCityMinDistance.add(city1);
//                        this.listCityMinDistance.add(city2);
//                    }
//                }
//                else {
//                    if(distance < this.minDistance) {
//                        this.minDistance = distance;
//                        this.listCityMinDistance.clear();
//                        this.listCityMinDistance.add(city1);
//                        this.listCityMinDistance.add(city2);
//                    }
//                }
//            }
//
//        }
//    }
//
//    /**
//     *
//     * @param column
//     * @param value
//     * @return List<City>
//     */
//    public Integer getCitiesByColumnValue(String column, String value){
//        if(column.equals("capital") && (value.equals("TRUE") || value.equals("true"))) {
//            return this.cityRepository.findByCapital(Boolean.TRUE).size();
//        }
//        else if(column.equals("capital") && (value.equals("FALSE") || value.equals("false"))) {
//            return this.cityRepository.findByCapital(Boolean.FALSE).size();
//        }
//        else {
//            CitySpecification citySpecification = new CitySpecification(column, value);
//            return this.citySpecificationRepository.findAll(citySpecification, new Sort(column)).size();
//        }
//    }
//
//
//    /**
//     * @return the maxDistance
//     */
//    public Double getMaxDistance() {
//        return maxDistance;
//    }
//
//    /**
//     * @return the minDistance
//     */
//    public Double getMinDistance() {
//        return minDistance;
//    }
//
//    public CityRepository getCityRepository() {
//        return cityRepository;
//    }
//
//    public void setCityRepository(CityRepository cityRepository) {
//        this.cityRepository = cityRepository;
//    }
//
    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }
//
//    public List<City> getListCityMaxDistance() {
//        return listCityMaxDistance;
//    }
//
//
//    public void setListCityMaxDistance(List<City> listCityMaxDistance) {
//        this.listCityMaxDistance = listCityMaxDistance;
//    }
//
//    public List<City> getListCityMinDistance() {
//        return listCityMinDistance;
//    }
//
//
//    public void setListCityMinDistance(List<City> listCityMinDistance) {
//        this.listCityMinDistance = listCityMinDistance;
//    }

    /**
     * Convert City entity into CityDTO
     * @param city
     * @return cityDTO
     */
    public CityDto convertCity(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setIbge_id(city.getIbgeId());
        cityDto.setIbge_id(city.getIbgeId());
        cityDto.setName(city.getName());
        cityDto.setUf(city.getUf());
        cityDto.setCapital(city.getCapital());
        cityDto.setLat(city.getLat());
        cityDto.setLon(city.getLon());
        cityDto.setAlternative_names(city.getAlternative_names());
        cityDto.setNo_accents(city.getNo_accents());
        cityDto.setMesoregion(city.getMesoregion());
        cityDto.setMicroregion(city.getMicroregion());

        return cityDto;
    }

    /**
     * Convert CityDTO into City Entity
     * @param cityDto
     * @return city
     */
//    public City convertCityDto(CityDTO cityDto) {
//        City city = new City();
//        city.setIbge_id(cityDto.getIbge_id());
//        city.setName(cityDto.getName());
//        city.setUf(cityDto.getUf());
//        city.setCapital(cityDto.getCapital());
//        city.setLat(cityDto.getLat());
//        city.setLon(cityDto.getLon());
//        city.setAlternative_names(cityDto.getAlternative_names());
//        city.setNo_accents(cityDto.getNo_accents());
//        city.setMesoregion(cityDto.getMesoregion());
//        city.setMicroregion(cityDto.getMicroregion());
//
//        return city;
//    }


//    public City convertCityIO(CityIO cityIo) {
//        City city = new City();
//        city.setIbge_id(Integer.parseInt(cityIo.getIbge_id()));
//        city.setName(cityIo.getName());
//        city.setUf(cityDto.getUf());
//        city.setCapital(cityDto.getCapital());
//        city.setLat(cityDto.getLat());
//        city.setLon(cityDto.getLon());
//        city.setAlternative_names(cityDto.getAlternative_names());
//        city.setNo_accents(cityDto.getNo_accents());
//        city.setMesoregion(cityDto.getMesoregion());
//        city.setMicroregion(cityDto.getMicroregion());
//        return city;
//    }
//
//    public static Logger getLog() {
//        return log;
//    }

}
