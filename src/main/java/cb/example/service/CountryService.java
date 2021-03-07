package cb.example.service;

import cb.example.controller.RestCountries;
import cb.example.model.CapitalCurrency;
import cb.example.model.Country;
import cb.example.model.response.CountriesResponse;
import cb.example.model.response.MessageResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

@Service
public class CountryService {

    private static final Logger logger = LoggerFactory.getLogger(RestCountries.class);

    private final Map<String, CapitalCurrency> councurrentCountries = new ConcurrentSkipListMap<>();

    @Value("${urlSuperSet}")
    private String urlSuperSet;

    @PostConstruct
    public void init() {
        try {
             // Get all Countries, Capital and Currencies list
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Country>> responseEntity = restTemplate.exchange(urlSuperSet,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Country>>() {});

            List<Country> countryList = responseEntity.getBody();

            if (countryList != null) {
                for (Country country : countryList) {
                    if (country.getCountry() != null) {
                        CapitalCurrency capitalCurrency = new CapitalCurrency();
                        capitalCurrency.setCapital(country.getCapital());
                        capitalCurrency.setCurrenciesName(country.getLocalCurrenciesNames());

                        councurrentCountries.put(country.getCountry(), capitalCurrency);
                    }
                }
            }
            logger.debug("Country service initialized by external REST API." + urlSuperSet);
        }catch (HttpStatusCodeException e) {
            logger.error("Error calling superset data external service: statusCode" + e.getStatusCode().toString());
        } catch (Exception e) {
             logger.error("Exception calling superset data external service: " + e.getMessage());
        }
    }

    // page and size > 0
    public CountriesResponse getAllCountriesPaginated(int page, int size) {
        try {
            if (page < 1 || size < 1) {
                return new CountriesResponse(true, "Can't return page of countries because page and size must be > 0", null);
            }

            int start = (page - 1) * size + 1;
            int end = page * size;

            int i = 1;
            List<Country> countryList = new ArrayList<>();
            for (Map.Entry<String, CapitalCurrency> mapCountry : councurrentCountries.entrySet()) {
                if (i >= start && i <= end) {
                    countryList.add(new Country(mapCountry.getKey(), mapCountry.getValue().getCapital(), mapCountry.getValue().getCurrenciesNames()));
                } else if (i > end) {
                    break;
                }
                i++;
            }

            return new CountriesResponse(false, "Success", countryList);
        } catch (Exception e) {
            logger.error(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE));
            return new CountriesResponse(true,new StringBuilder("Error List<Country> getCountries(").append(page).append(",").append(size).toString(), null);
        }
    }

    public MessageResponse setCapital(String country, String capital) {
        if (country == null || capital == null || country.trim().equals("") || capital.trim().equals("")) {
            return new MessageResponse(true, "Country and Capital must have a value.");
        } else {
            CapitalCurrency capitalCurrency = councurrentCountries.get(country);

            if (capitalCurrency == null) {
                return new MessageResponse(true, new StringBuilder("Country:").append(country).append(" doesn't exist:").toString());
            } else {
                capitalCurrency.setCapital(capital);
                councurrentCountries.put(country, capitalCurrency);

                return new MessageResponse(false, new StringBuilder("Capital:").append(capital).append(" is updated on Country:").append(country).toString());
            }
        }
    }

}
