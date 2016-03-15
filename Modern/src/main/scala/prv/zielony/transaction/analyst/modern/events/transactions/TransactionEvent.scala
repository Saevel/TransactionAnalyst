package prv.zielony.transaction.analyst.modern.events.transactions

import java.util.Date

import prv.zielony.transaction.analyst.modern.events.{Event, Identifier}

/**
 * Created by zielony on 14.02.16.
 */
abstract class TransactionEvent(timestamp:Date, transactionId:Long, val status:TransactionStatus)
  extends Event(timestamp) with Identifier[Long] {

  override val id:Long = transactionId;
}
