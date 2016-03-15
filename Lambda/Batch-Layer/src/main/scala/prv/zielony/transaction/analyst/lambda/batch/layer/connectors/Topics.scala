package prv.zielony.transaction.analyst.lambda.batch.layer.connectors

import prv.zielony.proper.inject._
import prv.zielony.proper.converters.auto._
import prv.zielony.proper.path.classpath
import prv.zielony.proper.utils.load

/**
 * Created by zielony on 14.03.16.
 */
object Topics {

  implicit val kafkaBundle = load(classpath:/"kafka.properties")

  val accountsCreated = %("kafka.topic.accounts.created")

  val accountsRemoved = %("kafka.topic.accounts.removed")

  val usersCreated = %("kafka.topic.users.created")

  val usersRemoved = %("kafka.topic.users.removed")

  val cashOperations = %("kafka.topic.cash.operations")

  val transfers = %("kafka.topic.transfers")

  val personalDataChanged = %("kafka.topic.personal.data.changed")

  val contactDataChanged = %("kafka.topic.contact.data.changed")
}
