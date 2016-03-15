package prv.zielony.transaction.analyst.classical.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import prv.zielony.transaction.analyst.classical.services.CapitalService;

import java.time.LocalDateTime;

/**
 * Created by zielony on 16.02.16.
 */
@RestController(value = "/capital")
public class CapitalController {

    @Autowired
    private CapitalService capitalService;

    @RequestMapping(value="/from/{from}/to/{to}/variance", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Double getCapitalVarianceForPeriod(@PathVariable("from")LocalDateTime from, @PathVariable("to") LocalDateTime to) {
        return capitalService.calculateCapitalVarianceInPeriod(from, to);
    }

    @RequestMapping(value = "from/{from}/to/{to}/insertions/average", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Double getAverageInsertionInPeriod(@PathVariable("from")LocalDateTime from, @PathVariable("to") LocalDateTime to) {
        return capitalService.getAverageInsertionInPeriod(from, to);
    }

    @RequestMapping(value = "from/{from}/to/{to}/withdrawals/average", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Double getAverageWithdrawalInPeriod(@PathVariable("from")LocalDateTime from, @PathVariable("to") LocalDateTime to) {
        return capitalService.getAverageWithdrawalInPeriod(from, to);
    }
}
