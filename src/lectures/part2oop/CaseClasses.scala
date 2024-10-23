package lectures.part2oop

object CaseClasses extends App {
  case class Person(name: String,age: Int)

  //1.case class parameter are fields
  val jim = new Person("jim",34)
  println(jim.name)

  //2.Sensible toString
  println(jim.toString)
  println(jim)//does same as "jim.toString"

  //3.equal and hasHCode implemented OOTB(outof the box)
  val jim2 = new Person("Jim",34)
  println(jim == jim2)

  //4.CCs have handy copy method
  val jim3 = jim.copy(age = 45)//copy jim=>jim3 but change age
  println(jim3)

  //5.CCs have companion objects automatically
  val thePerson = Person
  val marry = Person("Marry",23) //**In Practice:Instantiate CC like this dont use "new" keyword

  //6.CCs are serializable
  //Akka

  //7.CCs have extractor patterns == can be use in PATTERN MATCHING

  //can also declare case object
  case object UnitedKingdom {
    def name : String = "The UK of GB and NI"
  }
  /*Exercise
      Expand MyList - use case classes and case objects
     */
}
