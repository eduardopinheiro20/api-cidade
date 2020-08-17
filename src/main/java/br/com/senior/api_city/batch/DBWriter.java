package br.com.senior.api_city.batch;

import br.com.senior.api_city.model.City;
import br.com.senior.api_city.reposity.CityRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author eduardo-pinheiro
 *
 */
@Component
public class DBWriter implements ItemWriter<City> {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public void write(List<? extends City> cities) throws Exception{
        System.out.println("Data Saved for Users: " + cities);
        cityRepository.saveAll(cities);
    }
}
