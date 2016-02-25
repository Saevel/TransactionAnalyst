package prv.zielony.transaction.analyst.lambda.speed.layer

import java.time.LocalDateTime

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.model._

/**
 * Created by zielony on 25.02.16.
 */
package object services {

  //TODO: Modularize!

  private def countAccountsByCountry(findAccounts:(String => RDD[Account with User with Country]))(country:String):Long = {
    findAccounts(country) count
  }

  private[rest] val countAccountsByCountry:(String => Long) = countAccountsByCountry(dao.findAccountsByCountry _)_

  private def capitalVariation(findOps:((Option[LocalDateTime], Option[LocalDateTime]) => RDD[CashOperation]))
                              (from:Option[LocalDateTime], to:Option[LocalDateTime]):Double = {
    findOps(from, to) map (_.amount) reduce (_+_)
  }

  private[rest] val capitalVariation:((Option[LocalDateTime], Option[LocalDateTime]) => Double) =
    capitalVariation(dao.findCashOperations _)_

  private def averageUserAge(findClients:() => RDD[User])():Double = {
    val now = new LocalDateTime()
    findClients() filter { user =>
      user.birthDate.isDefined
    } map { user =>
      (now.getYear - user.birthDate.get.getYear)
    } mean
  }

  private[rest] val averageUserAge:(() => Double) = averageUserAge(dao.findAllClients _)_

  private def medianUserAge(findClients:(() => RDD[User]))():Double = {
    val now = new LocalDateTime()
    val years:RDD[Int] = findClients() filter { user =>
      user.birthDate.isDefined
    } map { user =>
      (now.getYear - user.birthDate.get.getYear)
    } sortBy { years =>
      years
    }

    //TODO: MEDIAN!

    ???
  }

  private[rest] val medianUserAge = averageUserAge(dao.findAllClients _)_

  private def averageInsertion(getOps:((Option[LocalDateTime], Option[LocalDateTime]) => RDD[CashOperation]))
                              (from:Option[LocalDateTime], to:Option[LocalDateTime]) = {

    getOps(from, to) filter { op =>
      op.operation == CashOperationType.Insertion
    } map { op =>
      op.amount
    } mean
  }

  private[rest] val averageInsertion:((Option[LocalDateTime], Option[LocalDateTime]) => Double) =
    averageInsertion(dao.findCashOperations _)_

  private def averageWithdrawal(getOps:((Option[LocalDateTime], Option[LocalDateTime]) => RDD[CashOperation]))
                               (from:Option[LocalDateTime], to:Option[LocalDateTime]) = {
    getOps(from, to) filter { op =>
      op.operation == CashOperationType.Withdrawal
    } map { op =>
      op.amount
    } mean
  }

  private[rest] val averageWithdrawal:((Option[LocalDateTime], Option[LocalDateTime]) => Double) =
    averageWithdrawal(dao.findCashOperations _)_

  private def averageAccountBalancePerUserAge(findAccounts:((Option[Int], Option[Int]) => RDD[Account with User with Country]))
                                             (minAge:Option[Int], maxAge:Option[Int], country:String) = {

    findAccounts(minAge, maxAge) filter { account =>
      account.country == country
    } map (_.balance) mean
  }

  private[rest] val averageAccountBalancePerUserAge:((Option[Int], Option[Int], String) => Double) =
    averageAccountBalancePerUserAge(dao.findAccountsByUserAge _)_

}
