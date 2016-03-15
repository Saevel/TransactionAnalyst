package prv.zielony.transaction.analyst.lambda.events.users

import java.time.LocalDateTime

/**
 * Created by zielony on 21.02.16.
 */
case class ContactDataChangedEvent(override val timestamp:LocalDateTime, override val userId:Long,
                                    val newData:ContactData) extends UserEvent(timestamp, userId);
