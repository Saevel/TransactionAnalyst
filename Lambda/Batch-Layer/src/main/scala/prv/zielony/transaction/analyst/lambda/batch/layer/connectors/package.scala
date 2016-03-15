package prv.zielony.transaction.analyst.lambda.batch.layer

import _root_.kafka.serializer.Decoder
import kafka.serializer.Decoder
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka.KafkaUtils

import com.owlike.genson.defaultGenson._
import prv.zielony.proper.path.classpath
import prv.zielony.proper.utils.load

import prv.zielony.proper.converters.auto._
import prv.zielony.proper.inject._
import prv.zielony.transaction.analyst.lambda.events.accounts.{AccountRemovedEvent, AccountCreatedEvent}
import prv.zielony.transaction.analyst.lambda.events.transactions.{CashOperationEvent, TransferEvent}
import prv.zielony.transaction.analyst.lambda.events.users.{ContactDataChangedEvent, PersonalDataChangedEvent, UserDeletedEvent, UserCreatedEvent}

/**
 * Created by zielony on 14.03.16.
 */
package object connectors {

  import prv.zielony.transaction.analyst.lambda.batch.layer.spark._

  import prv.zielony.transaction.analyst.lambda.batch.layer.kafka._
  
  private def consume[EventType](context:StreamingContext, kafkaParams:Map[String, String],
                                          topicName:String)(implicit m:Manifest[EventType]):DStream[EventType] = {

    KafkaUtils.createStream[String, String, Decoder[String], Decoder[String]](context, kafkaParams,
      Map(topicName -> 1), StorageLevel.DISK_ONLY).map { pair =>

      val (key, value) = pair
      fromJson[EventType](value)
    }
  }

  lazy val accountsRecentlyCreated = consume[AccountCreatedEvent](streamingstreamingContext, kafkaProperties, Topics.accountsCreated)

  lazy val accountsRecentlyRemoved = consume[AccountRemovedEvent](streamingContext, kafkaProperties, Topics.accountsRemoved)

  lazy val usersRecentlyCreated = consume[UserCreatedEvent](streamingContext, kafkaProperties, Topics.usersCreated)

  lazy val usersRecentlyRemoved = consume[UserDeletedEvent](streamingContext, kafkaProperties, Topics.usersRemoved)

  lazy val recentTransfers = consume[TransferEvent](streamingContext, kafkaProperties, Topics.transfers)

  lazy val recentCashOperations = consume[CashOperationEvent](streamingContext, kafkaProperties, Topics.cashOperations)

  lazy val recentPersonalDataChanges = consume[PersonalDataChangedEvent](streamingContext, kafkaProperties, Topics.personalDataChanged)

  lazy val recentContactDataChanges = consume[ContactDataChangedEvent](streamingContext, kafkaProperties, Topics.contactDataChanged)
}