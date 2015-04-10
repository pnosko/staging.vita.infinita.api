package api

import akka.event.{ NoLogging }
import akka.http.testkit.RouteTest
import com.typesafe.config.ConfigFactory
import core.{DatabaseCfg, BootService}

/**
 *
 */
trait TestBootService extends BootService { this: RouteTest =>
  override val config = ConfigFactory.parseResources("test.conf")
  override val logger = NoLogging

  DatabaseCfg.init(config)
}
