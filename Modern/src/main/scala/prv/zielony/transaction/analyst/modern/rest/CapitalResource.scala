package prv.zielony.transaction.analyst.modern.rest

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import prv.zielony.transaction.analyst.modern.services
import prv.zielony.transaction.analyst.modern.services._

import spray.routing.{Route, HttpService}

import scala.concurrent.Await
import scala.concurrent.duration._

import prv.zielony.transaction.analyst.modern.services.capitalService;

/**
 * Created by zielony on 25.02.16.
 */
trait CapitalResource extends HttpService {

  val formatter = new SimpleDateFormat("yyyy-MM-dd")

  def capitalInterface:Route = {
    path("capital" / "variance") {
      parameters("start".?, "end".?) { (start, end) =>

        val startDate = start map (formatter.parse)
        val endDate = end map (formatter.parse)

        val data = Await.result(capitalService.calculateCapitalVarianceInPeriod(startDate, endDate), 1 minutes)

        complete(data)
      }
    } ~
      path("capital" / "insertions" / "average") {
        parameters("start" ?, "end" ? ) { (start, end) =>

          val startDate = start map (formatter.parse)
          val endDate = end map (formatter.parse)

          val data = Await.result(capitalService.calculateInsertionsAverageInPeriod(startDate, endDate), 1 minutes)

          complete(data)
        }
      } ~
      path("capital" / "withrawals" / "average") {
        parameters("start" ? , "end" ?) { (start, end) =>

          val startDate = start map (formatter.parse)
          val endDate = end map (formatter.parse)

          val data = Await.result(capitalService.calculateWithdrawalsAverageInPeriod(startDate, endDate), 1 minutes)

          complete(data)
        }
      }
  }
}