package prv.zielony.transaction.analyst.classical.utils;

import org.springframework.stereotype.Component;
import prv.zielony.transaction.analyst.classical.dto.AddressDto;
import prv.zielony.transaction.analyst.classical.model.Address;

/**
 * Created by zielony on 27.02.16.
 */
@Component
public class AddressConverter implements Converter<AddressDto, Address> {

    @Override
    public Address convert(AddressDto source) {

        Address result = new Address();
        result.setCity(source.getCity());
        result.setCode(source.getCode());
        result.setCountry(source.getCountry());
        result.setStreetName(source.getStreetName());
        result.setStreetNumber(source.getStreetNumber());

        return result;
    }
}
