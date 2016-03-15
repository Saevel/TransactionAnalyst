package prv.zielony.transaction.analyst.modern.model

/**
 * Created by zielony on 14.02.16.
 */
case class UserAccount(val id:Long, val balance:Double, val userId:Long, val userCountry:Option[String]);
