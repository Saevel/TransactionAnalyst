package prv.zielony.transaction.analyst.lambda.events.accounts

import java.time.LocalDateTime

import events.Identifier
import prv.zielony.transaction.analyst.lambda.batch.layer.events.Identifier
import prv.zielony.transaction.analyst.lambda.events.{Identifier, Event}

/**
 * Created by zielony on 14.02.16.
 */
abstract class AccountEvent(timestamp:LocalDateTime, accountId:Long) extends Event(timestamp) with Identifier[Long] {
  override val id:Long = accountId;
}
