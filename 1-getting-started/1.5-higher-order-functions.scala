/**
  * HOF: take functions as parameter or return a function
  */
case class WeeklyWeatherForecast(temperatures: Seq[Double]) {

  private def convertCtoF(temp: Double) = temp * 1.8 + 32

  // possible to pass methods as arguments to higher-order functions
  // Scala compiler will coerce the method into a function
  def forecastInFahrenheit: Seq[Double] =
    temperatures.map(convertCtoF) // <-- passing the method convertCtoF
}

object HofDemo {
  def main(args: Array[String]) = {
    val salaries = Seq(20000, 70000, 40000)
    val newSalaries = salaries.map(_ * 2)
    newSalaries.foreach(println)

    // functions that return functions
    def urlBuilder(
        ssl: Boolean,
        domainName: String
    ): (String, String) => String = {
      val schema = if (ssl) "https://" else "http://"
      (endpoint: String, query: String) =>
        s"$schema$domainName/$endpoint?$query"
    }

    def getURL = urlBuilder(ssl = true, "www.example.com")
    println(getURL("users", "id=1"))
  }
}
