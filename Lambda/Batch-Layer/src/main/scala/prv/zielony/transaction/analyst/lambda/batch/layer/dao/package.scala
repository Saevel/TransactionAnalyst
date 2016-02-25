package prv.zielony.transaction.analyst.lambda.batch.layer

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import prv.zielony.proper.model.Bundle
import prv.zielony.proper.path.classpath
import prv.zielony.proper.utils.load
import prv.zielony.transaction.analyst.lambda.batch.layer.dao.MasterDataSet
import prv.zielony.transaction.analyst.lambda.batch.layer.events._
import prv.zielony.transaction.analyst.lambda.batch.layer.events.accounts.{AccountRemovedEvent, AccountCreatedEvent}
import prv.zielony.transaction.analyst.lambda.batch.layer.events.transactions.{TransferEvent, CashOperationEvent}
import prv.zielony.transaction.analyst.lambda.batch.layer.events.users.{ContactDataChangedEvent, PersonalDataChangedEvent, UserDeletedEvent, UserCreatedEvent}

import prv.zielony.proper.inject._
import prv.zielony.proper.default._
import prv.zielony.proper.converters.auto._

import MasterDataSet._

import com.owlike.genson.defaultGenson._

/**
 * Created by zielony on 14.02.16.
 */
package object dao {

  //Move to a separate object and import
  private val sparkBundle:Bundle = load(classpath:/"spark.properties")

  private val sparkConf:SparkConf = new SparkConf()
    .setAppName(%("spark.app.name" @@ sparkBundle))
    .setMaster(%("spark.master" @@ sparkBundle));

  val sparkContext:SparkContext = new SparkContext(sparkConf)

  private def findAll[EventType](fileName:String)(implicit manifest:Manifest[EventType]) = {
    sparkContext.textFile(fileName) map { line =>
      fromJson[EventType](line)
    }
  }

  private[services] lazy val findAllTransfers:(RDD[TransferEvent]) =
    findAll[TransferEvent](transfersFileName)

  private[services] lazy val findAllAccountsCreated:(RDD[AccountCreatedEvent]) =
    findAll[AccountCreatedEvent](accountsCreatedFileName)

  private[services] lazy val findAllAccountsRemoved:(RDD[AccountRemovedEvent]) =
    findAll[AccountRemovedEvent](accountsRemovedFileName)

  private[services] lazy val findAllCashOperations:(RDD[CashOperationEvent]) =
    findAll[CashOperationEvent](cashOperationsFileName)

  private[services] lazy val findAllUsersCreated:(RDD[UserCreatedEvent]) =
    findAll[UserCreatedEvent](usersCreatedFileName)

  private[services] lazy val findAllUsersRemoved:(RDD[UserDeletedEvent]) =
    findAll[UserDeletedEvent](usersRemovedFileName)

  private[services] lazy val findAllPersonalDataChanges:(RDD[PersonalDataChangedEvent]) =
    findAll[PersonalDataChangedEvent](personalDataChangedFileName)

  private[services] lazy val findAllContactDataChanges:(RDD[ContactDataChangedEvent]) =
    findAll[ContactDataChangedEvent](contactDataChangedFileName)

  private[services] def saveAll[Type](file:String)(entities:RDD[Type])(implicit m:Manifest[Type]):Unit = {
    entities.map (toJson[Type](_)).saveAsTextFile(file)
  }
}
