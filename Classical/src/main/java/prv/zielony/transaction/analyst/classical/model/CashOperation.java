package prv.zielony.transaction.analyst.classical.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

/**
 * Created by zielony on 26.02.16.
 */
@Entity
@DiscriminatorValue("CASH_OPERATION")
public class CashOperation extends Transaction {
    @OneToOne
    private Account account;

    @Enumerated
    private CashOperationType type;

    public CashOperationType getType() {
        return type;
    }

    public void setType(CashOperationType type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
