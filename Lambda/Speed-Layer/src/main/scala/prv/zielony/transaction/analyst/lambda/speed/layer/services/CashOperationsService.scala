package prv.zielony.transaction.analyst.lambda.speed.layer.services

import java.time.LocalDateTime

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.model.{CashOperationType, CashOperation}

/**
 * Created by zielony on 25.02.16.
 */
object CashOperationsService {

  private[services] def averageWithdrawal(getOps:((Option[LocalDateTime], Option[LocalDateTime]) => RDD[CashOperation]))
                                    (from:Option[LocalDateTime], to:Option[LocalDateTime]) = {
    getOps(from, to) filter { op =>
      op.operation == CashOperationType.Withdrawal
    } map { op =>
      op.amount
    } mean
  }

  private[services] def averageInsertion(getOps:((Option[LocalDateTime], Option[LocalDateTime]) => RDD[CashOperation]))
                                   (from:Option[LocalDateTime], to:Option[LocalDateTime]) = {

    getOps(from, to) filter { op =>
      op.operation == CashOperationType.Insertion
    } map { op =>
      op.amount
    } mean
  }

  private[services] def capitalVariation(findOps:((Option[LocalDateTime], Option[LocalDateTime]) => RDD[CashOperation]))
                              (from:Option[LocalDateTime], to:Option[LocalDateTime]):Double = {
    findOps(from, to) map (_.amount) reduce (_+_)
  }
}