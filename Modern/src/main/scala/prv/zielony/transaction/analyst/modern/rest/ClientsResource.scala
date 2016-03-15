package prv.zielony.transaction.analyst.modern.rest

import prv.zielony.transaction.analyst.modern.services
import prv.zielony.transaction.analyst.modern.services._

import spray.routing.{Route, HttpService}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by zielony on 25.02.16.
 */
trait ClientsResource extends HttpService{

  def clientsInterface:Route = {
    path("clients" / "age" / "median") {
      get {

        val data = Await.result(clientsService.calclulateClientsAgeMedian, 1 minutes)
        complete(data)
      }
    } ~
      path("clients" / "age" / "average") {
        get {

          val data = Await.result(clientsService.calculateAverageClientAge, 1 minutes)
          complete(data)
        }
      }
  }
}
