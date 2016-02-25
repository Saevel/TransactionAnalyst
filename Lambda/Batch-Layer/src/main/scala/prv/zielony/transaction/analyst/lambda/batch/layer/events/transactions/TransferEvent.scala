package prv.zielony.transaction.analyst.lambda.batch.layer.events.transactions

import java.time.LocalDateTime

import prv.zielony.transaction.analyst.lambda.model.Currency

/**
 * Created by zielony on 14.02.16.
 */
case class TransferEvent(override val timestamp:LocalDateTime, val transactionId:Long,
                         override val status:TransactionStatus, val sourceAccountId:Long, val targetAccountId:Long,
                         val amount:Double, val currency: Currency) extends TransactionEvent(timestamp, transactionId, status);
