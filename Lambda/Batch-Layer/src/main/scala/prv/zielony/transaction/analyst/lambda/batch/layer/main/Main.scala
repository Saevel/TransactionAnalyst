package prv.zielony.transaction.analyst.lambda.batch.layer.main

import org.apache.spark.{SparkConf, SparkContext}
import prv.zielony.transaction.analyst.lambda.batch.layer.dao.sparkContext
import prv.zielony.transaction.analyst.lambda.batch.layer.services._

/**
 * Created by zielony on 21.02.16.
 */
object Main extends App {

  //TODO: Handle Kafka

  saveUsers(allUsers)

  saveAccountsWithUsers(allAccountsWithUsers)

  saveCashOperations(allSuccessfulCashOperations)
}
