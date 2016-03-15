package prv.zielony.transaction.analyst.lambda.speed.layer.rest

import java.time.LocalDateTime

import prv.zielony.transaction.analyst.lambda.events.transactions.TransferEvent
import prv.zielony.transaction.analyst.lambda.speed.layer.services
import spray.routing.{Route, HttpService}

/**
 * Created by zielony on 25.02.16.
 */
trait CapitalResource extends HttpService {

  def capitalInterface:Route = {
    path("capital" / "variance") {
      parameters("start".?, "end".?) { (start, end) =>

        val startDate = start map (LocalDateTime.parse(_, defaultDateTimeFormatter))
        val endDate = end map (LocalDateTime.parse(_, defaultDateTimeFormatter))

        complete(services.capitalVariation(startDate, endDate).toString())
      }
    } ~
      path("capital" / "insertions" / "average") {
        parameters("start" ?, "end" ? ) { (start, end) =>

          val startDate = start map (LocalDateTime.parse(_, defaultDateTimeFormatter))
          val endDate = end map (LocalDateTime.parse(_, defaultDateTimeFormatter))

          complete(services.averageInsertion(startDate, endDate).toString())
        }
      } ~
      path("capital" / "withrawals" / "average") {
        parameters("start" ? , "end" ?) { (start, end) =>

          val startDate = start map (LocalDateTime.parse(_, defaultDateTimeFormatter))
          val endDate = end map (LocalDateTime.parse(_, defaultDateTimeFormatter))

          complete(services.averageWithdrawal(startDate, endDate).toString())
        }
      } ~
      path("transfers") {

        put {
          entity(as[TransferEvent]) { event =>
            //TODO: Register transfer
            complete("DONE")
          }
        }
      } ~
      path("cash" / "operations") {

        put {
          entity(as[CashOperationEvent]) { event =>
            //TODO: Register cash operation
            complete("DONE")
          }
        }
      }
  }
}
