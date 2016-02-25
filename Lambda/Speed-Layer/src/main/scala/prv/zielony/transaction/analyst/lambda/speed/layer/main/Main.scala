package prv.zielony.transaction.analyst.lambda.speed.layer.main

import akka.actor.ActorSystem
import prv.zielony.transaction.analyst.lambda.speed.layer.rest.RestInterface
import spray.routing.SimpleRoutingApp

/**
 * Created by zielony on 17.02.16.
 */
object Main extends App with RestInterface {

  implicit val defaultActorSystem:ActorSystem = ActorSystem("default");

  //TODO: Rewrite as various actors!

  exposeRestInterface(host= "localhost", port = 9080)

  /*
  startServer(interface = "localhost", port = 9080) {

    /*
    get {
        path("sample") {
          complete("sample")
        }
      }.~(

      get {
        path("other") {
          complete("other")
        }
      })~
      */


    /*
    path("sample") {
      get {
        complete("sample")
      }
    } ~
    path("other") {
      get {
        complete("other")
      }
    }~
    path("accounts" / "capital" / "variance") {
      get {
        complete("12.0")
      }
    }
    */
  }
  */
}
