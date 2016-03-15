package prv.zielony.transaction.analyst.modern.events.users

/**
 * Created by zielony on 20.02.16.
 */
case class ContactData(val phoneNumbers: Traversable[String] = Traversable(),
                       val emails:Traversable[String] = Traversable(),
                       val addresses:Traversable[Address] = Traversable())

case class Address(val street:Option[Street], val city:Option[String], val country:Option[String]);

case class Street(val name:String, val number:String);

