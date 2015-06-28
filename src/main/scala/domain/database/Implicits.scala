package domain.database

import scala.concurrent.ExecutionContext
import scalaz.{Monad, Functor, Monoid}

object Implicits {
  import slick.dbio._

//  implicit def dbioActionMonoid[R : Monoid, E <: Effect]: Monoid[DBIOAction[R, NoStream, E]] =
//    new Monoid[DBIOAction[R, NoStream, E]] {
//      override val zero: DBIOAction[R, NoStream, E] =
//        DBIO.successful(Monoid[R].zero)
//
//      override def append(x: DBIOAction[R, NoStream, E], y: DBIOAction[R, NoStream, E]): DBIOAction[R, NoStream, E] =
//        x >> y
//    }

  implicit def dbioActionFunctor[E <: Effect](implicit ec: ExecutionContext): Functor[({type F[A] = DBIOAction[A, NoStream, E]})#F] =
    new Functor[({type F[A] = DBIOAction[A, NoStream, E]})#F] {
      override def map[A, B](fa: DBIOAction[A, NoStream, E])(f: A => B): DBIOAction[B, NoStream, E] =
        fa map f
    }

  implicit def dbioActionMonad[E <: Effect](implicit ec: ExecutionContext) =
    new Monad[({type M[A] = DBIOAction[A, NoStream, E]})#M] {
      override def point[A](x: => A): DBIOAction[A, NoStream, E] =
        DBIO.successful(x)

      override def bind[A, B](fa: DBIOAction[A, NoStream, E])(f: A => DBIOAction[B, NoStream, E]): DBIOAction[B, NoStream, E] =
        fa flatMap f
    }
}
