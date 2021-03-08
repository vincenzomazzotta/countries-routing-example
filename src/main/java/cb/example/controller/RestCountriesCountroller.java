package cb.example.controller;

import cb.example.model.response.CountriesResponse;
import cb.example.model.response.MessageResponse;
import cb.example.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest/v1")
public class RestCountriesCountroller {

    private static Logger logger = LoggerFactory.getLogger(RestCountriesCountroller.class);

    @Autowired
    CountryService countryService;

    @GetMapping("/getCountryList")
    @ResponseBody
    public ResponseEntity<List<String>> getCountryList() {
        return new ResponseEntity<List<String>>(countryService.getListCountryField(), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<CountriesResponse> all(@RequestParam @Min(1) Integer page , @RequestParam @Min(1) Integer size) {
        CountriesResponse countriesResponse = countryService.getAllCountriesPaginated(page, size);
        return new ResponseEntity<CountriesResponse>(countriesResponse,
                countriesResponse.isError()
                        ? HttpStatus.INTERNAL_SERVER_ERROR
                        : HttpStatus.OK);
    }

    @PutMapping("/changeCapital")
    @ResponseBody
    public ResponseEntity<MessageResponse> changeCapital(@RequestParam String country, @RequestParam String capital) {
        MessageResponse messageResponse = countryService.setCapital(country, capital);
        return new ResponseEntity<MessageResponse>(messageResponse,
                messageResponse.isError()
                        ? HttpStatus.INTERNAL_SERVER_ERROR
                        : HttpStatus.OK);
    }
}
