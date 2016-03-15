package prv.zielony.transaction.analyst.modern.services

import java.time.LocalDateTime
import java.util.Date

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.modern.dao
import prv.zielony.transaction.analyst.modern.model.User

import prv.zielony.transaction.analyst.modern.dao._

import scala.concurrent.Future

/**
 * Created by zielony on 28.02.16.
 */
object ClientsService {

  def calclulateClientsAgeMedian:Future[Int] = {

    val sortedBirthDates =  dao.users.findAll map { list =>
      list filter(_.isDefined) map(_.get) sortBy(_.getTime)
    }

    sortedBirthDates map { list =>
      val index = Math.floorDiv(list.length, 2)
      (new Date().getYear - list(index).getYear)
    }
  }

  def calculateAverageClientAge:Future[Double] = {

    val ages:Future[List[Int]] = dao.users.findAll map { list =>
      list filter(_.isDefined) map(_.get) map { date =>
        (new Date().getYear - date.getYear)
      }
    }

    ages map { list =>
      list.reduce { (first, second) =>
        (first + second) / list.length
      }
    }
  }
}