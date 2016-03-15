package prv.zielony.transaction.analyst.classical.services;

import org.springframework.transaction.annotation.Transactional;
import prv.zielony.transaction.analyst.classical.model.CashOperationType;
import prv.zielony.transaction.analyst.classical.model.Currency;

/**
 * Created by zielony on 27.02.16.
 */
public interface TransactionService {

    void registerTransfer(double amount, long sourceAccountId, long targetAccountId);

    void registerCashOperation(long accountId, double amount, CashOperationType type);

    void registerFailedTransfer(double amount, long sourceAccountId, long targetAccountId);

    void registerFailedCashOperation(long accountId, double amount, CashOperationType type);
}
