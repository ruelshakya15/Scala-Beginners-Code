package lectures.part4pm

object PatternsEverywhere extends App {

  //big idea #1
  try{
    //code
  }catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  //catches are actually MATCHES - below is how code is interpreted
  /*
      try {
        // code
      } catch (e) {
        e match {
          case e: RuntimeException => "runtime"
          case npe: NullPointerException => "npe"
          case _ => "something else"
        }
      }
     */

  //big idea # 2
  val list = List(1,2,3,4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // ?! generators are also based on PATTERN MATCHING
  }yield 10 * x

  //can use tuples also
  val tuples = List((1,2), (3,4))
  val filterTuples = for {
    (first, second) <- tuples
  }yield first * second
  //case classes, :: operators ,..  can also be used in {for comprehension generators}

  //  big idea # 3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple //using "NAME BINDING" property of pattern matching assign multiple values at once
  println(b)
  // multiple value definitions based on PATTERN MATCHING
  // ALL THE POWER

  val head:: tail = list
  println(head)
  println(tail)

  //big idea #4 - NEW
  //partial function based on PATTERN MATCHING
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => " the one"                                             //partial function literal
    case _ => " something else"
  }

  val mappedList2 = list.map { x => x match {      //EQUIVALENT TO ABOVE
    case v if v % 2 == 0 => v + " is even"
    case 1 => " the one"
    case _ => " something else"
   }
  }

  println(mappedList)

}
