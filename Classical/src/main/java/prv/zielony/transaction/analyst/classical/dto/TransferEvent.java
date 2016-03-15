package prv.zielony.transaction.analyst.classical.dto;

import prv.zielony.transaction.analyst.classical.model.Currency;

import java.time.LocalDateTime;

/**
 * Created by zielony on 27.02.16.
 */
public class TransferEvent {

    private LocalDateTime timestamp;

    private Long sourceAccountId;

    private Long targetAccountId;

    private Double amount;

    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(Long sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(Long targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}