package lectures.part3fp

object MapFlatmapFilterFor extends App{

  val list = List(1,2,3)
  println(list.head)
  println(list.tail)

  //map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))
  

  //filter
  println(list.filter(_ % 2 == 0))


  //flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  /***ITERATIONS ***/
  //print all combination between two list("iterating")
  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')//List ("a1","a2",..."d4")
  val colors = List("black", "white")

  //2 combination
  val combination = (x: String) => numbers.map("" + x + _)
  println(chars.map(_.toString).flatMap(combination))

  //Alternate for above("" + ... ) garyo vane same as "toString"
  val aDiffCombination = numbers.flatMap(x => chars.map(c =>"" + c + x))
  println(aDiffCombination)

  //3 combination
  val threeCombination =
    println(numbers.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color))))

  //foreach
  list.foreach(println)

  //for - comprehensions(Similar Output as Above but easier to read)
  // compiler rewrites "for-comprehension" code as map,flatMap and filter while compiling
  val forCombinations = for {
    n <- numbers if n % 2 == 0 //filtering only even no
    c <- chars
    color <- colors
  }yield "" + c + n + "-" + color
  println(forCombinations)

  //"foreach" as "forComprehension"
  for{
    n <- numbers
  } println(n)

  //Syntax Overload(can use curly braces)(Syntactic Sugar)
  list.map { x =>
    x * 2
  }
  /*
     1.  MyList supports for comprehensions?
         map(f: A => B) => MyList[B]
         filter(p: A => Boolean) => MyList[A]
         flatMap(f: A => MyList[B]) => MyList[B]
     2.  A small collection of at most ONE element - Maybe[+T]
         - map, flatMap, filter
    */

  //2. Solution
  val aSmallCollection = List(1)
}
