package com.mryu.appcrawler
import org.apache.commons.io.FileUtils
import org.scalatest.tools.Runner
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.{Source, Codec}
import scala.reflect.io.File
import collection.JavaConversions._

trait Report extends CommonLog {
  var reportPath = ""
  var testcaseDir = ""

  def saveTestCase(store: URIElementStore, resultDir: String): Unit = {
    log.info("save testcase")
    reportPath = resultDir
    testcaseDir = reportPath + "/tmp/"
    //为了保持独立使用
    val path = new java.io.File(resultDir).getCanonicalPath

    val suites = store.elementStore.map(x => x._2.element.url).toList.distinct
    suites.foreach(suite => {
      log.info(s"gen testcase class ${suite}")
      //todo: 基于规则的多次点击事件只会被保存到一个状态中. 需要区分
      SuiteToClass.genTestCaseClass(
        suite,
        "com.mryu.appcrawler.TemplateTestCase",
        Map("uri"->suite, "name"->suite),
        testcaseDir
      )
    })
  }


  //todo: 用junit+allure代替
  def runTestCase(namespace: String=""): Unit = {
    var cmdArgs = Array("-R", testcaseDir,
      "-oF", "-u", reportPath, "-h", reportPath)

    if(namespace.nonEmpty){
      cmdArgs++=Array("-s", namespace)
    }

    log.info(s"run ${cmdArgs.mkString(" ")}")
    Runner.run(cmdArgs)
    changeTitle()
  }

  def changeTitle(title:String=Report.title): Unit ={
    val originTitle="ScalaTest Results"
    val indexFile=reportPath+"/index.html"
    val newContent=Source.fromFile(indexFile).mkString.replace(originTitle, title)
    scala.reflect.io.File(indexFile).writeAll(newContent)
  }

}

object Report extends Report{
  var showCancel=false
  var title="AppCrawler"
  var master=""
  var candidate=""
  var reportDir=""
  var store=new URIElementStore


  def loadResult(elementsFile: String): URIElementStore ={
    TData.fromYaml[URIElementStore](Source.fromFile(elementsFile).mkString)
  }
}
