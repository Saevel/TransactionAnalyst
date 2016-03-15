package prv.zielony.transaction.analyst.classical.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prv.zielony.transaction.analyst.classical.dao.UsersDao;
import prv.zielony.transaction.analyst.classical.model.ContactData;
import prv.zielony.transaction.analyst.classical.model.PersonalData;
import prv.zielony.transaction.analyst.classical.model.User;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zielony on 27.02.16.
 */
@Service
public class DefaultClientsService implements ClientsService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public double calculateAverageClientAge(String country) {

        List<User> users = usersDao.getClientsByCountry(country);

        double avg = 0.0;
        LocalDateTime now = LocalDateTime.now();
        for(User user : users) {
            avg += now.getYear() - user.getPersonalData().getBirthDate().getYear();
        }

        avg = avg / users.size();

       return avg;
    }

    @Override
    public double calculateClientAgeMedian(String country) {

        List<User> users = usersDao.getClientsByCountry(country);

        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getPersonalData().getBirthDate().compareTo(o2.getPersonalData().getBirthDate());
            }
        });

        int mediumIndex = Math.floorDiv(users.size(), 2);
        LocalDateTime now = LocalDateTime.now();


        return (now.getYear() - users.get(mediumIndex).getPersonalData().getBirthDate().getYear());
    }

    @Override
    public User createUser(String username, String password) {
        return null;
    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public User updateUser(String username, String password, PersonalData personalData, ContactData contactData) {
        return null;
    }
}