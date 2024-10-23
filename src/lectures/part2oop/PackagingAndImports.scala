package lectures.part2oop

import playground.{PrinceCharming, Cinderella => Princess}

import java.sql
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  //package member accessible by name
  val writer = new Writer("Ruel","Shakya",2018)

  //import the package (ALIASING)of CINDERELLA
  val princess = new Princess // ==playground.Cinderella =full qualified name

  //packages are in hierarchy =>matched folder structure

  //package object
  sayHello
  println(SPEED_OF_LIGHT)

  //imports
  val prince = new PrinceCharming

  //1.use FQ name
  val date = new Date()
  val SQLData = new SqlDate(2018,5,4)
  //2.Use aliasing

  //default imports
  //java.lang - String, Object, Exception
  //scala = Int, Nothing, Function
  //scala.Predef - println, ???

}
