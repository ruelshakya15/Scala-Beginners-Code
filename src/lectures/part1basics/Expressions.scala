package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2
  println(x)

  println(2 + 3 * 4)
  println(1 == x)
  println(!(1 == x))

  var aVariable = 2
  aVariable += 3//side effects

  //Instruction(DO) vs Expression(VALUE) ****

  //IF expression
  val aCondition = true
  val aConditionValue = if(aCondition) 5 else 3 //IF Expression
  println(aConditionValue)
  println(1 + 3)

  var i = 0
  val aWhile = while (i < 10){
    println(i)
    i +=1
  }//NEVER WRITE

  //EVERYTHING IN SCALA Is Expression

  val aWeirdValue = (aVariable = 3)//Unit == void
  println(aWeirdValue)

  //Side effect:println(), whiles, reassigning

  //Code block

  val aCodeblock={
    val y =2
    val z = y + 1
    if (z > 2) "Hello" else "goodbye"
  }


}
