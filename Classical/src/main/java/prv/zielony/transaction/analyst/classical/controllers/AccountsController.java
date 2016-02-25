package prv.zielony.transaction.analyst.classical.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import prv.zielony.transaction.analyst.classical.dto.AmountCurrencyDto;
import prv.zielony.transaction.analyst.classical.model.Currency;

/**
 * Created by zielony on 16.02.16.
 */
@RestController("/accounts")
public class AccountsController {

    @RequestMapping(value = "/country/{country}/balance?minAge={minAge}&maxAge={maxAge}",
            produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public AmountCurrencyDto getAverageAccountBalanceByCountry(@PathVariable("country") String country,
                                                               @RequestParam("minAge") Integer minAge,
                                                               @RequestParam("maxAge") Integer maxAge) {
        return new AmountCurrencyDto(10, Currency.USD);
    }

    @RequestMapping(value = "/country/{country}/active", method = RequestMethod.GET)
    public int countActiveAccountsPerCountry(String country) {
        return 1700;
    }
}
