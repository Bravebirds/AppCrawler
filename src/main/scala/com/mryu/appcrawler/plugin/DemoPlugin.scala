package com.mryu.appcrawler.plugin
import com.mryu.appcrawler.URIElement
class DemoPlugin extends Plugin{
  override def beforeElementAction(element: URIElement): Unit ={
    log.info(element)
  }
  override def afterUrlRefresh(url:String): Unit ={
    getCrawler().currentUrl=url.split('|').last
    log.info(s"new url=${getCrawler().currentUrl}")
    if(getCrawler().currentUrl.contains("Browser")){
      getCrawler().getBackButton()
    }
  }

}
