package api

import java.util.concurrent.TimeUnit
import akka.util.Timeout

/**
 *
 */
trait aDefaultTimeout {
  implicit val timeout = new Timeout(2, TimeUnit.SECONDS)
}
