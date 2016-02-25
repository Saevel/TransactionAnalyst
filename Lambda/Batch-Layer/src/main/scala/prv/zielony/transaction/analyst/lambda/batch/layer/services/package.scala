package prv.zielony.transaction.analyst.lambda.batch.layer

import java.time.LocalDateTime

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.batch.layer.events._
import prv.zielony.transaction.analyst.lambda.batch.layer.events.accounts.{AccountRemovedEvent, AccountCreatedEvent}
import prv.zielony.transaction.analyst.lambda.batch.layer.events.transactions.{TransferEvent, CashOperationEvent}
import prv.zielony.transaction.analyst.lambda.batch.layer.implicits.Identifiers
import prv.zielony.transaction.analyst.lambda.batch.layer.util.{UsersData, TransactionsData, AccountsData}
import prv.zielony.transaction.analyst.lambda.model._

import Identifiers._
import prv.zielony.transaction.analyst.lambda.model.batch.views.BatchViews

/**
 * Created by zielony on 14.02.16.
 */
package object services {

  import prv.zielony.transaction.analyst.lambda.batch.layer.dao._

  private lazy val allAccounts:RDD[Account] = AccountService.getAllAccounts(
    AccountsData(findAllAccountsCreated, findAllAccountsRemoved),
    TransactionsData(findAllTransfers, findAllCashOperations))

  private[main] lazy val allUsers:RDD[User] = UserService.findActiveUsers(
    UsersData(findAllUsersCreated, findAllUsersRemoved, findAllPersonalDataChanges, findAllContactDataChanges))

  private[main] lazy val allAccountsWithUsers:RDD[Account with User] =
    AccountService.getAllAccountsWithUsers(allAccounts, allUsers)

  private[main] lazy val allSuccessfulCashOperations:RDD[CashOperation] =
    CashOperationsService.getAllSuccessfulCashOperations(findAllCashOperations)

  private[main] val saveUsers:(RDD[User] => Unit) =
    dao.saveAll[User](BatchViews.usersFile)_

  private[main] val saveAccountsWithUsers:(RDD[Account with User] => Unit) =
    dao.saveAll[Account with User](BatchViews.accountsWithUsersFile)_

  private[main] val saveCashOperations:(RDD[CashOperation] => Unit) =
    dao.saveAll[CashOperation](BatchViews.cashOperationsFile)_
}
