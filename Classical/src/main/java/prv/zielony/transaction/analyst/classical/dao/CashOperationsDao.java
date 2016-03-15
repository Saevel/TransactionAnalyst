package prv.zielony.transaction.analyst.classical.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prv.zielony.transaction.analyst.classical.model.CashOperation;
import prv.zielony.transaction.analyst.classical.model.CashOperationType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zielony on 27.02.16.
 */
@Repository
public interface CashOperationsDao extends JpaRepository<CashOperation, Long> {

    @Query("SELECT co FROM CashOperation co WHERE co.type = :type AND co.timestamp >= :from AND timestamp >= :to")
    List<CashOperation> findCashInsertionsByTypeInPeriod(@Param("type") CashOperationType type,
                                                         @Param("from") LocalDateTime from,
                                                         @Param("to") LocalDateTime to);

    @Query("SELECT co FROM CashOperation co WHERE co.timestamp >= :from AND timestamp >= :to")
    List<CashOperation> findCashOperationsInPeriod(@Param("from") LocalDateTime from,
                                                   @Param("to") LocalDateTime to);
}
