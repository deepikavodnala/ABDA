object ThreadExample {
    def main(args: Array[String]): Unit = {
      // Define a task using Runnable
      val task1 = new Runnable {
        def run(): Unit = {
          for (i <- 1 to 5) {
            println(s"Task 1 - Count: $i")
            Thread.sleep(500)
          }
        }
      }

      val task2 = new Runnable {
        def run(): Unit = {
          for (i <- 1 to 5) {
            println(s"Task 2 - Count: $i")
            Thread.sleep(700)
          }
        }
      }

      // Start threads
      val thread1 = new Thread(task1)
      val thread2 = new Thread(task2)

      thread1.start()
      thread2.start()

      // Wait for threads to finish
      thread1.join()
      thread2.join()

      println("Both tasks completed.")
    }
  }