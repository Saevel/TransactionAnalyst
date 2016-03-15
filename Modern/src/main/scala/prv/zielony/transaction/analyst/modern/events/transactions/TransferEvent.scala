package prv.zielony.transaction.analyst.modern.events.transactions

import java.util.Date

/**
 * Created by zielony on 14.02.16.
 */
case class TransferEvent(override val timestamp:Date, val transactionId:Long,
                         override val status:TransactionStatus, val sourceAccountId:Long, val targetAccountId:Long,
                         val amount:Double) extends TransactionEvent(timestamp, transactionId, status);
