package core

import akka.http.Http
import akka.stream.scaladsl._
import api.UserApi

/**
 * Main application launcher.
 * - defines actor system for our application
 * - creates server instance
 * - add shutdown hook for actor system
 */
object Boot extends DefaultBootService {

  def routes = new UserApi().routes

  def main(args: Array[String]): Unit = {
    Http().bind(interface = config.getString("application.server.host"), port = config.getInt("application.server.port"))
      .toMat(Sink.foreach(_.flow.join(routes).run()))(Keep.left)
      .run()
  }
}
