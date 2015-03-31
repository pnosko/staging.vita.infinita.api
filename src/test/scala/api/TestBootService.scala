package api

import akka.event.{ NoLogging }
import akka.http.testkit.RouteTest
import core.{DatabaseCfg, BootService}

/**
 *
 */
trait TestBootService extends BootService { this: RouteTest =>
  override val config = testConfig
  override val logger = NoLogging

  DatabaseCfg.init(config)
}
