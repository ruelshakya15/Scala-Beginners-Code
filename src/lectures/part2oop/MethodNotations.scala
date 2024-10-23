package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String,val age:Int= 0){
    def likes(movie: String):Boolean = movie == favoriteMovie
//    def hangOutWith(person: Person):String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person):String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname:String):Person = new Person(name+s"($nickname)",favoriteMovie)
    def unary_! : String = s"$name,what the heck"//override unary operator !
    def unary_+ :Person = new Person(name, favoriteMovie, age+1)
    def isAlive: Boolean = true
    def apply(): String = s"hi my name is $name and I like $favoriteMovie"
    def apply(num:Int) = s"$name watched $favoriteMovie $num times"

    def learns(text:String) = s"$name learns $text"
    def learnsScala = s"$name learns Scala"
  }
  val marry = new Person("Marry","Inception")
  println(marry.likes("Inception"))
  println(marry likes "IncSSeption")//equivalent //infix notation/operator notation(only with methods with single parameter)

  //operators in Scala
  val tom = new Person("Tom","Fight Club")
//  println(marry hangOutWith tom)//syntatic sugar
  println(marry + tom)//syntatic sugar
  println(marry.+(tom))//same

  println(1 + 2)
  println(1.+(2))//ALL OPERATORS ARE METHODS
  //Akka actors have ! ?**CONFUSE

  //prefix notation
  val x = -1
  val y = 1.unary_-//equivalent
  //unary prefix onlyW works with - + ~ !
  println(!marry)
  println(marry.unary_!)//equivalent

  //postfix notation(only available to methis w/o paramter)
  println(marry.isAlive)
  println(marry isAlive)//same

  //apply
  println(marry.apply())
  println(marry())//equivalent(calling object like function)

//  *********Exercises************
  /*
      1.  Overload the + operator
          mary + "the rockstar" => new person "Mary (the rockstar)"

      2.  Add an age to the Person class
          Add a unary + operator => new person with the age + 1
          +mary => mary with the age incrementer

      3.  Add a "learns" method in the Person class => "Mary learns Scala"
          Add a learnsScala method, calls learns method with "Scala".
          Use it in postfix notation.

      4.  Overload the apply method
          mary.apply(2) => "Mary watched Inception 2 times"
     */

  println((marry + "rockstar").apply())
  println((+marry).age)
  println(marry.learns("java"))
  println(marry learnsScala)
  println(marry(10))

}
