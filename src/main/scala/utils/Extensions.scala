package utils

import Alias._

object Extensions {
  implicit class AttemptExtensions[A](obj: Attempt[A]) {
    def get: A = obj.fold(throw _, identity)
  }
}
