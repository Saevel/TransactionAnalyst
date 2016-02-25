package prv.zielony.transaction.analyst.lambda.batch.layer.events

import java.time.LocalDateTime

/**
 * Created by zielony on 14.02.16.
 */
abstract class Event(val timestamp:LocalDateTime);
