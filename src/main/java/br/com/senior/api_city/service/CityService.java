package br.com.senior.api_city.service;

import java.util.ArrayList;
import java.util.List;

import br.com.senior.api_city.dto.CityDto;
import br.com.senior.api_city.dto.CitySpecificationDto;
import br.com.senior.api_city.dto.CoordinateDto;
import br.com.senior.api_city.dto.StateDto;
import br.com.senior.api_city.model.City;
import br.com.senior.api_city.reposity.CityRepository;
import br.com.senior.api_city.reposity.CitySpecificationRepository;
import br.com.senior.api_city.service.interfaces.ICityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StateDto stateMaxCities;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CitySpecificationRepository citySpecificationRepository;

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

    public StateDto getMaxUfState() {
        this.stateMaxCities = null;
        List<City> cities = this.cityRepository.findByCapital(Boolean.TRUE);
        List<StateDto> states = new ArrayList<>();

        for (City city : cities) {
            StateDto stateDto = new StateDto();
            stateDto.setUf(city.getUf());
            states.add(stateDto);
        }
        if (!states.isEmpty()) {
            for (StateDto state : states) {
                state.setNumberOfCities(this.cityRepository.countByUf(state.getUf()));
                if (this.stateMaxCities == null || this.stateMaxCities.getNumberOfCities() == null) {
                    this.stateMaxCities = state;
                    System.out.println(this.stateMaxCities.toString());
                } else {
                    if (this.stateMaxCities.getNumberOfCities() < state.getNumberOfCities()) {
                        this.stateMaxCities = state;
                        System.out.println(this.stateMaxCities.toString());
                    }
                }
            }
        }
        return this.stateMaxCities;
    }

    public City findByIbgeId(Integer ibgeId) {
        return this.cityRepository.findByIbgeId(ibgeId);
    }

    public List<City> getCitiesForState(String uf) {
        return this.cityRepository.findByUf(uf);
    }

    public City saveCity(City city) {
        this.cityRepository.save(city);
        return city;
    }

    public boolean removeCity(Integer ibgeId) {
        boolean deleted = false;
        City city = new City();
        try {
            city = cityRepository.findByIbgeId(ibgeId);
            this.cityRepository.delete(city);
            deleted = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return deleted;
    }


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

    public Integer getQtdeRecords() {
        return this.cityRepository.findAll().size();
    }

    public void getDistance() {
        City city1 = new City();
        City city2 = new City();

        List<City> cities = this.cityRepository.findAll();

        city1 = cities.get(0);

        for (int i = 0; i < cities.size(); i++) {
            city1 = cities.get(i);
            for (int j = (i + 1); j < cities.size(); j++) {
                city2 = cities.get(j);
                CoordinateDto cityOne = new CoordinateDto(city1.getLat(),
                        city1.getLon());
                CoordinateDto cityTwo = new CoordinateDto(city2.getLat(), city2.getLon());
                double distance = cityOne.distanceInKm(cityTwo);
                //Set max distance
                if (this.maxDistance == null || this.maxDistance == 0) {
                    this.maxDistance = distance;
                    if (this.listCityMaxDistance.isEmpty()) {
                        this.listCityMaxDistance.add(city1);
                        this.listCityMaxDistance.add(city2);
                    }
                } else {
                    if (distance > this.maxDistance) {
                        this.maxDistance = distance;
                        this.listCityMaxDistance.clear();
                        this.listCityMaxDistance.add(city1);
                        this.listCityMaxDistance.add(city2);
                    }
                }
                //Set min distance
                if (this.minDistance == null || this.minDistance == 0) {
                    this.minDistance = distance;
                    if (this.listCityMinDistance.isEmpty()) {
                        this.listCityMinDistance.add(city1);
                        this.listCityMinDistance.add(city2);
                    }
                } else {
                    if (distance < this.minDistance) {
                        this.minDistance = distance;
                        this.listCityMinDistance.clear();
                        this.listCityMinDistance.add(city1);
                        this.listCityMinDistance.add(city2);
                    }
                }
            }

        }
    }

    public Integer getCitiesByColumnValue(String column, String value){
        if(column.equals("capital") && (value.equals("TRUE") || value.equals("true"))) {
            return this.cityRepository.findByCapital(Boolean.TRUE).size();
        }
        else if(column.equals("capital") && (value.equals("FALSE") || value.equals("false"))) {
            return this.cityRepository.findByCapital(Boolean.FALSE).size();
        }
        else {
            CitySpecificationDto citySpecification = new CitySpecificationDto(column, value);
            return this.citySpecificationRepository.findAll().size();
        }
    }

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

    public List<City> getListCityMaxDistance() {
        return listCityMaxDistance;
    }

    public static Logger getLog() {
        return log;
    }

}
