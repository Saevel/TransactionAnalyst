package prv.zielony.transaction.analyst.classical.services;

import java.time.LocalDateTime;

/**
 * Created by zielony on 27.02.16.
 */
public interface CapitalService {
    double calculateCapitalVarianceInPeriod(LocalDateTime from, LocalDateTime to);

    double getAverageInsertionInPeriod(LocalDateTime from, LocalDateTime to);

    double getAverageWithdrawalInPeriod(LocalDateTime from, LocalDateTime to);
}
