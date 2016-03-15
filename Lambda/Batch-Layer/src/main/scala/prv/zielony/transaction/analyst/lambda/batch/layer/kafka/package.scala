package prv.zielony.transaction.analyst.lambda.batch.layer

import prv.zielony.proper.inject._
import prv.zielony.proper.converters.auto._
import prv.zielony.proper.path.classpath
import prv.zielony.proper.utils.load

/**
 * Created by zielony on 15.03.16.
 */
package object kafka {

  implicit val kafkaBundle = load(classpath:/"kafka.properties")

  private val kafkaBrokerList:String = %("kafka.broker.list")

  private val kafkaZookeperConnectors:String = %("kafka.zoookeeper.connect")

  val kafkaProperties:Map[String, String] = Map(
    "broker.list" -> kafkaBrokerList,
    "zookeeper.conntect" -> kafkaZookeperConnectors
  )
}
