package prv.zielony.transaction.analyst.lambda.batch.layer

import org.apache.spark.sql.{Dataset, SQLContext}
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import prv.zielony.proper.model.Bundle
import prv.zielony.proper.path.classpath
import prv.zielony.proper.utils.load
import prv.zielony.transaction.analyst.lambda.batch.layer.dao.MasterDataSet

import prv.zielony.proper.inject._
import prv.zielony.proper.default._
import prv.zielony.proper.converters.auto._

import MasterDataSet._

import com.owlike.genson.defaultGenson._
import prv.zielony.transaction.analyst.lambda.events.accounts.{AccountRemovedEvent, AccountCreatedEvent}
import prv.zielony.transaction.analyst.lambda.events.transactions.{CashOperationEvent, TransferEvent}
import prv.zielony.transaction.analyst.lambda.events.users.{UserCreatedEvent, UserDeletedEvent, PersonalDataChangedEvent, ContactDataChangedEvent}

/**
 * Created by zielony on 14.02.16.
 */
package object dao {

  import prv.zielony.transaction.analyst.lambda.batch.layer.spark._

  private[dao] def findAll[EventType](sqlContext:SQLContext)(fileName:String)(implicit m:Manifest[EventType]) = {

    sqlContext.sparkContext.textFile(fileName) map { line =>
      fromJson[EventType](line)
    }
  }

  lazy val allTransfers:(RDD[TransferEvent]) =
    findAll[TransferEvent](sqlContext)(transfersFileName)

  lazy val allAccountsCreated:(RDD[AccountCreatedEvent]) =
    findAll[AccountCreatedEvent](sqlContext)(accountsCreatedFileName)

  lazy val allAccountsRemoved:(RDD[AccountRemovedEvent]) =
    findAll[AccountRemovedEvent](sqlContext)(accountsRemovedFileName)

  lazy val allCashOperations:(RDD[CashOperationEvent]) =
    findAll[CashOperationEvent](sqlContext)(cashOperationsFileName)

  lazy val allUsersCreated:(RDD[UserCreatedEvent]) =
    findAll[UserCreatedEvent](sqlContext)(usersCreatedFileName)

  lazy val allUsersRemoved:(RDD[UserDeletedEvent]) =
    findAll[UserDeletedEvent](sqlContext)(usersRemovedFileName)

  lazy val allPersonalDataChanges:(RDD[PersonalDataChangedEvent]) =
    findAll[PersonalDataChangedEvent](sqlContext)(personalDataChangedFileName)

  lazy val allContactDataChanges:(RDD[ContactDataChangedEvent]) =
    findAll[ContactDataChangedEvent](sqlContext)(contactDataChangedFileName)

  def saveAll[Type](file:String)(entities:RDD[Type])(implicit m:Manifest[Type]):Unit = {
    entities.map(toJson[Type](_)) saveAsTextFile(file)
  }

  def saveStream[Type](file:String)(entities:DStream[Type])(implicit m:Manifest[Type]):Unit = {
    (entities map(toJson[Type](_))).foreachRDD(_.saveAsTextFile(file))
  }
}