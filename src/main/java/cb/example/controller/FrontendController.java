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

    @RequestMapping("/all")
    public String getAllCountriesPaginated(@RequestParam @Min(1) int page, @RequestParam @Min(1) int size, Model model) {
        model.addAttribute("countriesResponse", consumerRest.getAllCountriesPaginated(page, size));
        return "getAllCountriesPaginated";
    }

    @RequestMapping("/changeCapital")
    public String changeCapital(@RequestParam String country, @RequestParam String capital, Model model) {
        model.addAttribute("messageResponse", consumerRest.setCapital(country, capital));
        return "changeCapitalByCountry";
    }
}
