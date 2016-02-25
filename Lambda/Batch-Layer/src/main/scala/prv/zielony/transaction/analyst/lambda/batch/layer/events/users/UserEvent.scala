package prv.zielony.transaction.analyst.lambda.batch.layer.events.users

import java.time.LocalDateTime

import prv.zielony.transaction.analyst.lambda.batch.layer.events.{Event, Identifier}

/**
 * Created by zielony on 20.02.16.
 */
abstract class UserEvent(timestamp:LocalDateTime, val userId:Long) extends Event(timestamp) with Identifier[Long]{
  override val id:Long = userId;
}
