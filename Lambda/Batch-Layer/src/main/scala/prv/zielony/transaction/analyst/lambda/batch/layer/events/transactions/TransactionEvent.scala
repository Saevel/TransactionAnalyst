package prv.zielony.transaction.analyst.lambda.batch.layer.events.transactions

import java.time.LocalDateTime

import prv.zielony.transaction.analyst.lambda.batch.layer.events.{Event, Identifier}

/**
 * Created by zielony on 14.02.16.
 */
abstract class TransactionEvent(timestamp:LocalDateTime, transactionId:Long, val status:TransactionStatus)
  extends Event(timestamp) with Identifier[Long] {

  override val id:Long = transactionId;
}
