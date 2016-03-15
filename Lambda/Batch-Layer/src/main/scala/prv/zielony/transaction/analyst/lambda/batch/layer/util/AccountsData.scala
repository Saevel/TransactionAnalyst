package prv.zielony.transaction.analyst.lambda.batch.layer.util

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.events.accounts.{AccountRemovedEvent, AccountCreatedEvent}

/**
 * Created by zielony on 20.02.16.
 */
case class AccountsData(val createdEvents:RDD[AccountCreatedEvent], val removedEvents:RDD[AccountRemovedEvent]);