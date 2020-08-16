package br.com.senior.api_city.controller;

import br.com.senior.api_city.dto.CityDto;
import br.com.senior.api_city.model.City;
import br.com.senior.api_city.service.CityService;
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
        List<City> cities =  this.cityService.getCapitalCitiesOrderByName();

        for(City city : cities) {
            CityDto cityDto = new CityDto();
            cityDto = cityService.convertCity(city);
            citiesDto.add(cityDto);
        }
        Collections.sort (citiesDto, new Comparator<CityDto>() {
            public int compare (CityDto c1, CityDto c2) {
                return c1.getName().toUpperCase().compareTo (c2.getName().toUpperCase());
            }
        });
        return citiesDto;
    }

    //Retorna a Qtde de cidades por estado
    @GetMapping(value = "/getQtdeByUf/{uf}")
    @ResponseBody
    public Integer getQtdeByUf(@PathVariable("uf") String uf) {
        return this.cityService.getNumberCitiesPerState(uf);
    }
}
