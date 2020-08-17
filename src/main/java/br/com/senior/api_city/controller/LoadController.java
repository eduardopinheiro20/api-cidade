package br.com.senior.api_city.controller;

import br.com.senior.api_city.dto.CityDto;
import br.com.senior.api_city.dto.StateDto;
import br.com.senior.api_city.model.City;
import br.com.senior.api_city.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/load")
@CrossOrigin(origins = "*")

public class LoadController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Autowired
    private CityService cityService;

    private static final Logger log = LoggerFactory.getLogger(LoadController.class);

    @GetMapping
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        System.out.println("JobExecution: " + jobExecution.getStatus());

        return jobExecution.getStatus();
    }

    //Retorna Somente cidades que são Capitais ordenadas por nome;
    @GetMapping(value = "/getCapitals")
    public List<CityDto> getCapitals() {
        List<CityDto> citiesDto = new ArrayList<>();
        List<City> cities = this.cityService.getCapitalCitiesOrderByName();

        for (City city : cities) {
            CityDto cityDto = new CityDto();
            cityDto = cityService.convertCity(city);
            citiesDto.add(cityDto);
        }
        Collections.sort(citiesDto, new Comparator<CityDto>() {
            public int compare(CityDto c1, CityDto c2) {
                return c1.getName().toUpperCase().compareTo(c2.getName().toUpperCase());
            }
        });
        return citiesDto;
    }

    /*
     *   Retorna a Qtde de cidades por estado
     */
    @GetMapping(value = "/getQtdeByUf/{uf}")
    public Integer getQtdeByUf(@PathVariable("uf") String uf) {
        return this.cityService.getNumberCitiesPerState(uf);
    }

    /*
     *   Retornar o nome das cidades baseado em um estado selecionado
     *
     * */
    @GetMapping(value = "/getByUf/{uf}")
    @ResponseBody
    public List<CityDto> getCitiesByUf(@PathVariable("uf") String uf) {
        List<CityDto> citiesDto = new ArrayList<CityDto>();
        log.info("Getting uf: {}", uf);
        List<City> cities = this.cityService.getCitiesForState(uf);
        for (City city : cities) {
            CityDto cityDto = new CityDto();
            cityDto = cityService.convertCity(city);
            citiesDto.add(cityDto);
        }
        return citiesDto;
    }

    /*
     *   Retorna no nome do Estado com maior numero de cidades
     */
    @GetMapping(value = "/getMaxCitiesForState")
    public StateDto getMaxCitiesPerState() {
        StateDto stateDto = new StateDto();
        stateDto = this.cityService.getMaxUfState();
        return stateDto;
    }

    /*
     *   Retornda dados da cidade através do IBGE escolhido
     */
    @GetMapping(value = "/getByIBGE/{ibge_id}")
    public CityDto getCityByIbgeId(@PathVariable("ibge_id") Integer ibgeId) {
        CityDto cityDto = new CityDto();
        City city = new City();
        city = this.cityService.findByIbgeId(ibgeId);
        cityDto = cityService.convertCity(city);
        return cityDto;
    }

    /*
     *  Retorna a Quantidade total de Registros
     */
    @GetMapping(value = "/getAll")
    public Integer getQtdeRecords() {
        return this.cityService.getQtdeRecords();
    }

    /*
     *  Permite remover Registros
     */
    @DeleteMapping(value = "/delete/{ibge_id}")
    public String deleteCityByIbgeId(@PathVariable("ibge_id") Integer ibgeId) {
        if (!this.cityService.removeCity(ibgeId)) {
            log.info("Ibge id : {} not found.", ibgeId);
            return "Código IBGE não encontrado!";
        } else {
            return "Cidade deletada com sucesso!";
        }
    }

    @GetMapping(value = "/getByColumnValue/column/{column}/value/{value}")
    @ResponseBody
    public Integer getQtdeRecordsByColumn(@PathVariable("column") String column, @PathVariable("value") String value) {
        return this.cityService.getCitiesByColumnValue(column, value);
    }

    /*
     *   Duas cidades com maior distancia em Km uma da outra
     */
    @GetMapping(value = "/getMaxDistance")
    @ResponseBody
    public List<City> getMaxDistance() {
        List<City> cities = new ArrayList<>();
        log.info("Getting max distance...");
        this.cityService.getDistance();
        cities = this.cityService.getListCityMaxDistance();
        return cities;
    }

}
