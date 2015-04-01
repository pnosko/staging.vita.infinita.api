package api

import akka.http.server.Directives
import domain.Protocol

/**
 *
 */
trait ApiService extends Directives with DefaultTimeout with Protocol
