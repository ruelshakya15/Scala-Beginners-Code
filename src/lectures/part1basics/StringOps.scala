package lectures.part1basics

object StringOps extends App {

  val str: String ="hello, i am learning scala"

  println(str.charAt(2))//return l
  println(str.substring(7,11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))//true or false
  println(str.replace(" ","-"))
  println(str.toLowerCase())
  println(str.length)

  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')//prepending and appending
  println(str.reverse)
  println(str.take(2))

  //SCALA SPECIFIC String interpolators

  //s Interpolators
  val name  = "David"
  val age =12
  val greeting = s"Hello, my nam eis $name and I am $age years old"
  val anotherGreeting = s"Hello, my nam eis $name and I am ${age + 1} years old"
  println(anotherGreeting)

  //F Interpolators
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)

  //raw interpolator
  println(raw"This is a \n newline")//\n doesnt go to new line
  val escaped = "This is a \n newline"
  println(raw"$escaped")//raw doesnt effect print in new line
}
