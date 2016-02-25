package prv.zielony.transaction.analyst.lambda.batch.layer.util

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.batch.layer.events._
import prv.zielony.transaction.analyst.lambda.batch.layer.events.accounts.{AccountRemovedEvent, AccountCreatedEvent}
import prv.zielony.transaction.analyst.lambda.batch.layer.events.transactions.{TransferEvent, CashOperationEvent}

/**
 * Created by zielony on 20.02.16.
 */
case class AccountsData(val createdEvents:RDD[AccountCreatedEvent], val removedEvents:RDD[AccountRemovedEvent]);