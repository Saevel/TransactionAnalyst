package prv.zielony.transaction.analyst.lambda.speed.layer.services

import org.apache.spark.rdd.RDD
import prv.zielony.transaction.analyst.lambda.model.{Country, User, Account}

/**
 * Created by zielony on 25.02.16.
 */
object AccountService {

  private[services] def averageAccountBalancePerUserAge(findAccounts:((Option[Int], Option[Int]) => RDD[Account with User with Country]))
                                                  (minAge:Option[Int], maxAge:Option[Int], country:String) = {

    findAccounts(minAge, maxAge) filter { account =>
      account.country == country
    } map (_.balance) mean
  }

  private[services] def countAccountsByCountry(findAccounts:(String => RDD[Account with User with Country]))(country:String):Long = {
    findAccounts(country) count
  }
}
