package prv.zielony.transaction.analyst.modern.model

/**
 * Created by zielony on 14.02.16.
 */
sealed trait CashOperationType {
  val name:String;
}

object CashOperationType extends (String => CashOperationType){

  object Withdrawal extends CashOperationType {
    override val name: String = "WITHDRAWAL"
  }

  object Insertion extends CashOperationType {
    override val name: String = "INSERTION"
  }

  override def apply(name: String): CashOperationType = {
    if(name == Withdrawal.name) {
      return Withdrawal
    }
    else if(name == Insertion.name) {
      return Insertion
    }
    else {
      throw new IllegalArgumentException("Unsupported value")
    }
  }
}
