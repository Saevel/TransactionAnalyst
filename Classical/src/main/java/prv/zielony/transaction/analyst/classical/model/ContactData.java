package prv.zielony.transaction.analyst.classical.model;

import javax.persistence.Embeddable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zielony on 26.02.16.
 */
@Embeddable
public class ContactData {

  private List<String> phoneNumbers = new LinkedList<String>();

  private List<String> emails = new LinkedList<String>();

  private List<Address> addresses = new LinkedList<Address>();

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
