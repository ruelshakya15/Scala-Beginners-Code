package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  //anonymous class(notice:abstract class ko methods def direct val ma lekhdeko cha)
  val funnyAnimal: Animal = new Animal {
    def eat: Unit = println("ahahahahah")
  }
  println(funnyAnimal.getClass)
    /*
    above equivalent to(compiler does it behind the scenes) :
      class AnonymousClasses$$anon$1 extends Animal{
      override def eat: Unit = println("ahahahahah")
    }
    val funnyAnimal: Animal = new AnonymousClasses$$anon$1

     */

  //Anonymous class(for non abstract class aswell/traits as well)
  class Person(name: String) {
    def sayHi = println(s"Hi my name is $name")
  }

  val jim =new Person("Jim"){//rule1:still need to pass parameter("Jim")
    override def sayHi: Unit = println(s"Hi my name is Jim")//rule2:Also override every abstract methods for abstract class/traits
  }
  jim.sayHi
  /* Exercise
      1.  Generic trait MyPredicate[-T] with a little method test(T) => Boolean
      2.  Generic trait MyTransformer[-A, B] with a method transform(A) => B
      3.  MyList:
          - map(transformer) => MyList
          - filter(predicate) => MyList
          - flatMap(transformer from A to MyList[B]) => MyList[B]

          class EvenPredicate extends MyPredicate[Int]
          class StringToIntTransformer extends MyTransformer[String, Int]

          [1,2,3].map(n * 2) = [2,4,6]
          [1,2,3,4].filter(n % 2) = [2,4]
          [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
     */



}
