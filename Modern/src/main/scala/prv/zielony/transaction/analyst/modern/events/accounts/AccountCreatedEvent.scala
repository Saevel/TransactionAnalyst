package prv.zielony.transaction.analyst.modern.events.accounts

import java.time.LocalDateTime
import java.util.Date

/**
 * Created by zielony on 14.02.16.
 */
case class AccountCreatedEvent(override val timestamp:Date, val accountId:Long, val balance:Double,
                               val ownerId:Long) extends AccountEvent(timestamp, accountId);
