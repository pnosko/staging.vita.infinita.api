package core

import akka.http.Http
import akka.http.server.Route
import akka.stream.scaladsl._
import api._

/**
 * Main application launcher.
 * - defines actor system for our application
 * - creates server instance
 * - add shutdown hook for actor system
 */
object Boot extends DefaultBootService {

  def routes: Route = new api.RouteAggregator(this).routes

  def main(args: Array[String]): Unit = {
    DatabaseCfg.init(config)

    val host: String = config.getString("application.server.host")
    val port: Int = config.getInt("application.server.port")
    Http().bind(interface = host, port = port)
      .toMat(Sink.foreach(_.flow.join(routes).run()))(Keep.left)
      .run()
  }
}
