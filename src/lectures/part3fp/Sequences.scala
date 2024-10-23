package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7,5,6))
  println(aSequence.sorted)

  // Ranges ("to" => inclusive, "until" = exclusive)
  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)
  println(aRange)

  (1 to 10).foreach(x => println("Hello"))

  //List
  val aList = List(1,2,3)
  val prepended = 42 +: aList :+ 89
  println(prepended)

  val apples5 = List.fill(5)("apple") //fill is a curried fxn with 2 params
  println(apples5)
  println(aList.mkString("- | -"))

  // Array == simple Java arrays (MUTABLE)
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[String](3) // "Declaration" only no allocation,Default values set acc to TYPE
  threeElements.foreach(println)

  // mutation in ARRAYS
  numbers(2) = 0 // syntax sugar for number.update(2,0) similar to "apply" method used for mutable collections
  println(numbers.mkString(" "))

  // arrays and seq mapping
  val numbersSeq: Seq[Int] = numbers // "implicit conversion" (arrays -> ArraySeq)
  println(numbersSeq)

  // Vectors (default: for immutable sequences) all above operation can be used
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  //"Vectors" vs "Lists" which is more efficient ?
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for{
      it <- 1 to maxRuns
    } yield {
      val  currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // Ad of List => keeps reference to tail
  // DisAd of List => updating an elem in middle takes long
  println(getWriteTime(numbersList))
  // Ad of Vector => depth of tree is small
  // DisAd of Vector => need to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))
}
