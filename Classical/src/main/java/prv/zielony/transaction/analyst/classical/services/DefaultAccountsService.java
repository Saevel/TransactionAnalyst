package prv.zielony.transaction.analyst.classical.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prv.zielony.transaction.analyst.classical.dao.AccountsDao;
import prv.zielony.transaction.analyst.classical.dao.UsersDao;
import prv.zielony.transaction.analyst.classical.model.Account;
import prv.zielony.transaction.analyst.classical.model.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zielony on 27.02.16.
 */
@Service
public class DefaultAccountsService implements AccountsService {

    @Autowired
    private AccountsDao accountsDao;

    @Autowired
    private UsersDao userDao;

    @Override
    public double getAverageBalanceByCountry(String country, Integer minAge, Integer maxAge) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = now.minusYears(maxAge);
        LocalDateTime endDate = now.minusYears(minAge);

        List<Account> accounts = accountsDao.findAccountsByCountryAndUserBirthDate(country, startDate, endDate);

        double result = 0;
        for(Account account : accounts) {
            result += account.getBalance();
        }

        result = result / accounts.size();

        return result;
    }

    @Override
    public int getAccountNumberForCountry(String country) {
        return accountsDao.countAccountsByCountry(country);
    }

    @Override
    public Account findAccount(Long id) {
        return accountsDao.findOne(id);
    }

    @Transactional
    @Override
    public Account createAccount(Long ownerId) {

        User owner = userDao.findOne(ownerId);

        if(owner == null) {
            //TODO: Throw exception.
        }

        Account account = new Account();
        account.setOwner(owner);

        owner.getAccounts().add(account);

        userDao.save(owner);
        return accountsDao.save(account);
    }

    @Override
    @Transactional
    public void removeAccount(Long accountId) {

        Account account = accountsDao.findOne(accountId);
        if(account == null) {
            //TODO: Throw exception
        }

        User owner = account.getOwner();
        owner.getAccounts().remove(account);

        userDao.save(owner);
        accountsDao.delete(account);
    }
}
