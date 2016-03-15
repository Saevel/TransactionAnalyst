package prv.zielony.transaction.analyst.classical.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by zielony on 26.02.16.
 */
@Entity
@DiscriminatorValue("TRANSFER")
public class Transfer extends Transaction {

    @OneToOne
    private Account sourceAccount;

    @OneToOne
    private Account targetAccount;

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }
}