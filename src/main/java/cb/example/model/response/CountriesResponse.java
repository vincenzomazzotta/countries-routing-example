package cb.example.model.response;

import cb.example.model.CapitalCurrency;
import cb.example.model.Country;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class CountriesResponse extends MessageResponse {

    private List<Country> countries;

    public CountriesResponse() {}

    public CountriesResponse(boolean error, String message, List<Country> countries) {
        super.setError(error);
        super.setMessage(message);
        this.countries = countries;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE).toString();
    }

}
