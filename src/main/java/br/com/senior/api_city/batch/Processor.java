package br.com.senior.api_city.batch;

import br.com.senior.api_city.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<City, City> {

    private static final Logger log = LoggerFactory.getLogger(Processor.class);

    @Override
    public City process(City city) throws Exception {
        return city;
    }

    public static Logger getLog() {
        return log;
    }
}
