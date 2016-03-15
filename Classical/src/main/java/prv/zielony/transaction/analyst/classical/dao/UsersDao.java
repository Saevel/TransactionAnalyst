package prv.zielony.transaction.analyst.classical.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prv.zielony.transaction.analyst.classical.model.User;

import java.util.List;

/**
 * Created by zielony on 27.02.16.
 */
@Repository
public interface UsersDao extends JpaRepository<User, Long> {

    @Query("SELECT u from User u WHERE u.personalData IS NOT NULL AND u.personalData.country = :country")
    List<User> getClientsByCountry(@Param("country") String country);
}
