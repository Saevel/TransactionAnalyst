package prv.zielony.transaction.analyst.modern

import com.websudos.phantom.connectors.{ContactPoints, ContactPoint}
import prv.zielony.proper.path.classpath
import prv.zielony.proper.utils.load
import prv.zielony.proper.inject._
import prv.zielony.proper.converters.auto._

/**
 * Created by zielony on 06.03.16.
 */
package object dao {

  implicit val cassandraBundle = load(classpath:/"cassandra.properties")

  val keySpace = ContactPoints(Seq[String](%("cassandra.hosts"))).keySpace(%("cassandra.keyspace"))

  object userAccounts extends AbstractAccountsDao with keySpace.Connector;

  object users extends AbstractUsersDao with keySpace.Connector

  object cashOperations extends AbstractCashOperationsDao with keySpace.Connector
}
