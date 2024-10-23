package lectures.part2oop

object Object {
  //SCALA DOES NOT HAVE CLASS LEVEL FUNCTIONALITY no concept of("static")
  object Person{//equivalent to using static,object cannot have args() can have methods,var,val
    val N_EYES = 2
    def canFly:Boolean = false

// **Widely used
    //FACTORY method(builds new Person)normally used as APPLY method
    def apply(mother :Person, father:Person):Person = new Person("Boobie")
  }
  class Person(val name:String){//practically write object and class def in same scope ie PERSON KO
    //instance level functionality
  }
  //COMPANIONS of Person(code with be in SINGLETON OBJECT or CLASS of person)

  println(Person.N_EYES)
  println(Person.canFly)

  //Scala Object = SINGLETON INSTANCE ie (a scala object is TYPE+its only INSTANCE)
  val marry = new Person("marry")
  val john = new Person("john")
  println(marry == john)

  val person1 = Person
  val person2 = Person
  println(person1 == person2)//both point to same instance (ie singleton instance)

  val bobbie = Person(marry,john)//same as Person.apply(...)

  //SCALA Applications = Scala object with
    //def main( args: Array[String]) : Unit (even without extends App we can run code)


}
