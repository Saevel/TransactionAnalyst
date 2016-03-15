package prv.zielony.transaction.analyst.lambda.events.accounts

import java.time.LocalDateTime

/**
 * Created by zielony on 14.02.16.
 */
case class AccountRemovedEvent(override val timestamp:LocalDateTime, val accountId:Long)
  extends AccountEvent(timestamp, accountId);
