package exercises

abstract class MyList[+A] {
  /*
       head = first element of  the  list
       tail = remainder of the list
       isEmpty = is this list empty
       add(int) => new list with this element added
       toString => a string representation of the list
     */
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[ " + printElements + " ]"

  //higher-order functions
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean ): MyList[A]

  //concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]

  //HOFs
  def foreach(f : A => Unit): Unit
  def sort(compare:(A, A) => Int): MyList[A]
  def zipWith[B, C](list:MyList[B],zip: (A, B) => C) : MyList[C]
  def fold[B](start: B)(operator: (B,A) => B): B

}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""

  def map[B](transformer: Nothing=> B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  //Hofs
  def foreach(f: Nothing => Unit): Unit = ()//Unit Value
  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("List do not have same length")
    else Empty

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] { //+A ko satta A ni use garna milcha **CONFUSE
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }

  /* Working
     [1,2,3].filter(n % 2 == 0) =
       [2,3].filter(n % 2 == 0) =
       = new Cons(2, [3].filter(n % 2 == 0))
       = new Cons(2, Empty.filter(n % 2 == 0))
       = new Cons(2, Empty)
    */
  def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  /*
     [1,2,3].map(n * 2)
       = new Cons(2, [2,3].map(n * 2))
       = new Cons(2, new Cons(4, [3].map(n * 2)))
       = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
       = new Cons(2, new Cons(4, new Cons(6, Empty))))
    */
  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  /*
     [1,2] ++ [3,4,5]
     = new Cons(1, [2] ++ [3,4,5])
     = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
     = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
    */
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  /*
     [1,2].flatMap(n => [n, n+1])
     = [1,2] ++ [2].flatMap(n => [n, n+1])
     = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
     = [1,2] ++ [2,3] ++ Empty
     = [1,2,2,3]
    */
  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  //hofs
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    //auxillay fxn
    def insert(x: A,sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons(x,Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head,insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h,sortedTail)
  }

  def zipWith[B,C](list: MyList[B],zip: (A, B) => C) : MyList[C] = {
    if(list.isEmpty) throw new RuntimeException("List do not have same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  /*
      [1,2,3].fold(0)(+) =
      = [2,3].fold(1)(+) =
      = [3].fold(3)(+) =
      = [].fold(6)(+)
      = 6
     */
  def fold[B](start: B)(operator: (B, A) => B): B = {
    val newStart = operator(start, h)
    t.fold(newStart)(operator)
  }
}

//DELETED
//trait MyPredicate[-T]{ //Fxn from T => Boolean
//  def test(element: T): Boolean
//}
//
//trait MyTransformer[-A,B]{ // A => B
//  def transform(element: A): B
//}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)
  println(list.toString)

  val listOfInteger: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfInteger: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherlistOfInteger: MyList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfString: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))
  println(listOfInteger.toString)
  println(listOfString.toString)

  println(listOfInteger.map(_ * 2).toString)

  //Annoymous class jasto gareko
  println(listOfInteger.filter(_ % 2 == 0).toString)

  //concatenation test(cant use _ here because element used twice)
  println(listOfInteger ++ anotherlistOfInteger)//**CONFUSE ASK here too its printing why?
  println(listOfInteger.flatMap(element => new Cons(element, new Cons(element + 1, Empty))).toString)

  //case classes addition try "equals" method
  println(cloneListOfInteger == listOfInteger)

  //testing HOF and curry exercises
  listOfInteger.foreach(println)//shorter for :x => println(x)
  println(listOfInteger.sort((x, y) => y - x))
  println(listOfInteger)//**CONFUSE ASK how is this printing
  println(anotherlistOfInteger.zipWith[String, String](listOfString, _ + "-" + _ ))
  println(listOfInteger.fold(0)( _ + _ ))

//  "For comprehensions" to implement map,flatMap and filter
  val combinations = for {
    n <- listOfInteger
    string <- listOfString
  }yield n + "-" + string
  println(combinations)

}


