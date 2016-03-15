package prv.zielony.transaction.analyst.modern.dao

import java.util.Date

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.Connector
import com.websudos.phantom.dsl._
import prv.zielony.transaction.analyst.modern.model.{CashOperationType, CashOperation}

import scala.concurrent.Future

/**
 * Created by zielony on 06.03.16.
 */
class CashOperationsDao extends CassandraTable[AbstractCashOperationsDao, CashOperation] {

  object accountId extends LongColumn(this) with PartitionKey[Long]

  object amount extends DoubleColumn(this)

  object operation extends StringColumn(this) with Index[String]

  object timestamp extends DateColumn(this) with Index[Date]

  override def fromRow(row: Row): CashOperation = {
     CashOperation(accountId(row), amount(row), CashOperationType(operation(row)), timestamp(row))
  }

  //TODO: findAllBy Type and period
}

abstract class AbstractCashOperationsDao extends CashOperationsDao with RootConnector {

  def findAllCashOperationsByTypeAndPeriod(operation:CashOperationType, startDate:Option[Date],
                                           endDate:Option[Date]):Future[List[Double]] = {

    val partial = select(_.amount) where(_.operation eqs operation.name);

    val conditional = (startDate, endDate) match {

      case (Some(start), Some(end)) => partial and(_.timestamp gte start) and(_.timestamp lte end)
      case (Some(start), None) => partial and(_.timestamp gte start)
      case (None, Some(end)) => partial and(_.timestamp lte end)
      case _ => partial
    }

    conditional fetch
  }

  def persistCashOperation(operation:CashOperation):Future[ResultSet] = {

    insert()
      .value(_.accountId, operation.accountId)
      .value(_.amount, operation.amount)
      .value(_.operation, operation.operation.name)
      .value(_.timestamp, operation.timestamp)
      .future
  }
}