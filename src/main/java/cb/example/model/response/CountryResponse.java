package cb.example.model.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class CountryResponse extends MessageResponse implements Serializable {
    private String country;
    private String currency;
    private String capital;

    public CountryResponse(boolean error, String message, String country, String currency, String capital){
        super.setError(error);
        super.setMessage(message);
        this.setCountry(country);
        this.setCapital(capital);
        this.setCurrency(currency);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
