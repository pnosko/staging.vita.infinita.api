package api

import akka.http.server._
import akka.http.server.RouteConcatenation._
import core.BootService

/**
 *
 */
class RouteAggregator(val svc: BootService) {
  import svc._

  lazy val routes: Route = new UserApi().routes ~ new PersonApi().routes
}