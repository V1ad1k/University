class MyThread extends Runnable
{
  override def run()
  {
    // Displaying the thread that is running
    println("Thread " + Thread.currentThread().getName() +
      " is running.")
  }
}

// Creating object 
object MainObject
{
  // Main method
  def main(args: Array[String])
  {
    for (x <- 1 to 5)
    {
      var th = new Thread(new MyThread())
      th.setName(x.toString())
      th.start()
    }
  }
} 