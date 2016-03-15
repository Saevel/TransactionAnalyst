package prv.zielony.transaction.analyst.lambda.events.users

import java.time.LocalDateTime

/**
 * Created by zielony on 21.02.16.
 */
case class PersonalDataChangedEvent(override val timestamp:LocalDateTime, override val userId:Long,
                                    val newData:PersonalData) extends UserEvent(timestamp, userId);
