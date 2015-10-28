package naga.scala.basic.demo

import scala.util.control._

class Outer {
  class Inner {
     def  f() { println("I am function f Inner class...") }
    class InnerMost {
       f() // OK
    }
  }
  (new Inner).f() // OK because now f() is public
  
}



  object MyScalaObject {
    //demo access modifiers 
    (new Outer);
    def main(args: Array[String]): Unit = {
      (new BasicsDemo);
      println("Command Arguments are:" + args(0))
    }
  }

