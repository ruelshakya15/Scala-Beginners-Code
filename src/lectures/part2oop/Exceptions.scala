package lectures.part2oop

import java.nio.{BufferOverflowException, BufferUnderflowException}

object Exceptions extends App {

  val x:String = null
//  println(x.length)//this<< will crash with NPE

  //1.throwing and catching exceptions
//  val aWeirdValue:String = throw new NullPointerException

  //throwable classes extend the Throwable class
  //Exception and Error are the major Throwable subtypes

  //2.how to catch exceptions
  def getInt(withException: Boolean): Int = {
    if(withException) throw new RuntimeException("No int for you")
    else 42
  }

  val potentialFail = try {
    //code that might throw exception
    getInt(false)
  } catch {
    case e: RuntimeException => 43
  } finally {
    //optional
    //code that will get executed NO MATTER WHAT
    //does not influence return type of expression
    println("Finally")
  }
  println(potentialFail)

  //3.how to define our own Exceptions
  class MyException extends Exception
  val exception = new MyException
//  throw exception

  /*
     1.  Crash your program with an OutOfMemoryError
     2.  Crash with SOError
     3.  PocketCalculator
         - add(x,y)
         - subtract(x,y)
         - multiply(x,y)
         - divide(x,y)

         Throw
           - OverflowException if add(x,y) exceeds Int.MAX_VALUE
           - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
           - MathCalculationException for division by 0
    */
    //.1 Ans: OOM Error
//  val array = Array.ofDim(Int.MaxValue)

  //2. Ans: SO Error
//  def infinite : Int = 1 + infinite
//  val  noLimit = infinite

  class OverfLowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverfLowException //Because: MAX_Int value plus smgth = -negative
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverfLowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }

    def multiplication(x: Int, y: Int) = {//**CONFUSE on under and over flow
      val result = x * y
      if(x > 0 && y >0 && result < 0 ) throw new OverfLowException // + + => - (positive * positive)
      else if (x < 0 && y < 0 && result > 0) throw new OverfLowException// - - => +
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException// + - => +
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException//- + => +
      else result
    }
  }

  println(PocketCalculator.multiplication(-Int.MaxValue,Int.MaxValue))


}
