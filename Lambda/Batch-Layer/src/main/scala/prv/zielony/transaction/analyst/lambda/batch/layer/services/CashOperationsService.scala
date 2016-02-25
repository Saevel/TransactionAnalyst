package prv.zielony.transaction.analyst.lambda.batch.layer.services

import java.time.LocalDateTime

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.batch.layer.events.transactions.{TransactionStatus, CashOperationEvent}
import prv.zielony.transaction.analyst.lambda.model.{CashOperationType, Currency, CashOperation}

/**
 * Created by zielony on 21.02.16.
 */
object CashOperationsService {

  def getAllSuccessfulCashOperations(operations:(=> RDD[CashOperationEvent])):RDD[CashOperation] = {

    operations filter { event =>
      event.status == TransactionStatus.Success
    } map { event =>
      createCashOperation(event)
    }
  }

  private def createCashOperation(event:CashOperationEvent):CashOperation = new CashOperation {
    override val operation: CashOperationType = event.operation
    override val accountId: Long = event.accountId
    override val amount: Double = event.amount
    override val currency: Currency = event.currency
    override val timestamp: LocalDateTime = event.timestamp
  }
}
