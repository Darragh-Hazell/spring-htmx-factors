package io.github.darraghhazell.factors.controllers;

import io.github.darraghhazell.factors.services.FactorsService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@HxRequest
@RequestMapping("/hx")
public class HxController {

    private final FactorsService factorsService;

    @Autowired
    public HxController(FactorsService factorsService) {
        this.factorsService = factorsService;
    }

    @PostMapping("/factors")
    public HtmxResponse factors(@RequestParam int number) {
        if (number <= 100000) {
            var model = Map.of("factors", factorsService.getFactors(number));
            return HtmxResponse.builder().view(new ModelAndView("fragments :: factors", model)).build();
        } else {
            return HtmxResponse.builder().view("fragments :: error").build();
        }
    }
}
