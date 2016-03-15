package prv.zielony.transaction.analyst.lambda.speed.layer.rest

import prv.zielony.transaction.analyst.lambda.events.users.{ContactDataChangedEvent, PersonalDataChangedEvent, UserDeletedEvent, UserCreatedEvent}
import prv.zielony.transaction.analyst.lambda.speed.layer.services
import spray.routing.{Route, HttpService}

/**
 * Created by zielony on 25.02.16.
 */
trait ClientsResource extends HttpService{

  def clientsInterface:Route = {
    path("clients" / "age" / "median") {
      get {
        complete(services.medianUserAge().toString())
      }
    } ~
      path("clients" / "age" / "average") {
        get {
          complete(services.averageUserAge().toString())
        }
      } ~
      path("clients") {
        put {
          entity(as[UserCreatedEvent]) { event =>
            //TODO:Create user
            complete("DONE")
          }
        }
        delete {
          entity(as[UserDeletedEvent]) { event =>
            //TODO: Remove user
            complete("DONE")
          }
        }
      } ~
      path("clients" / "data" / "personal") {
        put {
          entity(as[PersonalDataChangedEvent]) { event =>
            //TODO: Change personal data
            complete("DONE")
          }
        }
      } ~
      path("clients" / "data" / "contact") {
        put {
          entity(as[ContactDataChangedEvent]) { event =>
            //TODO: Change conctact data
            complete("DONE")
          }
        }
      }
  }
}