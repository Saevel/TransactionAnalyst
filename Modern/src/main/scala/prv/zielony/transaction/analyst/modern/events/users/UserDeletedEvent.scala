package prv.zielony.transaction.analyst.modern.events.users

import java.util.Date

/**
 * Created by zielony on 21.02.16.
 */
case class UserDeletedEvent(override val timestamp:Date, override val userId:Long)
  extends UserEvent(timestamp, userId);
