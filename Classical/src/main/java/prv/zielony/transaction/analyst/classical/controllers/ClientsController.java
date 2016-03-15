package prv.zielony.transaction.analyst.classical.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prv.zielony.transaction.analyst.classical.services.ClientsService;

import java.time.LocalDateTime;

/**
 * Created by zielony on 16.02.16.
 */
@RestController("/clients")
public class ClientsController {

    @Autowired
    private ClientsService clientsService;

    @RequestMapping(value="/age/average?country={country}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Double getAverageClientAge(@RequestParam("country") String country){
        return clientsService.calculateAverageClientAge(country);
    }

    @RequestMapping(value="/age/median?country={country}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Double getClientAgeMedian(@RequestParam("country") String country){
        return clientsService.calculateClientAgeMedian(country);
    }
}
