package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App{

  //create success and failure
  val aSucess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSucess)
  println(aFailure)

  def unSafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  val potentialFailure = Try(unSafeMethod())
  println(potentialFailure)

  //syntax sugar
  val anotherPotentialFailure = Try {
    //code that might throw
  }

  //Utilities
  //--------------------------------------------
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unSafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  //IF you design an API(wrap ur code in "TRY")
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException())
  def betterBackupMethod(): Try[String] = Success(" A valid String")
  val betterFallback  = betterUnsafeMethod() orElse betterBackupMethod()

  // map, flatMap, filter
  println(aSucess.map(_ * 2))
  println(aSucess.flatMap(x => Success(x * 10)))
  println(aSucess.filter(_ > 10))
  //=> for-comprehension

  /*
    Exercise
   */

  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String) : String = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html> .... </html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService{
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // if you get the html page from the connection, print it to the console ; i.e call renderHTML

  val possibleConnection = HttpService.getSafeConnection(hostname, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home")) // ** Note: if flatMap not used and .getSafe called directly Try[Try[..]] returned
  possibleHTML.foreach(renderHTML)

  //Shorter Version:
  HttpService.getSafeConnection(hostname, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  //for -comprehension version
  for{
    connection <- HttpService.getSafeConnection(hostname, port)
    html <- connection.getSafe("/home")
  }renderHTML(html)

  //Imperative programming equivalent
  /*
     try {
       connection = HttpService.getConnection(host, port)
       try {
         page = connection.get("/home")
         renderHTML(page)
       } catch (some other exception) {

       }
     } catch (exception) {

     }
    */



}
