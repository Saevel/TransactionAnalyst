package prv.zielony.transaction.analyst.classical.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prv.zielony.transaction.analyst.classical.dao.AccountsDao;
import prv.zielony.transaction.analyst.classical.dao.CashOperationsDao;
import prv.zielony.transaction.analyst.classical.dao.TransferDao;
import prv.zielony.transaction.analyst.classical.model.*;

import java.time.LocalDateTime;

/**
 * Created by zielony on 27.02.16.
 */
@Service
public class DefaultTransactionService implements TransactionService {

    @Autowired
    private AccountsDao accountsDao;

    @Autowired
    private TransferDao transferDao;

    @Autowired
    private CashOperationsDao cashOperationsDao;

    @Override
    @Transactional
    public void registerTransfer(double amount, long sourceAccountId, long targetAccountId) {

        Account sourceAccount = accountsDao.findOne(sourceAccountId);
        Account targetAccount = accountsDao.findOne(targetAccountId);

        if(sourceAccount == null || targetAccount == null) {
            //TODO: Throw exception
        }

        if(sourceAccount.getBalance() < amount) {
            //TODO: Throw exception
        }

        Transfer transfer = new Transfer();

        transfer.setAmount(amount);
        transfer.setTimestamp(LocalDateTime.now());
        transfer.setSourceAccount(sourceAccount);
        transfer.setTargetAccount(targetAccount);
        transfer.setStatus(TransactionStatus.SUCCESS);

        transferDao.save(transfer);

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);

        accountsDao.save(sourceAccount);
        accountsDao.save(targetAccount);
    }

    @Override
    @Transactional
    public void registerCashOperation(long accountId, double amount, CashOperationType type) {

        Account account = accountsDao.findOne(accountId);

        if(account == null) {
            //TODO: Throw exception
        }

        if(CashOperationType.WITHDRAWAL.equals(type)) {

            if(account.getBalance() < amount) {
                //TODO: throw exception!
            }

            account.setBalance(account.getBalance() - amount);
        }
        else if(CashOperationType.INSERTION.equals(type)) {
            account.setBalance(account.getBalance() + amount);
        }

        CashOperation operation = new CashOperation();
        operation.setTimestamp(LocalDateTime.now());
        operation.setAccount(account);
        operation.setStatus(TransactionStatus.SUCCESS);

        accountsDao.save(account);
        cashOperationsDao.save(operation);
    }

    @Override
    public void registerFailedTransfer(double amount, long sourceAccountId, long targetAccountId) {
        Transfer transfer = new Transfer();

        Account sourceAccount = accountsDao.findOne(sourceAccountId);
        Account targetAccount = accountsDao.findOne(targetAccountId);

        transfer.setSourceAccount(sourceAccount);
        transfer.setTargetAccount(targetAccount);
        transfer.setAmount(amount);
        transfer.setTimestamp(LocalDateTime.now());
        transfer.setStatus(TransactionStatus.FAILED);

        transferDao.save(transfer);
    }

    @Override
    public void registerFailedCashOperation(long accountId, double amount, CashOperationType type) {

        Account account = accountsDao.findOne(accountId);

        CashOperation operation = new CashOperation();
        operation.setAccount(account);
        operation.setType(type);
        operation.setAmount(amount);
        operation.setStatus(TransactionStatus.FAILED);

        cashOperationsDao.save(operation);
    }
}
