package prv.zielony.transaction.analyst.classical.dto;

/**
 * Created by zielony on 27.02.16.
 */
public class UserEvent {

    private long id;

    private CrudOperation operation;

    private String username;

    private String password;

    private PersonalDataDto personalData;

    private ContactDataDto contactData;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CrudOperation getOperation() {
        return operation;
    }

    public void setOperation(CrudOperation operation) {
        this.operation = operation;
    }

    public PersonalDataDto getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalDataDto personalData) {
        this.personalData = personalData;
    }

    public ContactDataDto getContactData() {
        return contactData;
    }

    public void setContactData(ContactDataDto contactData) {
        this.contactData = contactData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}