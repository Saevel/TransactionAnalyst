package prv.zielony.transaction.analyst.lambda.batch.layer.events.accounts

import java.time.LocalDateTime

/**
 * Created by zielony on 14.02.16.
 */
case class AccountRemovedEvent(override val timestamp:LocalDateTime, override val accountId:Long)
  extends AccountEvent(timestamp, accountId);
