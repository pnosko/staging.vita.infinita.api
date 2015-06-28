package core

import akka.actor.ActorSystem
import akka.event.{ Logging, LoggingAdapter }
import akka.stream.ActorMaterializer
import com.typesafe.config.{ ConfigFactory, Config }
import scala.concurrent.ExecutionContextExecutor

trait BootService {
  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit val materializer: ActorMaterializer

  def config: Config
  val logger: LoggingAdapter
}

trait DefaultBootService extends BootService {
  override implicit val system: ActorSystem = ActorSystem()
  override implicit val executor: ExecutionContextExecutor = system.dispatcher
  override implicit val materializer: ActorMaterializer = ActorMaterializer()

  override val config: Config = ConfigFactory.load()
  override val logger: LoggingAdapter = Logging(system, getClass)
}
