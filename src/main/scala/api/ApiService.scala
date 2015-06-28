package api

import akka.http.scaladsl.server.Directives
import domain.Protocol

/**
 *
 */
trait ApiService extends Directives with DefaultTimeout with Protocol
