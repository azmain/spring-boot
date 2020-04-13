package azmain.github.io.controller;

import azmain.github.io.service.covid.CovidService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "covid", produces = MediaType.APPLICATION_JSON_VALUE)
public class CovidController {

    private final CovidService covidService;

    public CovidController(CovidService covidService) {
        this.covidService = covidService;
    }

    @GetMapping(path = "dailyDataReport")
    public ResponseEntity<?> getCovidDailyDataReport(){

        return ResponseEntity.ok(covidService.fetchCovidDailyDataReport());
    }
}
