package lectures.part2oop
//** CONFUSING WHOLE WATCH VIDEO and also look at mylist exercise
object Generics extends App {

  //type paramterization(only for traits,methods and classes not objects)
  class MyList[+A]{//reuse exercise.Mylist for diff datatypes
    //use the type A in def
//    def add(element: A):MyList[A]= ???//error
    def add[B >:A](element: B):MyList[B] = ???
    /*

    A = Cat
    B = Animal
     */

  }
  class MyMap[Key, Value]
  val listofIntegers =  new MyList[Int]
  val listofStrings = new MyList[String]

  //generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  //variance problems
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1.yes List[Cat] extends List[Animals] = COVARIANCE
  class CovariantList[+A]
  val animal:Animal = new Cat
  val animalList:CovariantList[Animal] = new CovariantList[Cat]// similar as above
  //animalList.add(new Dog)?? HARD QUESTION. = > we return list of animals

  //2.No (list of cat and list of dog diff) = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList : InvariantList[Animal] = new InvariantList[Animal]//yesma Cat mildaina "A" j ho tei hunu parcha

  //3.Hell No! CONTRAVARIANCE
  class Trainer[-A]
  val trainer:Trainer[Cat] = new Trainer[Animal]

  //bounded types(note:A>:Animal vo vane supertype of ANIMAL accept garcha)
  class Cage[A <: Animal](animal: A)//**note:type parameter accept =>( A which is subtype of ANIMAl/or ANIMAL)IMP ANIMAL matrai ni accept garcha
  val cage = new Cage(new Dog)

  class Car
//  val newCage = new Cage(new Car)//give error(ie Car is not subtype of ANIMAL)

  //Exercise Expand Mylist in exercise.Mylist to be generic

}
