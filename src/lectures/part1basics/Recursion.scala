package lectures.part1basics

import scala.annotation.tailrec
import scala.jdk.Accumulator

object Recursion extends App {

//  @tailrec= CHECK tail recursion
  def factorial(n:Int): Int =
    if (n<=1) 1
    else {
      println("COmputing factorial of " + n + " - 1 first need factorial of " + (n-1))
      val result = n * factorial(n-1)
      println("Computed factorial of "+ n)

      result
    }

  println(factorial(10))
//  println(factorial(5000))
//Stops at 4500 stack overflow

  def anotherFactorial (n:Int): BigInt = { // no need for auxillary function
    def factHelper( x:Int,accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1,x * accumulator )//TAIL RECURSION= use recursive call as LAST Expression

    factHelper(n,1)
  }
//  println(anotherFactorial(5000))
//  This works with BIG NO

  //WHEN YOU NEED LOOPS< USE _TAIL RECURSION.(use accumulators)

  /*
  Exercises
   */

    @tailrec
    def concatHelper(x: Int, baseWord:String,accumulator: String):String=
      if (x <= 0) accumulator
      else concatHelper(x-1,baseWord,accumulator + baseWord)

  println(concatHelper(3,"hello",""))


  def isPrime(n: Int):Boolean = {
    @tailrec
    def primeHelper(x: Int,y :Int):Boolean = {
      if (y == 1) true
      else if ((x % y) == 0) false else primeHelper(x,y-1)
    }
    primeHelper(n,n / 2)
  }
  println(isPrime(34))

  def fibonacci(n :Int): Int = {
    def fiboTailrecursion(x: Int,initial:Int , accumulator: Int):Int = {
      if (x <= 1) accumulator
      else if (accumulator == 0) fiboTailrecursion(x-1,initial,accumulator + initial)
      else  fiboTailrecursion(x-1,accumulator,accumulator + initial)
    }
    fiboTailrecursion(n,1,0)
  }
  println(fibonacci(8))

}
