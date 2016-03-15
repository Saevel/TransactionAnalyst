package prv.zielony.transaction.analyst.modern.events.users

import java.util.Date

/**
 * Created by zielony on 21.02.16.
 */
case class UserCreatedEvent(override val timestamp:Date, override val userId:Long, val username:String,
                            val personalData: PersonalData, val contactData: Option[ContactData])
                            extends UserEvent(timestamp, userId);
