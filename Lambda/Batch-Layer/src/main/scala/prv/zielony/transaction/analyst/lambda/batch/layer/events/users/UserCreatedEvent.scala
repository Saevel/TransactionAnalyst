package prv.zielony.transaction.analyst.lambda.batch.layer.events.users

import java.time.LocalDateTime

/**
 * Created by zielony on 21.02.16.
 */
case class UserCreatedEvent(override val timestamp:LocalDateTime, override val userId:Long, val username:String,
                            val personalData: PersonalData, val contactData: Option[ContactData]) extends UserEvent(timestamp, userId);
