package prv.zielony.transaction.analyst.modern.events.users

import java.util.Date

/**
 * Created by zielony on 21.02.16.
 */
case class PersonalDataChangedEvent(override val timestamp:Date, override val userId:Long,
                                    val newData:PersonalData) extends UserEvent(timestamp, userId);
