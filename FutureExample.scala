import scala.concurrent.{Future, ExecutionContext}
import scala.util.{Success, Failure}
import scala.concurrent.duration._
import scala.concurrent.Await

object FutureExample {
  // Use global ExecutionContext (Thread pool for Futures)
  implicit val ec: ExecutionContext = ExecutionContext.global

  def main(args: Array[String]): Unit = {
    val future1 = Future {
      Thread.sleep(1000)
      println("Future 1 completed")
      10
    }

    val future2 = Future {
      Thread.sleep(500)
      println("Future 2 completed")
      20
    }

    val resultFuture = for {
      res1 <- future1
      res2 <- future2
    } yield res1 + res2

    // Handle result
    resultFuture.onComplete {
      case Success(value) => println(s"Sum of future results: $value")
      case Failure(e)     => println(s"Error occurred: ${e.getMessage}")
    }

    // Wait for the result to avoid premature exit
    Await.result(resultFuture, 3.seconds)
  }
}
