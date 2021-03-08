package cb.example.controller;

import cb.example.service.ConsumerRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;

@Controller
public class FrontendController {

    private static final Logger logger = LoggerFactory.getLogger(FrontendController.class);

    @Autowired
    ConsumerRest consumerRest;

    @RequestMapping("/allCountries")
    public String getAllCountriesPaginated(@RequestParam(required = false) @Min(1) Integer page, @RequestParam(required = false) @Min(1) Integer size, Model model) {
        if (page != null && size != null) {
            model.addAttribute("countriesResponse", consumerRest.getAllCountriesPaginated(page, size));
        }
        return "getAllCountriesPaginated";
    }

    @RequestMapping("/setCapital")
    public String changeCapital(@RequestParam(required = false) String country, @RequestParam(required = false) String capital, Model model) {

        model.addAttribute("countriesListsResponse", consumerRest.getCountryList());
        if (country != null && capital != null) {
            model.addAttribute("messageResponse", consumerRest.setCapital(country, capital));
        }
        return "changeCapitalByCountry";
    }
}
