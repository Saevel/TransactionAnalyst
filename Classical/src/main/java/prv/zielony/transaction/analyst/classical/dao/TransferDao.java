package prv.zielony.transaction.analyst.classical.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prv.zielony.transaction.analyst.classical.model.Transfer;

/**
 * Created by zielony on 27.02.16.
 */
@Repository
public interface TransferDao extends JpaRepository<Transfer, Long> {
    ;
}
