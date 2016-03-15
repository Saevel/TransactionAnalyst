package prv.zielony.transaction.analyst.classical.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.UnexpectedRollbackException;
import prv.zielony.transaction.analyst.classical.dto.*;
import prv.zielony.transaction.analyst.classical.services.AccountsService;
import prv.zielony.transaction.analyst.classical.services.ClientsService;
import prv.zielony.transaction.analyst.classical.services.TransactionService;
import prv.zielony.transaction.analyst.classical.utils.ContactDataConverter;
import prv.zielony.transaction.analyst.classical.utils.PersonalDataConverter;

/**
 * Created by zielony on 27.02.16.
 */
@Component
public class MessageListener {

    @Autowired
    private TransactionService transfersService;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private PersonalDataConverter personalDataConverter;

    @Autowired
    private ContactDataConverter contactDataConverter;

    @JmsListener(destination = "transfer-event")
    public void consumeTransactionEvent(TransferEvent event) {
        try {
            transfersService.registerTransfer(event.getAmount(), event.getSourceAccountId(), event.getTargetAccountId());
        }
        catch(UnexpectedRollbackException e) {
            transfersService.registerFailedTransfer(event.getAmount(), event.getSourceAccountId(), event.getTargetAccountId());
        }
    }

    @JmsListener(destination = "account-event")
    public void consumeAccountEvent(AccountEvent event) {

        if(CrudOperation.CREATE.equals(event.getOperation())) {
            accountsService.createAccount(event.getId());
        }
        else if(CrudOperation.DELETE.equals(event.getOperation())) {
            accountsService.removeAccount(event.getId());
        }
    }

    @JmsListener(destination = "user-event")
    public void consumeUserEvent(UserEvent event) {
        switch(event.getOperation()) {

            case CREATE: clientsService.createUser(event.getUsername(), event.getPassword());

            case UPDATE: clientsService.deleteUser(event.getId());

            case DELETE: clientsService.updateUser(event.getUsername(), event.getPassword(),
                    personalDataConverter.convert(event.getPersonalData()),
                    contactDataConverter.convert(event.getContactData()));

            default: //TODO: Throw exception
        }
    }

    @JmsListener(destination = "cash-operation-event")
    public void consumeCashOperationEvent(CashOperationEvent event) {
        try {
            transfersService.registerCashOperation(event.getAccountId(), event.getAmount(), event.getType());
        }
        catch(UnexpectedRollbackException e) {
            transfersService.registerFailedCashOperation(event.getAccountId(), event.getAmount(), event.getType());
        }
    }
}
