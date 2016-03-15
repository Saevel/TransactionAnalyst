package prv.zielony.transaction.analyst.classical.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prv.zielony.transaction.analyst.classical.dao.CashOperationsDao;
import prv.zielony.transaction.analyst.classical.model.CashOperation;
import prv.zielony.transaction.analyst.classical.model.CashOperationType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zielony on 27.02.16.
 */
@Service
public class DefaultCapitalService implements CapitalService {

    @Autowired
    private CashOperationsDao cashOperationsDao;

    @Override
    public double calculateCapitalVarianceInPeriod(LocalDateTime from, LocalDateTime to) {

        if(from == null) {
            from = LocalDateTime.MIN;
        }
        if(to == null) {
            to = LocalDateTime.MAX;
        }

        List<CashOperation> cashOperations = cashOperationsDao.findAll();

        double result = 0.0;
        for(CashOperation cashOperation : cashOperations) {
            if(CashOperationType.INSERTION.equals(cashOperation.getType())) {
                result += cashOperation.getAmount();
            }
            else if(CashOperationType.WITHDRAWAL.equals(cashOperation.getType())){
                result -= cashOperation.getAmount();
            }
        }

        return result;
    }

    @Override
    public double getAverageInsertionInPeriod(LocalDateTime from, LocalDateTime to) {
        return getAverageCashOperationInPeriodWithType(CashOperationType.INSERTION, from, to);
    }

    @Override
    public double getAverageWithdrawalInPeriod(LocalDateTime from, LocalDateTime to) {
        return getAverageCashOperationInPeriodWithType(CashOperationType.WITHDRAWAL, from, to);
    }

    private double getAverageCashOperationInPeriodWithType(CashOperationType type, LocalDateTime from, LocalDateTime to) {

        if(from == null) {
            from = LocalDateTime.MIN;
        }
        if(to == null) {
            to = LocalDateTime.MAX;
        }

        List<CashOperation> operations =
                cashOperationsDao.findCashInsertionsByTypeInPeriod(type, from, to);

        double result = 0.0;
        for(CashOperation operation : operations) {
            result += operation.getAmount();
        }

        result = result/operations.size();

        return result;
    }
}
