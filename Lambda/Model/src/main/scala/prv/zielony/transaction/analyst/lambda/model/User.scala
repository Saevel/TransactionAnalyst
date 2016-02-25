package prv.zielony.transaction.analyst.lambda.model

import java.time.LocalDateTime
import java.util.Date

/**
 * Created by zielony on 15.02.16.
 */
trait User {

  val id:Long;

  val username:String;

  val birthDate:Option[LocalDateTime];
}