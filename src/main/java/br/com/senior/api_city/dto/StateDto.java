package br.com.senior.api_city.dto;
/**
 *
 * @author  eduardo-pinheiro
 *
 */
public class StateDto {
    private String uf;
    private Long numberOfCities;

    public StateDto() {

    }

    public StateDto(String uf, Long numberOfCities) {
        super();
        this.uf = uf;
        this.numberOfCities = numberOfCities;
    }

    //Getters and Setters
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Long getNumberOfCities() {
        return numberOfCities;
    }

    public void setNumberOfCities(Long numberOfCities) {
        this.numberOfCities = numberOfCities;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numberOfCities == null) ? 0 : numberOfCities.hashCode());
        result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
        StateDto other = (StateDto) obj;
        if (numberOfCities == null) {
            if (other.numberOfCities != null)
                return false;
        } else if (!numberOfCities.equals(other.numberOfCities))
            return false;
        if (uf == null) {
            if (other.uf != null)
                return false;
        } else if (!uf.equals(other.uf))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "State [uf=" + uf + ", numberOfCities=" + numberOfCities + "]";
    }

}
