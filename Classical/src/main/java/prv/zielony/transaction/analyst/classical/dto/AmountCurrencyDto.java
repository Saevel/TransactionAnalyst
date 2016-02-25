package prv.zielony.transaction.analyst.classical.dto;

import prv.zielony.transaction.analyst.classical.model.Currency;

/**
 * Created by zielony on 16.02.16.
 */
public class AmountCurrencyDto {

    private double amount;

    private Currency currency;

    public AmountCurrencyDto() {
    }

    public AmountCurrencyDto(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
