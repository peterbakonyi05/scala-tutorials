object FibonacciModule {
    def fibonacci(index: Int): Int = {
        @annotation.tailrec
        def loop(index: Int, prev: Int, current: Int): Int = {
            if (index <= 0) {
              current
            } else {
              loop(index - 1, prev = prev + current, current = prev)
            }
          }
        
          loop(index, prev = 1, current = 0)
    }

    def main(args: Array[String]): Unit = {
        println(fibonacci(0));
        println(fibonacci(1));
        println(fibonacci(2));
        println(fibonacci(3));
    }
}