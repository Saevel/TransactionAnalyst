package prv.zielony.transaction.analyst.classical.utils;

import org.springframework.stereotype.Component;
import prv.zielony.transaction.analyst.classical.dto.PersonalDataDto;

import prv.zielony.transaction.analyst.classical.model.PersonalData;

/**
 * Created by zielony on 27.02.16.
 */
@Component
public class PersonalDataConverter implements Converter<PersonalDataDto, PersonalData> {

    @Override
    public PersonalData convert(PersonalDataDto source) {

        PersonalData result = new PersonalData();
        result.setBirthDate(source.getBirthDate());
        result.setCountry(source.getCountry());
        result.setName(source.getName());
        result.setSurname(source.getSurname());

        return result;
    }
}
