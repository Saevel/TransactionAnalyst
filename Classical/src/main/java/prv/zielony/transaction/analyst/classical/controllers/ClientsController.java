package prv.zielony.transaction.analyst.classical.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by zielony on 16.02.16.
 */
@RestController("/clients")
public class ClientsController {

    @RequestMapping(value="/age/average?country={country}&timestamp={timestamp}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Double getAverageClientAge(@RequestParam("country") String country,
                                      @RequestParam("timestamp")LocalDateTime timestamp){

        return 18.0;
    }

    @RequestMapping(value="/age/median?country={country}&timestamp={timestamp}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Double getClientAgeMedian(@RequestParam("country") String country,
                                      @RequestParam("timestamp")LocalDateTime timestamp){

        return 18.0;
    }
}
