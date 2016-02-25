package prv.zielony.transaction.analyst.lambda.batch.layer.events.users

import java.time.LocalDateTime

/**
 * Created by zielony on 21.02.16.
 */
case class UserDeletedEvent(override val timestamp:LocalDateTime, override val userId:Long)
  extends UserEvent(timestamp, userId);
