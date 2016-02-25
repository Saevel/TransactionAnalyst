package prv.zielony.transaction.analyst.lambda.model

/**
 * Created by zielony on 14.02.16.
 */
sealed trait CashOperationType {
  val name:String;
}

object CashOperationType {

  object Withdrawal extends CashOperationType {
    override val name: String = "WITHDRAWAL"
  }

  object Insertion extends CashOperationType {
    override val name: String = "INSERTION"
  }
}
