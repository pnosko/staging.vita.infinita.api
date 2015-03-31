package api

import core.BootService

/**
 *
 */
class ApiAggregate(val svc: BootService) {
  import svc._

  lazy val routes = new UserApi().routes
}
