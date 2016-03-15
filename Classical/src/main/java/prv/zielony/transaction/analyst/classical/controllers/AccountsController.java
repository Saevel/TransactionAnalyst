package prv.zielony.transaction.analyst.classical.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import prv.zielony.transaction.analyst.classical.model.Currency;
import prv.zielony.transaction.analyst.classical.services.AccountsService;

/**
 * Created by zielony on 16.02.16.
 */
@RestController("/accounts")
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @RequestMapping(value = "/country/{country}/balance?minAge={minAge}&maxAge={maxAge}",
            produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Double getAverageAccountBalanceByCountry(@PathVariable("country") String country,
                                                               @RequestParam("minAge") Integer minAge,
                                                               @RequestParam("maxAge") Integer maxAge) {
        return accountsService.getAverageBalanceByCountry(country, minAge, maxAge);
    }

    @RequestMapping(value = "/country/{country}/active", method = RequestMethod.GET)
    public int countActiveAccountsPerCountry(String country) {
        return accountsService.getAccountNumberForCountry(country);
    }
}
