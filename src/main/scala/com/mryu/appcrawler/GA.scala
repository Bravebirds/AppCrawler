package com.mryu.appcrawler
import com.brsanthu.googleanalytics.{GoogleAnalytics, PageViewHit}
import org.apache.log4j.{BasicConfigurator, Level, Logger}
object GA {
  var logLevel=Level.INFO

  BasicConfigurator.configure()
  Logger.getRootLogger.setLevel(Level.OFF)
  Logger.getLogger(classOf[GoogleAnalytics]).setLevel(Level.OFF)
  val ga = new GoogleAnalytics("UA-74406102-1")
  var appName="default"
  def setAppName(app:String): Unit ={
    appName=app
  }
  def log(action: String): Unit ={
    ga.postAsync(new PageViewHit(s"localhost/${appName}/${action}", "test"))
  }

}
