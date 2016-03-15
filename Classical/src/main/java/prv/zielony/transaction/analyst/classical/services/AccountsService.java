package prv.zielony.transaction.analyst.classical.services;

import prv.zielony.transaction.analyst.classical.model.Account;

/**
 * Created by zielony on 27.02.16.
 */
public interface AccountsService {
    double getAverageBalanceByCountry(String country, Integer minAge, Integer maxAge);

    int getAccountNumberForCountry(String country);

    Account findAccount(Long id);

    Account createAccount(Long ownerId);

    void removeAccount(Long accountId);
}
