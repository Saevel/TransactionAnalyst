package prv.zielony.transaction.analyst.lambda.model

import java.time.LocalDateTime

/**
 * Created by zielony on 21.02.16.
 */
trait CashOperation {

  val accountId:Long;

  val currency:Currency;

  val amount:Double;

  val operation:CashOperationType;

  val timestamp:LocalDateTime;
}
