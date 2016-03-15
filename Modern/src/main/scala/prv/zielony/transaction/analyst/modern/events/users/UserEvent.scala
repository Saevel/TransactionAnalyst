package prv.zielony.transaction.analyst.modern.events.users

import java.util.Date

import prv.zielony.transaction.analyst.modern.events.{Identifier, Event}

/**
 * Created by zielony on 20.02.16.
 */
abstract class UserEvent(timestamp:Date, val userId:Long) extends Event(timestamp) with Identifier[Long]{
  override val id:Long = userId;
}
