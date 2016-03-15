package prv.zielony.transaction.analyst.lambda.speed.layer

import java.time.LocalDateTime

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.model._

/**
 * Created by zielony on 25.02.16.
 */
package object services {

  val countAccountsByCountry:(String => Long) =
    AccountService.countAccountsByCountry(dao.findAccountsByCountry _)_

  val capitalVariation:((Option[LocalDateTime], Option[LocalDateTime]) => Double) =
    CashOperationsService.capitalVariation(dao.findCashOperations _)_

  val averageUserAge:(() => Double) = UsersService.averageUserAge(dao.findAllClients _)_

  val medianUserAge = UsersService.medianUserAge(dao.findAllClients _)_

  val averageInsertion:((Option[LocalDateTime], Option[LocalDateTime]) => Double) =
    CashOperationsService.averageInsertion(dao.findCashOperations _)_

  val averageWithdrawal:((Option[LocalDateTime], Option[LocalDateTime]) => Double) =
    CashOperationsService.averageWithdrawal(dao.findCashOperations _)_

  val averageAccountBalancePerUserAge:((Option[Int], Option[Int], String) => Double) =
    AccountService.averageAccountBalancePerUserAge(dao.findAccountsByUserAge _)_
}