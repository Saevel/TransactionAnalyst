package prv.zielony.transaction.analyst.lambda.model

/**
 * Created by zielony on 14.02.16.
 */
trait Account {
  val id:Long;

  val balance:Double;

  val currency:Currency;

  val ownerId:Long
}
