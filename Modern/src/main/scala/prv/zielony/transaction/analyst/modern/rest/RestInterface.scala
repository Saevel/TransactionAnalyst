package prv.zielony.transaction.analyst.modern.rest

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import akka.actor.ActorSystem
import prv.zielony.transaction.analyst.lambda.speed.layer.services
import prv.zielony.transaction.analyst.lambda.speed.layer.services._
import spray.routing.{SimpleRoutingApp, HttpService}

/**
 * Created by zielony on 17.02.16.
 */
trait RestInterface extends SimpleRoutingApp with ClientsResource with AccountsResource with CapitalResource {

  def exposeRestInterface(host:String, port:Int)(implicit actorSystem:ActorSystem): Unit = {

    startServer(interface = host, port = port) {

      clientsInterface ~ accountsInterface ~ capitalInterface
    }
  }
}