package prv.zielony.transaction.analyst.modern

import java.time.format.DateTimeFormatter

import prv.zielony.proper.inject._
import prv.zielony.proper.default._
import prv.zielony.proper.converters.auto._

/**
 * Created by zielony on 25.02.16.
 */
package object rest {

  private[rest] val defaultDateTimeFormatter = DateTimeFormatter.ofPattern(%("date.time.format"));

}
