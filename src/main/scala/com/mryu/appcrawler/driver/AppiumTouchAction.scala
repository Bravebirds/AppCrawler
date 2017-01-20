package com.mryu.appcrawler.driver;

import io.appium.java_client.{AppiumDriver, TouchAction}
import io.appium.java_client.touch.TapOptions
import io.appium.java_client.touch.offset.{ElementOption, PointOption}
import org.openqa.selenium.WebElement


class AppiumTouchAction {
  var action: TouchAction[_] = null
  var width = 0
  var height = 0

  def this(driver: AppiumDriver[_ <: WebElement]) {
    this()
  }

  def this(driver: AppiumDriver[_ <: WebElement], width: Int, height: Int) {
    this()
    this.width = width
    this.height = height
  }

  def swipe(startX: Double, startY: Double, endX: Double, endY: Double): AppiumTouchAction = {
    action.press(PointOption.point((width * startX).toInt, (height * startY).toInt))
    this
  }

  def tap(currentElement: WebElement): AppiumTouchAction = {
    action.tap(TapOptions.tapOptions.withElement(ElementOption.element(currentElement)))
    this
  }
}