package prv.zielony.transaction.analyst.classical.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by zielony on 26.02.16.
 */
@Entity
public class Account {

    @Id
    private Long id;

    @Version
    private LocalDateTime timestamp;

    private double balance;

    @ManyToOne
    private User owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
