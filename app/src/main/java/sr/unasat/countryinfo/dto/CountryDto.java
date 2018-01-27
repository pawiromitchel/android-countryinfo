package sr.unasat.countryinfo.dto;

import java.io.Serializable;

/**
 * Created by mpawirodinomo on 1/27/2018.
 */

public class CountryDto implements Serializable{
    private String name;
    private String capital;
    private String region;

    // default constructor
    public CountryDto() {
    }

    // custom constructor
    public CountryDto(String name, String capital, String region) {
        this.name = name;
        this.capital = capital;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    // convert System.Out to custom string
    @Override
    public String toString() {
        return  name;
    }
}
