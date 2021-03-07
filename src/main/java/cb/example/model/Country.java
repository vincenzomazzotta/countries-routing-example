package cb.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    @JsonProperty("name")
    private String country;

    @JsonProperty("capital")
    private String capital;

    @JsonProperty("currencies")
    private List<String> localCurrenciesNames;

    public Country() {}

    public Country(String country, String capital, List<String> localCurrenciesNames) {
        this.country = country;
        this.capital = capital;
        this.localCurrenciesNames = localCurrenciesNames;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<String> getLocalCurrenciesNames() {
        return this.localCurrenciesNames;
    }

    public void setLocalCurrenciesNames(List<LocalCurrency> localCurrencies) {

        List<String> localCurrenciesNames = new ArrayList<String>();
        for (LocalCurrency localCurrency : localCurrencies) {
            localCurrenciesNames.add(localCurrency.getName());
        }
        this.localCurrenciesNames = localCurrenciesNames;
    }
}
