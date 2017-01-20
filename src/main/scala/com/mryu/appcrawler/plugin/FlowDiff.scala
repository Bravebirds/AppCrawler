package com.mryu.appcrawler.plugin
import com.mryu.appcrawler.URIElement
class FlowDiff extends Plugin{
  override def start(): Unit ={
  }
  override def afterElementAction(element: URIElement): Unit ={
    getCrawler().store.saveResDom(getCrawler().driver.currentPageSource)
  }
}