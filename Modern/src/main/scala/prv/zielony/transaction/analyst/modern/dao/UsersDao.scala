package prv.zielony.transaction.analyst.modern.dao

import java.util.Date

import com.datastax.driver.core.Row
import com.websudos.phantom.column.DateColumn
import com.websudos.phantom.keys.PartitionKey
import prv.zielony.transaction.analyst.modern.model.User

import com.websudos.phantom.dsl._

import scala.concurrent.Future

/**
 * Created by zielony on 06.03.16.
 */
class UsersDao extends CassandraTable[AbstractUsersDao, User]{

  object id extends LongColumn(this) with PartitionKey[Long]

  object birthDate extends OptionalDateColumn(this)

  override def fromRow(row: Row): User = {
   User(id(row), birthDate(row))
  }
}

abstract class AbstractUsersDao extends UsersDao with RootConnector {
  def findAll():Future[List[Option[Date]]] = {
    select(_.birthDate).fetch
  }

  def persistUser(user:User):Future[ResultSet] = {

    insert()
      .value(_.id, user.id)
      .value(_.birthDate, user.birthDate)
      .future
  }
}
