package prv.zielony.transaction.analyst.lambda.batch.layer.services

import java.time.LocalDateTime

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.batch.layer.events.accounts.AccountCreatedEvent
import prv.zielony.transaction.analyst.lambda.batch.layer.events.transactions.TransactionStatus
import prv.zielony.transaction.analyst.lambda.batch.layer.util.{TransactionsData, AccountsData}
import prv.zielony.transaction.analyst.lambda.model.{CashOperationType, User, Currency, Account}

/**
 * Created by zielony on 20.02.16.
 */
object AccountService {

  private[services] def getAllAccounts(accountsData:(=> AccountsData), transactionsData:(=> TransactionsData)):RDD[Account] = {

    val activeAccounts = accountsData.createdEvents keyBy(_.id) subtractByKey (accountsData.removedEvents keyBy(_.id))

    val transfersOut = computeOutgoingTransfers(transactionsData)

    val transfersIn = computeIncomingTransfers(transactionsData)

    val cashOps = computeCashOperations(transactionsData)

    activeAccounts join transfersIn join transfersOut join cashOps map { record =>
      val (id, (((account, incoming), outgoing), cash)) = record
      createAccount(account, incoming, outgoing, cash)
    }
  }

  private def computeOutgoingTransfers(transactionsData: TransactionsData): RDD[(Long, Iterable[Double])] = {
    transactionsData.transfers filter { transfer =>
      (transfer.status == TransactionStatus.Success)
    } keyBy (_.sourceAccountId) mapValues (-_.amount) groupByKey
  }

  private def computeCashOperations(transactionsData: TransactionsData): RDD[(Long, Iterable[Double])] = {
    transactionsData.cashOperations filter { operation =>
      (operation.status == TransactionStatus.Success)
    } keyBy (_.accountId) mapValues { event =>
      if (event.operation == CashOperationType.Insertion) {
        event.amount
      }
      else {
        -event.amount
      }
    } groupByKey
  }

  private def computeIncomingTransfers(transactionsData: TransactionsData): RDD[(Long, Iterable[Double])] = {
    transactionsData.transfers filter { transfer =>
      (transfer.status == TransactionStatus.Success)
    } keyBy (_.targetAccountId) mapValues (_.amount) groupByKey
  }

  private[services] def getAllAccountsWithUsers(accounts:(=> RDD[Account]), users:(=> RDD[User])):RDD[Account with User] = {

    accounts.keyBy(_.ownerId) join (users keyBy(_.id)) map { record =>
      val( id, (account, user)) = record
      createAccountWithUser(account, user)
    }
  }

  private def createAccountWithUser(account:Account, user:User):Account with User = {
    new Account with User {
      override val balance: Double = account.balance
      override val currency: Currency = account.currency
      override val ownerId: Long = account.ownerId
      override val username: String = user.username
      override val birthDate: Option[LocalDateTime] = user.birthDate
      override val id: Long = account.id
    }
  }

  private def createAccount(createdEvent: AccountCreatedEvent, incomingTransfers:Iterable[Double],
                            outgoingTransfers:Iterable[Double], cashOperations:Iterable[Double]):Account = new Account {
    override val currency: Currency = createdEvent.currency
    override val balance: Double = createdEvent.balance + incomingTransfers.sum + outgoingTransfers.sum + cashOperations.sum
    override val id: Long = createdEvent.accountId
    override val ownerId:Long = createdEvent.ownerId
  }
}