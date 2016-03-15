package prv.zielony.transaction.analyst.lambda.batch.layer

import java.time.LocalDateTime

import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream
import prv.zielony.transaction.analyst.lambda.batch.layer.implicits.Identifiers
import prv.zielony.transaction.analyst.lambda.batch.layer.util.{UsersData, TransactionsData, AccountsData}
import prv.zielony.transaction.analyst.lambda.events.accounts.{AccountRemovedEvent, AccountCreatedEvent}
import prv.zielony.transaction.analyst.lambda.events.transactions.{TransferEvent, CashOperationEvent}
import prv.zielony.transaction.analyst.lambda.events.users.{ContactDataChangedEvent, PersonalDataChangedEvent, UserDeletedEvent, UserCreatedEvent}
import prv.zielony.transaction.analyst.lambda.model._

import Identifiers._
import prv.zielony.transaction.analyst.lambda.model.batch.views.BatchViews

/**
 * Created by zielony on 14.02.16.
 */
package object services {

  import prv.zielony.transaction.analyst.lambda.batch.layer.dao._

  private lazy val allAccounts:RDD[Account] = AccountService.getAllAccounts(
    AccountsData(allAccountsCreated, allAccountsRemoved),
    TransactionsData(allTransfers, allCashOperations))

  lazy val allUsers:RDD[User] = UserService.findActiveUsers(
    UsersData(allUsersCreated, allUsersRemoved, allPersonalDataChanges, allContactDataChanges))

  lazy val allAccountsWithUsers:RDD[Account with User] =
    AccountService.getAllAccountsWithUsers(allAccounts, allUsers)

  lazy val allSuccessfulCashOperations:RDD[CashOperation] =
    CashOperationsService.getAllSuccessfulCashOperations(allCashOperations)

  //TODO: Modularize
  
  val saveUsers:(RDD[User] => Unit) =
    dao.saveAll[User](BatchViews.usersFile)_

  val saveAccountsWithUsers:(RDD[Account with User] => Unit) =
    dao.saveAll[Account with User](BatchViews.accountsWithUsersFile)_

  val saveCashOperations:(RDD[CashOperation] => Unit) =
    dao.saveAll[CashOperation](BatchViews.cashOperationsFile)_
  
  val ingestUsersCreated:(DStream[UserCreatedEvent] => Unit) =
    dao.saveStream[UserCreatedEvent](MasterDataSet.usersCreatedFileName)_
  
  val ingestUsersRemoved:(DStream[UserDeletedEvent] => Unit) =
    dao.saveStream[UserDeletedEvent](MasterDataSet.usersRemovedFileName)_
  
  val ingestAccountsCreated:(DStream[AccountCreatedEvent] => Unit) =
    dao.saveStream[AccountCreatedEvent](MasterDataSet.accountsCreatedFileName)_
  
  val ingestAccountsRemoved:(DStream[AccountRemovedEvent] => Unit) =
    dao.saveStream[AccountRemovedEvent](MasterDataSet.accountsRemovedFileName)_
  
  val ingestCashOperations:(DStream[CashOperationEvent] => Unit) =
    dao.saveStream[CashOperationEvent](MasterDataSet.cashOperationsFileName)_
  
  val ingestTransfers:(DStream[TransferEvent] => Unit) =
    dao.saveStream[TransferEvent](MasterDataSet.transfersFileName)_
  
  val ingestPersonalDataChanges:(DStream[PersonalDataChangedEvent] => Unit) =
    dao.saveStream[PersonalDataChangedEvent](MasterDataSet.personalDataChangedFileName)_
  
  val ingestContactDataChanges:(DStream[ContactDataChangedEvent] => Unit) =
    dao.saveStream[ContactDataChangedEvent](MasterDataSet.contactDataChangedFileName)_
}