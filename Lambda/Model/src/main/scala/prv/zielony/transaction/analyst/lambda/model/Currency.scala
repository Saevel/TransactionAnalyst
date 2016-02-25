package prv.zielony.transaction.analyst.lambda.model

/**
 * Created by zielony on 14.02.16.
 */
sealed trait Currency {
  val code:String;
}

object Currency {

  case object PLN extends Currency {
    override val code: String = "PLN"
  }

  case object USD extends Currency {
    override val code: String = "USD"
  }

  case object EUR extends Currency {
    override val code: String = "EUR"
  }
}
