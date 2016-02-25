package prv.zielony.transaction.analyst.lambda.batch.layer.events.transactions

import java.time.LocalDateTime

import prv.zielony.transaction.analyst.lambda.model.{CashOperationType, Currency}

/**
 * Created by zielony on 14.02.16.
 */
case class CashOperationEvent(override val timestamp:LocalDateTime, val transactionId:Long,
                                override val status:TransactionStatus, val accountId:Long, val amount:Double,
                                val currency:Currency, val operation:CashOperationType) extends TransactionEvent(timestamp,
                                transactionId, status);
