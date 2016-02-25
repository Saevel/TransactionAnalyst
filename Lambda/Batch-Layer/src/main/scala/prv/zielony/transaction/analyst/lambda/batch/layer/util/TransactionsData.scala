package prv.zielony.transaction.analyst.lambda.batch.layer.util

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.batch.layer.events.transactions.{CashOperationEvent, TransferEvent}

/**
 * Created by zielony on 21.02.16.
 */
case class TransactionsData(val transfers:RDD[TransferEvent], val cashOperations:RDD[CashOperationEvent]);
