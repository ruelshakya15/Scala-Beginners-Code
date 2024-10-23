package lectures.part2oop

object Inheritance extends App{
  //single class inheritance
//  final class Animal {
  sealed class Animal {
    val creatureType = "wild"
//    protected def eat = println("nomnom")
//    final def eat = println("nomnom")
    def eat = println("nomnom")
  }

  class Cat extends Animal{//** SCALA has single class inheritance cannot inherit from multiple classes
    def crunch = {
      eat//protected use gare pachi direct call garna mildaina subclass ko object le class def vitra chai milcha
      println("crunch crunch")
    }
  }

  val cat = new Cat
//  cat.eat
  cat.crunch

  //Constructors
  class Person(name : String, age : Int){
    def this(name: String) = this(name,0)//can call this constructor below as well
  }
  class Adult(name :String,age: Int, idCard: String) extends Person(name)//because base class constructor is called first(JVM Rule)

  //overiding
  class Dog(override val creatureType: String) extends Animal {//can also override superclass in class parameters
   //override val creatureType: String = "domestic"
    override def eat = {
      super.eat //or eat
      println("crunch , crunch")
    }//override method and val
  }

  val dog =new Dog("k9")
  dog.eat
  println(dog.creatureType)

  //type substitution( polymorphism)
  val unknownAnimal: Animal = new Dog("K9")//animal ma derive class ko instance pass garna milcha
//  unknownAnimal.eat//need to delete protected word from base class(then code will call from derived class)

  //Super keyword
  //To prevent override
    // {1 - final keyword,
    // 2- use final on class(wont allow inherit),
    // 3 - Seal the class = extends allowed in THIS FILE,prevented in OTHER FILES


}
