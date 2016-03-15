package prv.zielony.transaction.analyst.classical.dto;

/**
 * Created by zielony on 27.02.16.
 */
public class AccountEvent {

    private Long id;

    private CrudOperation operation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CrudOperation getOperation() {
        return operation;
    }

    public void setOperation(CrudOperation operation) {
        this.operation = operation;
    }
}
