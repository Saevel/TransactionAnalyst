package prv.zielony.transaction.analyst.lambda.batch.layer.dao

import prv.zielony.proper.inject._
import prv.zielony.proper.converters.auto._
import prv.zielony.proper.default._

/**
 * Created by zielony on 24.02.16.
 */
object MasterDataSet {

  val transfersFileName:String = %("mds.file.transactions")

  val accountsCreatedFileName:String = %("mds.file.accounts.created")

  val cashOperationsFileName:String = %("mds.file.cash.operations")

  val accountsRemovedFileName:String = %("mds.file.accounts.removed")

  val usersCreatedFileName:String = %("mds.file.users.created")

  val usersRemovedFileName:String = %("mds.file.users.removed")

  val personalDataChangedFileName:String = %("mds.file.personal.data.changed")

  val contactDataChangedFileName:String = %("mds.file.contact.data.changed")
}
