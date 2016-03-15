package prv.zielony.transaction.analyst.lambda.batch.layer.util

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.events.users.{UserCreatedEvent, UserDeletedEvent, PersonalDataChangedEvent, ContactDataChangedEvent}

/**
 * Created by zielony on 21.02.16.
 */
case class UsersData(val createdEvents:RDD[UserCreatedEvent], val removedEvents:RDD[UserDeletedEvent],
                     val personalDataChanges: RDD[PersonalDataChangedEvent], val contactDataChanges: RDD[ContactDataChangedEvent]);
