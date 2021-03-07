package cb.example.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Scope(value="request")
public class CapitalCurrency {

    private String capital;
    private List<String> currenciesNames;

    public synchronized String getCapital() {
        return capital;
    }

    public synchronized void setCapital(String capital) {
        this.capital = capital;
    }

    public List<String> getCurrenciesNames() {
        return currenciesNames;
    }

    public void setCurrenciesName(List<String> currenciesName) {
        this.currenciesNames = currenciesName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
