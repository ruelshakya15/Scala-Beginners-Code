package lectures.part3fp

object AnonymousFunctions extends App {
  //syntactic sugar
  //anonymous function (LAMBDA)
  val doubler = (x: Int) => x * 2

  //multiple param in a lambda
  val adder:(Int,Int) => Int = (a, b) => a + b //dont need to write (a:Int, b: Int) because type already declared

  //no param
  val justDoSomething: () => Int = () => 3

  println(justDoSomething)//OUT : lectures.part3fp.AnonymousFun
  println(justDoSomething())//OUT: 3

  //curly braces with lambdas(common style to write)
  val stringToInt = {(str: String) =>
    str.toInt
  }

  //MoaR syntactic sugar
  val niceIncrementer: Int => Int =  _ + 1 //equivalent to x=> x + 1
  val niceAdder:(Int, Int)=> Int = _ + _ //equivalent to (a,b) => a + b

  /*
     1.  MyList: replace all FunctionX calls with lambdas
     2.  Rewrite the "special" adder as an anonymous function
    */

  //2.soln
  val superAdder = (x: Int) => (y: Int)=> x + y
  println(superAdder(3)(4))

}
