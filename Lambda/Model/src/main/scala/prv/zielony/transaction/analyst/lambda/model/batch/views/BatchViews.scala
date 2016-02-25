package prv.zielony.transaction.analyst.lambda.model.batch.views

import prv.zielony.proper.inject._
import prv.zielony.proper.default._
import prv.zielony.proper.converters.auto._

/**
 * Created by zielony on 24.02.16.
 */
object BatchViews {

  val accountsWithUsersFile:String = %("batch.views.file.accounts")

  val cashOperationsFile:String = %("batch.views.file.cash.operations")

  val usersFile:String = %("batch.views.file.users")
}
