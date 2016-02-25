package prv.zielony.transaction.analyst.lambda.speed.layer.rest

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import akka.actor.ActorSystem
import prv.zielony.transaction.analyst.lambda.speed.layer.services
import prv.zielony.transaction.analyst.lambda.speed.layer.services._
import spray.routing.{SimpleRoutingApp, HttpService}



/**
 * Created by zielony on 17.02.16.
 */
trait RestInterface extends SimpleRoutingApp {

  //TODO: Moularize the interface

  def exposeRestInterface(host:String, port:Int)(implicit actorSystem:ActorSystem): Unit = {

    startServer(interface = host, port = port) {

        path("clients" / "age" / "median") {
          get {
            complete(services.medianUserAge())
          }
        } ~
        path("clients" / "age" / "average") {
          get {
            complete(services.averageUserAge())
          }
        } ~
        path("capital" / "variance") {
          parameters("start".?, "end".?) { (start, end) =>

            val startDate = start map (LocalDateTime.parse(_, defaultDateTimeFormatter))
            val endDate = end map (LocalDateTime.parse(_, defaultDateTimeFormatter))

            complete(services.capitalVariation(startDate, endDate))
          }
        } ~
        path("accounts" / "country" / Segment / "active") { country =>
          complete(services.countAccountsByCountry(country))
        } ~
        path("accounts" / "country" / Segment / "balance") { country =>

          parameters("minAge".?.as[Option[Int]], "maxAge".?.as[Option[Int]]) { (minAge, maxAge) =>
            complete(averageAccountBalancePerUserAge(minAge, maxAge, country))
          }
        } ~
        path("capital" / "insertions" / "average") {
          parameters("start" ?, "end" ? ) { (start, end) =>

            val startDate = start map (LocalDateTime.parse(_, defaultDateTimeFormatter))
            val endDate = end map (LocalDateTime.parse(_, defaultDateTimeFormatter))

            complete(services.averageInsertion(startDate, endDate))
          }
        } ~
        path("capital" / "withrawals" / "average") {
          parameters("start" ? , "end" ?) { (start, end) =>

            val startDate = start map (LocalDateTime.parse(_, defaultDateTimeFormatter))
            val endDate = end map (LocalDateTime.parse(_, defaultDateTimeFormatter))

            complete(services.averageWithdrawal(startDate, endDate))
          }
        }
    }
  }
}