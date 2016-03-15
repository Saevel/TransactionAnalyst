package prv.zielony.transaction.analyst.modern.services

import java.time.LocalDateTime
import java.util.Date

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.modern.dao
import prv.zielony.transaction.analyst.modern.model.{CashOperationType, CashOperation}

import prv.zielony.transaction.analyst.modern.dao._

import scala.concurrent.Future

/**
 * Created by zielony on 28.02.16.
 */
object CapitalService {

  def calculateCapitalVarianceInPeriod(from:Option[Date], to:Option[Date]):Future[Double] = {

    val insertionSum = this.calculateCashOperationsAverageInPeriod(CashOperationType.Insertion, from, to)

    val withdrawalSum = this.calculateCashOperationsAverageInPeriod(CashOperationType.Withdrawal, from, to)

    for{
      insertion <- insertionSum
      withdrawal <- withdrawalSum
    } yield insertion - withdrawal
  }

  val calculateInsertionsAverageInPeriod:((Option[Date],Option[Date]) => Future[Double]) =
    this.calculateCashOperationsAverageInPeriod(CashOperationType.Insertion, _, _)

  val calculateWithdrawalsAverageInPeriod:((Option[Date], Option[Date]) => Future[Double]) =
    this.calculateCashOperationsAverageInPeriod(CashOperationType.Withdrawal, _, _)

  private def calculateCashOperationsAverageInPeriod(op:CashOperationType, from:Option[Date], to:Option[Date]):Future[Double] = {
    cashOperations findAllCashOperationsByTypeAndPeriod(op, from, to) map { list =>
      list reduce(_+_)
    }
  }
}