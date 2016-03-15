package prv.zielony.transaction.analyst.modern.services

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.modern.dao
import prv.zielony.transaction.analyst.modern.model.UserAccount

import prv.zielony.transaction.analyst.modern.dao._

import scala.concurrent.Future

/**
 * Created by zielony on 28.02.16.
 */
object AccountsService {

  def countAllAccountsByCountry(country:String):Future[Long] = {
    dao.userAccounts.countAll map(_.getOrElse(0))
  }

  def calculateAverageBalanceByCountry(country:String):Future[Double] = {

    val futureBalances = userAccounts.findBalanceByCountry(country)

    futureBalances map { balances =>

      balances.map { balance =>
        balance / balances.length
      } reduce { (first, second) =>
        first + second
      }
    }
  }
}