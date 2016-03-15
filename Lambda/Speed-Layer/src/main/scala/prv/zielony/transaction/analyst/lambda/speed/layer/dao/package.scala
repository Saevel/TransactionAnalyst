package prv.zielony.transaction.analyst.lambda.speed.layer

import java.time.LocalDateTime

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}

import prv.zielony.proper.inject._
import prv.zielony.proper.converters.auto._
import prv.zielony.proper.default._
import prv.zielony.proper.path.classpath
import prv.zielony.proper.utils.load
import prv.zielony.transaction.analyst.lambda.model.{Country, User, Account, CashOperation}
import prv.zielony.transaction.analyst.lambda.model.batch.views.BatchViews

import com.owlike.genson.defaultGenson._

/**
 * Created by zielony on 24.02.16.
 */
package object dao {

  private val sparkBundle = load(classpath:/"spark.properties")

  private val sparkConf:SparkConf = new SparkConf()
    .setMaster(%("spark.master" @@ sparkBundle))
    .setAppName(%("spark.app.name" @@ sparkBundle))

  val sparkContext:SparkContext = new SparkContext(sparkConf)

  def findCashOperations(from:Option[LocalDateTime], to:Option[LocalDateTime]): RDD[CashOperation] = {

    val allOperations = sparkContext.textFile(BatchViews.cashOperationsFile).map { line =>
        fromJson[CashOperation](line)
    }

    if(from.isDefined && to.isDefined) {
      allOperations.filter { operation =>
        (operation.timestamp isAfter from.get) && (operation.timestamp isBefore to.get)
      }
    }
    else if(from.isDefined) {
      allOperations filter(_.timestamp isAfter from.get)
    }
    else if(to.isDefined) {
      allOperations filter(_.timestamp isBefore to.get)
    }
    else {
      allOperations
    }
  }

  def findAllClients():RDD[User] = {
    sparkContext.textFile(BatchViews.usersFile) map { line =>
      fromJson[User](line)
    }
  }

  def findAccountsByCountry(name:String):RDD[Account with User with Country] = {

    sparkContext.textFile(BatchViews.accountsWithUsersFile) map { line =>
      fromJson[Account with User with Country](line)
    } filter { account =>
      account.country == name
    }
  }

  def findAccountsByUserAge(minAge:Option[Int], maxAge:Option[Int]):RDD[Account with User with Country] = {

    val allAccounts = sparkContext.textFile(BatchViews.accountsWithUsersFile) map { line =>
      fromJson[Account with User with Country](line)
    } filter (_.birthDate.isDefined)

    if(minAge.isEmpty && maxAge.isEmpty){
     allAccounts
    }
    else {
      val now = LocalDateTime.now();
      allAccounts filter { account =>

        val age = now.getYear - account.birthDate.get.getYear
        (minAge.isDefined && age > minAge.get) || (maxAge.isDefined && age < maxAge.get)
      }
    }
  }

  //TODO: Maybe save stuff in batches?
  private def save[EventType](context:SparkContext, file:String)(event:EventType)(implicit m:Manifest[EventType]):Unit = {
    context.parallelize(Seq(toJson[EventType](event))).saveAsTextFile(file)
  }

  val save
}