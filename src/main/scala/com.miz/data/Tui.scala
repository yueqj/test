package com.miz.data

import org.apache.spark.sql.SparkSession
import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.mllib.recommendation.ALS
import org.jblas.DoubleMatrix

object Tui {

  def run1 = {

    val ss = SparkSession.builder().appName("test").master("local").getOrCreate()
    val rawData = ss.sparkContext.textFile("u.data")
    println(rawData.first())
    val rawRatings = rawData.map(_.split("\t"))
    println(rawRatings.first())
    val ratings = rawRatings.map{
      case Array(user,movie,rating) => Rating(user.toInt,movie.toInt,rating.toDouble)
    }
    val model = ALS.train(ratings,50,10,0.01)
    //预测评分
    val rr = model.predict(123,3)
    //topN推荐
    val cp = model.recommendProducts(11,10)

    val movData = ss.sparkContext.textFile("u.item")
    val titles = movData.map(_.split("\\|")).map(x => (x(0).toInt,x(1))).collectAsMap()
    titles(12)//得出电影id=12的电影名

    val movForUser = ratings.keyBy(x => x.user).lookup(123)//获取用户123的记录
    //获取用户评分最高的10部电影,并打印出电影名和评分
    movForUser.sortBy(x => x.rating).take(10).map(x => (titles(x.product),x.rating)).foreach(println(_))

    val itemId = 111
    val itemFactor = model.productFeatures.lookup(itemId).head
    val itemVector = new DoubleMatrix(itemFactor)
  }

  def cosineSimilarity(vec1: DoubleMatrix, vec2: DoubleMatrix): Double = {
    vec1.dot(vec2) / (vec1.norm2() * vec2.norm2())
  }

}
