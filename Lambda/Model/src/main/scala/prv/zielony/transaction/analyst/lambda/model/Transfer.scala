package prv.zielony.transaction.analyst.lambda.model

/**
 * Created by zielony on 15.02.16.
 */
trait Transfer {

  val id:Long;

  val sourceAccount:Account;

  val targetAccount:Account;

  val amount:Double;

  val currency:Currency;
}
