package prv.zielony.transaction.analyst.lambda.events.transactions

/**
 * Created by zielony on 14.02.16.
 */
sealed trait TransactionStatus {
  val status:String;
}

object TransactionStatus {

  case object Success extends TransactionStatus {
    override val status: String = "SUCCESS"
  }

  case object Failed extends TransactionStatus {
    override val status: String = "FAILED"
  }
}