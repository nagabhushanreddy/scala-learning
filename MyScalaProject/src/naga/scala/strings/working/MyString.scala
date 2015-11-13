package naga.scala.strings.working

class MyString {

  var str1 = "Hello Naga"

  println(str1);

  println(str1.concat(" and World."));
  println(str1 + " and World.");

  var floatVar = 12.456
  var intVar = 2000
  
  var fs = printf("The value of the float variable is " +
    "%f, while the value of the integer " +
    "variable is %d, and the string " +
    "is %s", floatVar, intVar, str1)
  println(fs)
  
  println(str1.charAt(6))
  println(str1.compareTo("Hello"))
  println(str1.compareToIgnoreCase("hello naga"))
  println(str1.contentEquals(new StringBuffer("Hello Naga")))  
  
  //Explaining immutable and mutable behavior with string objects and append action 
  //String object are immutable 
  var f1  = "Hello"
  var f2 = f1
  f2 += " World"
  println(f1+"\t"+f2)
  //String buffer is mutable 
  var f3 = new StringBuffer("Hello")
  var f4 = f3
  f4.append(" World")
   println(f3.toString() +"\t"+ f4.toString())
  

}

object MyObject {

  def main(args: Array[String]): Unit = {

    new MyString();
  }

}