package cb.example;

import cb.example.model.response.CountriesResponse;
import cb.example.model.response.MessageResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ExampleBackendApplicationTests {
    @LocalServerPort
    int randomServerPort;

    @Value("${user}")
    private String username;

    @Value("${pass}")
    private String password;

    @Value("${urlServerRest}")
    private String urlServerRest;

    HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }

    @Test
    public void testChangeCapitalToAfghanistan() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<MessageResponse> result = restTemplate.exchange(
                new StringBuilder(urlServerRest).append("changeCapital?country=").append("Afghanistan").append("&capital=").append("Unit test: Change capital on Afghanistan").toString(),
                HttpMethod.PUT,
                new HttpEntity<>(createHeaders(username, password)),
                MessageResponse.class);

        //Verify request succeed
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testGetAllFirstPageOf25Rows() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CountriesResponse> result = restTemplate.exchange(
                new StringBuilder(urlServerRest).append("all?page=1&size=25").toString(),
                HttpMethod.GET,
                new HttpEntity<String>(createHeaders(username, password)),
                CountriesResponse.class);

        //Verify request succeed
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testGetCountryList() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<String>> result = restTemplate.exchange(
                new StringBuilder(urlServerRest).append("getCountryList").toString(),
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(username, password)),
                new ParameterizedTypeReference<List<String>>() {
                });
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    /***************************
     * TEST WITHOUT BASIC AUTH *
     ***************************/

    @Test
    public void testChangeCapitalToAfghanistanNOAUTH() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MessageResponse> result = null;
        try {
            result = restTemplate.exchange(
                    new StringBuilder(urlServerRest).append("changeCapital?country=").append("Afghanistan").append("&capital=").append("Unit test: Change capital on Afghanistan").toString(),
                    HttpMethod.PUT,
                    null,
                    MessageResponse.class);
        } catch (HttpClientErrorException hcee) {
            //Verify request 401 UNAUTHORIZED
            Assertions.assertEquals(HttpStatus.UNAUTHORIZED, hcee.getStatusCode());

        }
    }

    @Test
    public void testGetAllFirstPageOf25RowsNOAUTH() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CountriesResponse> result = null;
        try {
            result = restTemplate.exchange(
                    new StringBuilder(urlServerRest).append("all?page=").append(1).append("&size=").append(25).toString(),
                    HttpMethod.GET,
                    null,
                    CountriesResponse.class);
        } catch (HttpClientErrorException hcee) {
            //Verify request 401 UNAUTHORIZED
            Assertions.assertEquals(HttpStatus.UNAUTHORIZED, hcee.getStatusCode());
        }
    }

    @Test
    public void testGetCountryListNOAUTH() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<String>> result = null;
        try {
            result = restTemplate.exchange(
                    new StringBuilder(urlServerRest).append("getCountryList").toString(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<String>>() {
                    });
        } catch (HttpClientErrorException hcee) {
            //Verify request 401 UNAUTHORIZED
            Assertions.assertEquals(HttpStatus.UNAUTHORIZED, hcee.getStatusCode());
        }
    }

}