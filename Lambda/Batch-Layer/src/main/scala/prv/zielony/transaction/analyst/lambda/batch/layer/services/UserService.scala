package prv.zielony.transaction.analyst.lambda.batch.layer.services

import java.time.LocalDateTime
import java.util.Date
import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.batch.layer.util.UsersData
import prv.zielony.transaction.analyst.lambda.events.Event
import prv.zielony.transaction.analyst.lambda.events.users.{UserCreatedEvent, PersonalDataChangedEvent, ContactDataChangedEvent}
import prv.zielony.transaction.analyst.lambda.model.User

/**
 * Created by zielony on 21.02.16.
 */
object UserService {

  private[services] def findActiveUsers(usersData: => UsersData):RDD[User] = {

    val activeUsers = usersData.createdEvents keyBy(_.id) subtractByKey (usersData.removedEvents keyBy(_.id))

    val latestContactData = usersData.contactDataChanges.keyBy(_.userId).groupByKey().map { record =>
      val (id, changes) = record
      findLatestEventByTimestamp(changes)
    } keyBy (_.id)

    val latestPersonalData =  usersData.personalDataChanges.keyBy(_.userId).groupByKey().map { record =>
      val (id, changes) = record
      findLatestEventByTimestamp(changes)
    } keyBy (_.id)

    (activeUsers join latestContactData join latestPersonalData) map { record =>
      val (id, ((activeUser, contactData), personalData)) = record
      constructUser(activeUser, contactData, personalData)
    }
  }

  private def findLatestEventByTimestamp[EventType <: Event](events:Iterable[EventType]):EventType  = events.reduce { (eventOne, eventTwo) =>
    if(eventOne.timestamp isAfter eventTwo.timestamp) {
      eventOne
    }
    else {
      eventOne
    }
  }

  private def constructUser(creationEvent:UserCreatedEvent, contactDataEvent:ContactDataChangedEvent,
                            personalDataEvent:PersonalDataChangedEvent):User = new User {
    override val id: Long = creationEvent.id
    override val username: String = creationEvent.username
    override val birthDate:Option[LocalDateTime] = personalDataEvent.newData.birthDate
  }
}