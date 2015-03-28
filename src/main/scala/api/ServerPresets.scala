package api

import java.util.concurrent.TimeUnit
import akka.util.Timeout

/**
 *
 */
trait DefaultTimeout {
  implicit val timeout = new Timeout(2, TimeUnit.SECONDS)
}
