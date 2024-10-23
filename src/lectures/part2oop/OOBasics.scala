package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John",36)
//  println(person.name)//cannot resolve
  println(person.age)
  println(person.x)
  person.greet("Daniel")

  val author = new Writer("ruel", "shakya", 2000)
  println(author.fullname())

  val imposter = new Writer("ruelr", "shakyar", 2000)
  val novel = new Novel("fault", 1996, author)
  println(novel.authorAge())
  println(novel.isWrittenBy(imposter))
  println(novel.copy(1988))

  val counter = new Counter(0)//**CONfusing**//
  counter.incCount.print
  counter.incCount.incCount.incCount.print
  counter.incCount(10).print
}

class Person(name: String,val age: Int = 0) {
  //body
  val x = 2 //field

  println(1 + 3) //will be executed when class is instantiated

  //method
  def greet(name: String): Unit = println(s"${this.name} says: Hi $name")

  //overloading
  def greet(): Unit = println(s"Hi,I am $name")

  //multiple constructora
  def this(name: String) = this(name, 0) //auxilarry constructor must call another constructor_ INSTEAD just supply default paramters

  def this() = this("John DOE")
  //constructor with class parameter,paramter convert to FIELD using 'val'
}
  /*
Exercises
Novel and Writer
 */

   class Writer(val fname:String, val lname: String, val year: Int){

    def fullname()={fname + " " + lname}
  }

  class Novel(val name: String, val yearOfRelease:Int,author: Writer){
    def authorAge() = 2023 - author.year
    def isWrittenBy(author:Writer) = author == this.author
    def copy(newYearOfRelease: Int) = {
      new Novel(name, newYearOfRelease, author)
    }
  }

  /*
  Counter Class
   */

  class Counter(val count: Int){

    def currentCount = count

    def incCount = {
      println("incrementing")
      new Counter(count + 1)
    } //immutability

    def incCount(value : Int):Counter = {
      if (value <= 0) this//return current counter object
      else incCount.incCount(value - 1)//***CONFUSING//
    } //overload

    def print = println(count)
  }




//class parameter are NOT FIELD
