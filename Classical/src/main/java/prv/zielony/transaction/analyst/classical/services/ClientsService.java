package prv.zielony.transaction.analyst.classical.services;

import prv.zielony.transaction.analyst.classical.model.ContactData;
import prv.zielony.transaction.analyst.classical.model.PersonalData;
import prv.zielony.transaction.analyst.classical.model.User;

/**
 * Created by zielony on 27.02.16.
 */
public interface ClientsService {
    double calculateAverageClientAge(String country);

    double calculateClientAgeMedian(String country);

    User createUser(String username, String password);

    void deleteUser(long id);

    User updateUser(String username, String password, PersonalData personalData, ContactData contactData);
}
