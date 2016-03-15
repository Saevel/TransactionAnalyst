package prv.zielony.transaction.analyst.modern.model

import java.time.LocalDateTime
import java.util.Date

/**
 * Created by zielony on 21.02.16.
 */
case class CashOperation(val accountId:Long, val amount:Double, val operation:CashOperationType, timestamp:Date);
