package prv.zielony.transaction.analyst.lambda.speed.layer.rest

import prv.zielony.transaction.analyst.lambda.events.accounts.{AccountRemovedEvent, AccountCreatedEvent}
import prv.zielony.transaction.analyst.lambda.speed.layer.services
import prv.zielony.transaction.analyst.lambda.speed.layer.services._
import spray.routing.{Route, HttpService}

import spray.httpx.SprayJsonSupport._

/**
 * Created by zielony on 25.02.16.
 */
trait AccountsResource extends HttpService {

  def accountsInterface:Route = {
    path("accounts" / "country" / Segment / "active") { country =>
      complete(services.countAccountsByCountry(country).toString())
    } ~
      path("accounts" / "country" / Segment / "balance") { country =>

        parameters("minAge".?.as[Option[Int]], "maxAge".?.as[Option[Int]]) { (minAge, maxAge) =>
          complete(averageAccountBalancePerUserAge(minAge, maxAge, country).toString())
        }
      } ~
      path("accounts") {
        put {
          entity(as[AccountCreatedEvent]) { event =>
            //TODO: Save event
            complete("DONE")
          }
        }
        delete {
          entity(as[AccountRemovedEvent]) { event =>
            //TODO: Remove account
            complete("DONE")
          }
        }
      }
  }
}
