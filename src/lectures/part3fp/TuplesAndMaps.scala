package lectures.part3fp

import lectures.part2oop.MethodNotations.Person

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "hello, Scala") //Tuple2[int, String] = (Int, String)  OR val aTuple = (2, "hello, Scala")

  println(aTuple._1) //2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello, Scala", 2)

  // MAPS - key -> values (Immutable)
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), ("Daniel" -> 789)).withDefaultValue(-1)
  //a -> b syntatic sugar for (a, b)
  println(phonebook)

  //map ops
  println(phonebook.contains("Jim"))
  println(phonebook("Jim"))
  println(phonebook("Marry")) // return default value

  //add a paring
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  //functionals on maps
  //map, flatMap, filter

  println(phonebook.map(pair => pair._1.toLowerCase() -> pair._2))

  //filterKeys
  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap) // filterKeys only is deprecated use (view.filterKeys).toMap [need to convert MapView into staticMap]
  //mapValues
  println(phonebook.view.mapValues(number => number * 10).toMap)

  //conversion to other collections
  println(phonebook.toList) // -> pairing converted into tuples and stored in List((..,..), (.., ..))
  println(List(("Daniel", 555)).toMap) //and vice versa
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(_.charAt(0))) // group LIST to MAP acc to fxn

  /*
     1.  What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900

          !!! careful with mapping keys.

      2.  Overly simplified social network based on maps
          Person = String
          - add a person to the network
          - remove
          - friend (mutual)
          - unfriend

          - number of friends of a person
          - person with most friends
          - how many people have NO friends
          - if there is a social connection between two people (direct or not)
   */

  //1. Ans
  val exMap = Map("Jim" -> 555, "JIM" -> 900)
  println(exMap.map(pair => pair._1.toLowerCase() -> pair._2))

  //2.Ans
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty,"Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(network, "Bob", "Mary"))
  println(remove(friend(network,  "Bob", "Mary"), "Bob"))

  // Jim,Bob,Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println("testNet " + testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(network))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.filterKeys(k => network(k).size == 0).size
    // OR  network.count(_._2.isEmpty)
    // OR network.filter(_._2.isEmpty).size

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]], a:String, b:String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if(discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))




}
