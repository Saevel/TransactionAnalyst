package prv.zielony.transaction.analyst.classical.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prv.zielony.transaction.analyst.classical.dto.AddressDto;
import prv.zielony.transaction.analyst.classical.dto.ContactDataDto;
import prv.zielony.transaction.analyst.classical.model.Address;
import prv.zielony.transaction.analyst.classical.model.ContactData;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zielony on 27.02.16.
 */
@Component
public class ContactDataConverter implements Converter<ContactDataDto, ContactData> {

    @Autowired
    private Converter<AddressDto, Address> addressConverter;

    @Override
    public ContactData convert(ContactDataDto source) {

        ContactData result = new ContactData();
        result.setEmails(source.getEmails());
        result.setPhoneNumbers(source.getPhoneNumbers());

        if(source.getAddresses() != null) {
            List<Address> addresses = new LinkedList<Address>();
            for(AddressDto dto : source.getAddresses()) {
                addresses.add(addressConverter.convert(dto));
            }

            result.setAddresses(addresses);
        }

        return result;
    }
}
