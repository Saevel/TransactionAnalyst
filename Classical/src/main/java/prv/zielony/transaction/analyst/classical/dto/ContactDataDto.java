package prv.zielony.transaction.analyst.classical.dto;

import prv.zielony.transaction.analyst.classical.model.Address;

import javax.persistence.Embeddable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zielony on 26.02.16.
 */
@Embeddable
public class ContactDataDto {

  private List<String> phoneNumbers = new LinkedList<String>();

  private List<String> emails = new LinkedList<String>();

  private List<AddressDto> addresses = new LinkedList<AddressDto>();

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

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDto> addresses) {
        this.addresses = addresses;
    }
}