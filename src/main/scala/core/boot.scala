package core

import akka.http.Http
import akka.http.model.japi.IncomingConnection
import akka.stream.ActorFlowMaterializer
import akka.actor.ActorSystem
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
    val host: String = config.getString("application.server.host")
    val port: Int = config.getInt("application.server.port")
    Http().bind(interface = host, port = port)
      .toMat(Sink.foreach(_.flow.join(routes).run()))(Keep.left)
      .run()
  }
}
