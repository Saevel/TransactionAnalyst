package prv.zielony.transaction.analyst.lambda.batch.layer

import org.apache.spark.sql.SQLContext
import org.apache.spark.streaming.{Minutes, StreamingContext}
import org.apache.spark.{SparkContext, SparkConf}
import prv.zielony.proper.inject._
import prv.zielony.proper.converters.auto._
import prv.zielony.proper.model.Bundle
import prv.zielony.proper.path.classpath
import prv.zielony.proper.utils.load

/**
 * Created by zielony on 15.03.16.
 */
package object spark {

  val sparkBundle:Bundle = load(classpath:/"spark.properties")

  private val sparkConf:SparkConf = new SparkConf()
    .setAppName(%("spark.app.name" @@ sparkBundle))
    .setMaster(%("spark.master" @@ sparkBundle));

  val sparkContext:SparkContext = new SparkContext(sparkConf)

  val sqlContext:SQLContext = new SQLContext(sparkContext);

  val streamingContext:StreamingContext =
    new StreamingContext(sparkContext, Minutes(%("spark.streaming.batch.duration" @@ sparkBundle)))
}
