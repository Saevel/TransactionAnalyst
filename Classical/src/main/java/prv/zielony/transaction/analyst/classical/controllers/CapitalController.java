package prv.zielony.transaction.analyst.classical.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by zielony on 16.02.16.
 */
@RestController(value = "/capital")
public class CapitalController {

    @RequestMapping(value="/from/{from}/to/{to}/variance", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Double getCapitalVarianceForPeriod(@PathVariable("from")LocalDateTime from, @PathVariable("to") LocalDateTime to) {

        return 11212.121;
    }

    @RequestMapping(value = "from/{from}/to/{to}/insertions/average", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Double getAverageInsertionInPeriod(@PathVariable("from")LocalDateTime from, @PathVariable("to") LocalDateTime to) {

        return 100.77;
    }

    @RequestMapping(value = "from/{from}/to/{to}/withdrawals/average", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Double getAverageWithdrawalInPeriod(@PathVariable("from")LocalDateTime from, @PathVariable("to") LocalDateTime to) {

        return 100.77;
    }
}
