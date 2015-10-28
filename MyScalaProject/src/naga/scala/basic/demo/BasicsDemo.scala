package naga.scala.basic.demo

import scala.util.control._
import scala.math.sqrt

class BasicsDemo {

  //demo if, in scala "if" is expression which means it returns some thing
  var y = 2;
  var s = if (y > 0)  1  else -1
  println("Value of s is ..." + s)
  y = -2;
  //How does this work?????   
  var r = if (y > 0) 1
  println("Value of r is ..." + r)
  //Guess this answer
  r = if (y > 0) 1
  else if (y == 0) 0
  println("Value of r is ..." + r)
  //Guess this answer
  r = if (y > 0) 1
  else if (y == 0) 0 else -1
  println("Value of r is ..." + r)

  //demo expression in scala,  
  var p = { y = y * y; var x = y - 1; sqrt(y) }
  println("Value of p is ..." + p) //last statement of expression is return value 

  //last statement of expression is return value 
  var q = { y = y * y; var x = y - 1; sqrt(y); println("Hello World"); }
  println("Value of q is ..." + q)
  //guess output
  q = { y = y * y; var x = y - 1; sqrt(y); y -= 1; }
  println("Value of q is ..." + q) //Assignment in Scala is of type Unit

  //demo for loop

  //just print 1 to 10
  for (a <- 1 to 10) {
    println(a)
  }

  //print possible combinations
  for (a <- 1 to 3; b <- 1 to 3)
    println(a + ":" + b)

  //print from list 
  val numList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
  for (a <- numList) {
    println(a)
  }
  //print even number 
  println("Printing even numbers...")
  for (a <- numList; if a % 2 == 0) {
    println(a)
  }
  println("Printing even numbers done...")

  //for loop and yield
  //get even numbers less than 8 
  println("Printing even numbers less than 8...")
  var retVal = for {
    a <- numList
    if a % 2 == 0; if a < 8
  } yield a

  for {a <- retVal}
    println(a)

  //double the numbers with yield
 printf("Double numbers with yield like n * %d \n",2)
    retVal = for (a <- numList) yield a * 2
  for (a <- retVal)
    println(a)

  //break loop when value is 6 in list
  val loop = new Breaks
  loop.breakable {
    println("Demo loop break");
    for (a <- numList) {
      if (a == 6)
        loop.break();
      else
        println(a);
    }
  }
  println("After loop ");

  //demo scope 
  println("demo scope");
  val x = 1
  def increment(y: Int): Int = y + 1
  var result = {
    val x = increment(3); //x here becomes 4
    x * x
  } + x // x here is 1
  println(result)

}

