package prv.zielony.transaction.analyst.modern.events.transactions

import java.util.Date

import prv.zielony.transaction.analyst.modern.model.CashOperationType


/**
 * Created by zielony on 14.02.16.
 */
case class CashOperationEvent(override val timestamp:Date, val transactionId:Long,
                                override val status:TransactionStatus, val accountId:Long, val amount:Double,
                              val operation:CashOperationType) extends TransactionEvent(timestamp,
                                transactionId, status);
