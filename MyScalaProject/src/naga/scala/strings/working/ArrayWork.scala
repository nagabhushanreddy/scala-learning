package naga.scala.strings.working

import scala.Array._

class ArrayWork {
  var myArray = new Array[String](3)
  //var myArray:Array[String] = new Array[String](3)
  //var myArray = Array("John","Smith","Dora")
  myArray(0) = "John"
  myArray(1) = "Smith"
  myArray(2) = "Dora"
  // myArray(3) = "Bora"  //Exception: java.lang.ArrayIndexOutOfBoundsException: 3

  //foreach has Unit return
  myArray.foreach { x => { var y = "Hello " + x; println(y) } }

  //Modified Arrays with yield
  var modifiedArray: Array[String] = for (i <- myArray) yield { ("Hola " + i).toLowerCase() }
  for (i <- modifiedArray) println(i)

  println("Concatenate two Arrays")
  //concatenate Arrays
  myArray = concat(modifiedArray, myArray)
  for (i <- myArray) println(i)
  //Question: Why myArray(3) threw error, but now myArray is able to hold more than 3 elements? 

  //Appying a function with iterate 
  println("Applying a function on a value for n times...")
  myArray = iterate(myArray(0), 3)((x: String) => { ("Hello " + x).toUpperCase() })
  for (i <- myArray) println(i)

  //empty array
  myArray = empty
  println("Emptied Array, its length is:" + myArray.length)

  println("Multi-dimensional arrays")
  var myMatrix = ofDim[Int](4, 4)

  // build a matrix
  for (i <- 0 to 2) {
    for (j <- 0 to 2) {
      myMatrix(i)(j) = j;
    }
  }
  // Print two dimensional array
  for (i <- 0 to (myMatrix.length-1)) {
    for (j <- 0 to (myMatrix(0).length-1)) {
      print(" " + myMatrix(i)(j));
    }
    println();
  }

}

object ArrayObject {

  def main(args: Array[String]): Unit = {

    new ArrayWork()
  }

}