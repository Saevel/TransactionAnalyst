package prv.zielony.transaction.analyst.modern.events.accounts

import java.util.Date

import prv.zielony.transaction.analyst.modern.events.{Identifier, Event}

/**
 * Created by zielony on 14.02.16.
 */
abstract class AccountEvent(timestamp:Date, accountId:Long) extends Event(timestamp) with Identifier[Long] {
  override val id:Long = accountId;
}
