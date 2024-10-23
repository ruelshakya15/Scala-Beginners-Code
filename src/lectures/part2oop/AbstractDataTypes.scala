package lectures.part2oop

object AbstractDataTypes extends App {

  //abstract(no object)
  abstract class Animal {
    val creatureType: String = "wild"//(can have)non Abstract member
    def eat: Unit
  }

  class Dog extends Animal{
    override val creatureType: String = "Canine"
    def eat: Unit = println("crunch crunch")
  }

  //Traits
  trait Carnivore {//no paramters like trait Carnivore(...)
    def eat(animal: Animal):Unit
    val preferredMeal: String = "fresh meat"//(can have)non abstract member
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Croc"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal):Unit = println(s"I am a croc and Im eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  //traits vs abstract classes
    //1 - traits do no have constructor parameters
    //2 - multiple traits may be inherited by the same class
    //3 - traits  = "behaviour"(coldblooded),abstract class ="Thing"(Animal)

}
