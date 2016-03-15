package prv.zielony.transaction.analyst.modern.rest

import prv.zielony.transaction.analyst.modern.events.accounts.{AccountCreatedEvent, AccountRemovedEvent}
import spray.routing.{Route, HttpService}

import prv.zielony.transaction.analyst.modern.services
import prv.zielony.transaction.analyst.modern.services._

import prv.zielony.transaction.analyst.modern.services.accountsService

import scala.concurrent.duration._
import scala.concurrent.Await

/**
 * Created by zielony on 25.02.16.
 */
trait AccountsResource extends HttpService {

  def accountsInterface: Route = {
    path("accounts" / "country" / Segment / "active") { country =>

      val data = Await.result(accountsService.countAllAccountsByCountry(country), 1 minutes)
      complete(data)
    } ~
      path("accounts" / "country" / Segment / "balance") { country =>

        val data = Await.result(accountsService.calculateAverageBalanceByCountry(country), 1 minutes)
        complete(data)
      }
    } ~
    path("accounts") {
      put {
        entity(as[AccountCreatedEvent]) { event =>
          //TODO: create accounts
          complete("DONE")
        }
      }
      delete {
        entity(as[AccountRemovedEvent]) { event =>
          //TODO: Remove accounts
          complete("DONE")
        }
      }
    }
}