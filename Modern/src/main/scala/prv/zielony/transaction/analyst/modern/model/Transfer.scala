package prv.zielony.transaction.analyst.modern.model

/**
 * Created by zielony on 15.02.16.
 */
case class Transfer(val id:Long, val sourceAccountId:Long, val targetAccountId:Long, val amount:Double);
