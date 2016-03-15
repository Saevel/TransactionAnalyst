package prv.zielony.transaction.analyst.lambda.speed.layer.services

import java.time.LocalDateTime

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.model.User

/**
 * Created by zielony on 25.02.16.
 */
object UsersService {

  private[services] def medianUserAge(findClients:(() => RDD[User]))():Double = {
    val now = LocalDateTime.now()
    val years:Array[Int] = findClients() filter { user =>
      user.birthDate.isDefined
    } map { user =>
      (now.getYear - user.birthDate.get.getYear)
    } sortBy { years =>
      years
    } collect

    years(Math.floorDiv(years.length, 2))
  }

  private[services] def averageUserAge(findClients:() => RDD[User])():Double = {
    val now = LocalDateTime.now()
    findClients() filter { user =>
      user.birthDate.isDefined
    } map { user =>
      (now.getYear - user.birthDate.get.getYear)
    } mean
  }
}