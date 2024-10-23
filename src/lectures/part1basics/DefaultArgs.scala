package lectures.part1basics

import scala.collection.mutable.Map.WithDefault

object DefaultArgs extends App {

  def trFact(n:Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else trFact(n-1, n*acc)
  }

//  val fact10 = trFact(10,1)//no default value
//  val fact10 = trFact(10,2)//override default value
  val fact10 = trFact(10)

  def savePicture(format: String = "jpg", width: Int = 1920,height: Int = 1080):Unit = println("saving picture")
//    savePicture(800,600)//ERROR leading value must have mentioned default arg
//  savePicture(800,)//ERROR
  savePicture("bmp")//CORRECT
  savePicture(width = 800)//CORRECT
  savePicture("jpg",800,600)
  savePicture(width = 800,height = 600, format = "bmp")

}
