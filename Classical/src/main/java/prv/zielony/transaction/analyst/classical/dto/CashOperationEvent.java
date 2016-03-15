package prv.zielony.transaction.analyst.classical.dto;

import prv.zielony.transaction.analyst.classical.model.CashOperationType;

/**
 * Created by zielony on 27.02.16.
 */
public class CashOperationEvent {

    private Long accountId;

    private CashOperationType type;

    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CashOperationType getType() {
        return type;
    }

    public void setType(CashOperationType type) {
        this.type = type;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
