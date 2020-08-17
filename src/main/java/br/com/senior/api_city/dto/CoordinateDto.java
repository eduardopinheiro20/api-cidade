package br.com.senior.api_city.dto;

import br.com.senior.api_city.dto.utils.GeoUtils;

/**
 *
 * @author eduardo-pinheiro
 *
 */
public class CoordinateDto {

    private Double latitude;

    private Double longitude;

    public CoordinateDto(Double latitude, Double longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Recupera a latitude
     */
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double distanceInKm(CoordinateDto coordinate) {
        return GeoUtils.geoDistanceInKm(this, coordinate);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new CoordinateDto(this.getLatitude(), this.getLatitude());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CoordinateDto other = (CoordinateDto) obj;
        if (Double.doubleToLongBits(latitude) != Double
                .doubleToLongBits(other.latitude))
            return false;
        if (Double.doubleToLongBits(longitude) != Double
                .doubleToLongBits(other.longitude))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GeoCoordinate [latitude=" + latitude + ", longitude=" + longitude + "]";
    }

}
