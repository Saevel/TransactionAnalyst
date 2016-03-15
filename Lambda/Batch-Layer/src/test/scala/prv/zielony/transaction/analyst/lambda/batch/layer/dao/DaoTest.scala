package prv.zielony.transaction.analyst.lambda.batch.layer.dao

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.runner.RunWith
import org.scalatest.FunSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import prv.zielony.transaction.analyst.lambda.batch.layer.{SampleClass, dao}

/**
 * Created by zielony on 07.03.16.
 */
@RunWith(classOf[JUnitRunner])
class DaoTest extends FunSpec with ShouldMatchers {

  describe("findAll") {

    it("reads an arbitrary class from a JSON file") {
      val samples = dao.findAll[Sample](dao.sqlContext)("src/test/resources/sample.json").collect

      samples should not be (null)
      samples should have length 9
    }
  }
/*
  describe("saveAll") {

    it("saves an arbitrary class to a JSON file") {

      val samples = List(
        Sample(1, "Sample1", 13.56),
        Sample(2, "Sample2", 63.85),
        Sample(3, "Sample3", 63.63)
      )

      dao.saveAll[Sample]("src/test/resources/sample-write.json")(dao.sparkContext.parallelize(samples))

      val saved = dao.sparkContext.textFile("src/test/resources/sample-write.json")

      saved should not be (null)
      saved.collect should have length samples.length
    }
  }
  */
}

case class Sample(val id:Int, val text:String, val number:Double) extends Serializable;
