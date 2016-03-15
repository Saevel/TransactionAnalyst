package prv.zielony.transaction.analyst.lambda.batch.layer.implicits

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.events.Identifier

/**
 * Created by zielony on 20.02.16.
 */
object Identifiers {

  implicit def keyByIdenifier[IdentifierType, DataType <: Identifier[IdentifierType]](input:RDD[DataType]):RDD[(IdentifierType, DataType)] = {
    input.keyBy { item =>
      item.id
    }
  }
}
