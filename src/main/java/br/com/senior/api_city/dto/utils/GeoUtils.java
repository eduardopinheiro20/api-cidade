package br.com.senior.api_city.dto.utils;

import br.com.senior.api_city.dto.CoordinateDto;
import org.springframework.stereotype.Component;

/**
 *
 * @author eduardo-pinheiro
 *
 */

@Component
public class GeoUtils {

    public static int EARTH_RADIUS_KM = 6371;

    public static Double geoDistanceInKm(Double firstLatitude,
                                         Double firstLongitude, Double secondLatitude, Double secondLongitude) {

        //  Graus pra radianos das latitudes
        Double firstLatToRad = Math.toRadians(firstLatitude);
        Double secondLatToRad = Math.toRadians(secondLatitude);

        // Diferença das longitudes
        Double deltaLongitudeInRad = Math.toRadians(secondLongitude
                - firstLongitude);

        // Cálcula da distância entre os pontos
        return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
                * Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
                * Math.sin(secondLatToRad))
                * EARTH_RADIUS_KM;
    }

    public static Double geoDistanceInKm(CoordinateDto first,
                                         CoordinateDto second) {
        return geoDistanceInKm(first.getLatitude(), first.getLongitude(),
                second.getLatitude(), second.getLongitude());
    }

}
