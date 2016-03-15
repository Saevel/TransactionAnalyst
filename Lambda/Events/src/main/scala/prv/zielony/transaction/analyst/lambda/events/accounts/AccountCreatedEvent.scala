package prv.zielony.transaction.analyst.lambda.events.accounts

import java.time.LocalDateTime

import prv.zielony.transaction.analyst.lambda.model.Currency

/**
 * Created by zielony on 14.02.16.
 */
case class AccountCreatedEvent(override val timestamp:LocalDateTime, val accountId:Long, val balance:Double,
                               val currency:Currency, val ownerId:Long) extends AccountEvent(timestamp, accountId);
