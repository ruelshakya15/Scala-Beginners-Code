package lectures.part3fp

object WhatsAFunction extends App {

  //DREAM : use functions as first class elements
  //problem : oop world
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  //function types = Function1[A,B],Function2[A,B],......3,4
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  //Below: Syntactic Sugar for new Function2[Int,Int,Int]
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  //Function types Function2[A,B,B] === (A,B)=>B (replace with)
  //ALL SCALA FUNCTIONS ARE OBJECTS
  /*
     1.  a function which takes 2 strings and concatenates them
     2.  transform the MyPredicate and MyTransformer into function types
     3.  define a function which takes an int and returns another function which takes an int and returns an int
         - what's the type of this function
         - how to do it
    */

  //1. solution(trying diff syntactic sugar of MYFunction1
  val concatenator: ((String, String) => String) = (a: String, b: String) => a + b
  println(concatenator("a", "b"))

  //3. solution
  val superAdder: Function1[Int,Function1[Int, Int]] =new Function1[Int,Function1[Int,Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int]{
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) //curried fxn
}


trait MyFunction[A, B] {
  def apply(element: A): B = ???
}
