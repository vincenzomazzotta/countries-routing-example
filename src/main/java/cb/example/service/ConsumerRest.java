package cb.example.service;

import cb.example.model.response.CountriesResponse;
import cb.example.model.response.MessageResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

@Service
public class ConsumerRest {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerRest.class);

    @Value("${user}")
    private String username;

    @Value("${pass}")
    private String password;

    @Value("${urlServerRest}")
    private String urlServerRest;

    public List<String> getCountryList() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<String>> restListCountriesResponse = restTemplate.exchange(
                new StringBuilder(urlServerRest).append("getCountryList").toString(),
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(username, password)),
                new ParameterizedTypeReference<List<String>>() {});
        if (restListCountriesResponse.getStatusCode() == HttpStatus.OK) {
            return restListCountriesResponse.getBody();
        } else {
            return null;
        }
    }

    public CountriesResponse getAllCountriesPaginated(int page, int size) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CountriesResponse> restCountriesResponse = restTemplate.exchange(
                new StringBuilder(urlServerRest).append("all?page=").append(page).append("&size=").append(size).toString(),
                HttpMethod.GET,
                new HttpEntity<String>(createHeaders(username, password)),
                CountriesResponse.class);
        if (restCountriesResponse.getStatusCode() == HttpStatus.OK) {
            return new CountriesResponse(restCountriesResponse.getBody().isError(),
                                            restCountriesResponse.getBody().getMessage(),
                                            restCountriesResponse.getBody().getCountries());
        } else {
            return new CountriesResponse(true,
                                        "Error calling backend REST API",
                                        null);
        }
    }

    public MessageResponse setCapital(String country, String capital) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MessageResponse> restMessageResponse = restTemplate.exchange(
                new StringBuilder(urlServerRest).append("changeCapital?country=").append(country).append("&capital=").append(capital).toString(),
                HttpMethod.PUT,
                new HttpEntity<>(createHeaders(username, password)),
                MessageResponse.class);
        if (restMessageResponse.getStatusCode() == HttpStatus.OK) {
            return new MessageResponse(restMessageResponse.getBody().isError(), restMessageResponse.getBody().getMessage());
        } else {
            return new MessageResponse(true, "Error calling backend REST API");
        }
    }

    HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8")));
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }
}
