package utils

import scalaz.\/

trait Aliases {
  type Attempt[+A] = Throwable \/ A
}

object Alias extends Aliases