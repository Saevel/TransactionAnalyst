package prv.zielony.transaction.analyst.lambda.batch.layer.main

import kafka.consumer.Consumer
import kafka.serializer.Decoder
import org.apache.kafka.clients.consumer.Consumer
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Minutes, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.{SparkConf, SparkContext}
import prv.zielony.transaction.analyst.lambda.batch.layer.{services, connectors}
import prv.zielony.transaction.analyst.lambda.batch.layer.dao._
import prv.zielony.transaction.analyst.lambda.batch.layer.dao.sparkContext
import prv.zielony.transaction.analyst.lambda.batch.layer.services._
import prv.zielony.transaction.analyst.lambda.batch.layer.connectors._

import prv.zielony.proper.converters.auto._
import prv.zielony.proper.inject._
import prv.zielony.proper.default._

/**
 * Created by zielony on 21.02.16.
 */
object Main extends App {
  
  ingestData

  generateBatchViews

  private def generateBatchViews:Unit = {

    saveUsers(allUsers)

    saveAccountsWithUsers(allAccountsWithUsers)

    saveCashOperations(allSuccessfulCashOperations)
  }

  private def ingestData:Unit = {

    ingestAccountsCreated(accountsRecentlyCreated)

    ingestAccountsRemoved(accountsRecentlyRemoved)

    ingestUsersCreated(usersRecentlyCreated)

    ingestUsersRemoved(usersRecentlyRemoved)

    ingestTransfers(recentTransfers)

    ingestCashOperations(recentCashOperations)

    ingestPersonalDataChanges(recentPersonalDataChanges)

    ingestContactDataChanges(recentContactDataChanges)
  }
}