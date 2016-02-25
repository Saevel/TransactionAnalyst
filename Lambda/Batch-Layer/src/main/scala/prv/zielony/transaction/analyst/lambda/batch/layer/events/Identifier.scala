package prv.zielony.transaction.analyst.lambda.batch.layer.events

/**
 * Created by zielony on 20.02.16.
 */
trait Identifier[T] {
  val id:T;
}
