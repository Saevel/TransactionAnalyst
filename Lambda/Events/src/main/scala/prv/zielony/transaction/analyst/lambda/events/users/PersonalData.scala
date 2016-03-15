package prv.zielony.transaction.analyst.lambda.events.users

import java.time.LocalDateTime

/**
 * Created by zielony on 20.02.16.
 */
case class PersonalData(val name:String, val surname:String, val birthDate:Option[LocalDateTime] = None,
                        val country:Option[String] = None);
