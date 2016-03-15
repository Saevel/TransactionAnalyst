package prv.zielony.transaction.analyst.modern.dao

import com.datastax.driver.core.Row
import com.websudos.phantom.dsl._
import prv.zielony.transaction.analyst.modern.model.UserAccount

import scala.concurrent.Future

/**
 * Created by zielony on 06.03.16.
 */
class AccountsDao extends CassandraTable[AbstractAccountsDao, UserAccount] {

  object id extends LongColumn(this) with PartitionKey[Long]

  object balance extends DoubleColumn(this)

  object userId extends LongColumn(this) with Index[Long]

  object userCountry extends OptionalStringColumn(this) with Index[Option[String]]

  override def fromRow(row: Row): UserAccount = {
    UserAccount(id(row), balance(row), userId(row), userCountry(row))
  }
}

abstract class AbstractAccountsDao extends AccountsDao with RootConnector {

  def findBalanceByCountry(country:String):Future[Seq[Double]] = {
    select(_.balance) where(_.userCountry eqs country) fetch
  }

  def countAll():Future[Option[Long]] = {
    select.count().one()
  }

  def persistUserAccount(account:UserAccount):Future[ResultSet] = {
    insert() 
      .value(_.id, account.id) 
      .value(_.balance, account.balance) 
      .value(_.userId, account.userId)
      .value(_.userCountry, account.userCountry)
      .future
  }
}
