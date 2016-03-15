package prv.zielony.transaction.analyst.classical.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prv.zielony.transaction.analyst.classical.model.Account;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zielony on 26.02.16.
 */
@Repository
public interface AccountsDao extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a JOIN FETCH a.owner o WHERE D o.personalData IS NOT NULL " +
            "AND o.personalData.country IS NOT NULL AND o.personalData.country = :country AND o.birthDate IS NOT NULL " +
            "AND o.birthDate >= :startDate AND o.birthDate <= :endDate")
    public List<Account> findAccountsByCountryAndUserBirthDate(@Param("country") String country,
                                                               @Param("startDate") LocalDateTime startDate,
                                                               @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(a) FROM Account a JOIN FETCH a.owner o WHERE o.personalData IS NOT NULL AND" +
            " o.personalData.country IS NOT NULL AND o.personalData.country = :country")
    public int countAccountsByCountry(@Param("country") String country);
}
