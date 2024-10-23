package lectures.part1basics

object Functions extends App {

  def aFunction(a :String,b:Int):String = {
     a + " " + b
  }

  println(aFunction("hello",4))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())
  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String,n: Int):String ={
    if (n == 1) aString
    else aString + aRepeatedFunction(aString,n-1)
  }

  println(aRepeatedFunction("hello",3))

  //WHEN YOU NEED LOOPS<USE RECURSION

  def aFunctionWithSideEffects(aString : String):Unit = println(aString)

  def aBigFunction(n: Int):Int = {
    def aSmallerFunction(a:Int,b:Int): Int = a + b

    aSmallerFunction(n,n-1)
  }
  /*
  Exercise
   */

  def greetingFunction(name:String,age:Int):Unit = {
    println(s"Hi my name is $name and I am $age years old.")
  }

  def factorial(num:Int):Int={
    if(num <= 1) 1 else num * factorial(num-1)
  }

  def fibonacci(n:Int):Int = {
    if (n <= 2) 1 else fibonacci(n-1) + fibonacci(n-2)
  }

  def numberPrimeOrNot(num:Int):Boolean = {

     def isPrime(i:Int):Boolean={//AUXILLARY FUNCTION
       if(i <= 1) true
       else {
         if((num % i) !=0) isPrime(i-1) else false
       }
     }
     isPrime(num/2)
  }
  println(factorial(5))
  println(fibonacci(8))
  println(numberPrimeOrNot(37))
  println(numberPrimeOrNot(24))



}
