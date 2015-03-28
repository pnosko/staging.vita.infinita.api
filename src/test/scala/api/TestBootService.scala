package api

import akka.actor.ActorSystem
import akka.event.{ NoLogging }
import akka.http.testkit.RouteTest
import akka.stream.ActorFlowMaterializer
import com.typesafe.config.ConfigFactory
import core.BootService

/**
 *
 */
trait TestBootService extends BootService { this: RouteTest =>
  override val config = testConfig
  override val logger = NoLogging
}
