package naga.scala.functions.demo

import java.util.Date

object FunctionDemo {

  println("Welcome Tectoro")                      //> Welcome Tectoro

  def addInt(a: Int, b: Int): Int = {
    var sum: Int = 0
    sum = a + b
    return sum
  }                                               //> addInt: (a: Int, b: Int)Int

  def constOne(a: Int, b: Int): Int = 1           //> constOne: (a: Int, b: Int)Int

  def constOneByName(a: Int, b: => Int): Int = 1  //> constOneByName: (a: Int, b: => Int)Int

  def loop(): Int = loop                          //> loop: ()Int

  //call by value
  addInt(6, 7)                                    //> res0: Int = 13
  constOne(1 + 6, 2)                              //> res1: Int = 1

  //call by name vs call by value difference
  println("Demo call by name...")                 //> Demo call by name...
  constOneByName(1, loop)                         //> res2: Int = 1

  //uncomment to check
  println("Demo call by value...")                //> Demo call by value...
  // constOne(1, loop)

  // functions with named arguments
  def funcNamed(a: Int, b: Int) {
    printf("Value of a is %d and value of b is %d \n", a, b)
  }                                               //> funcNamed: (a: Int, b: Int)Unit
  funcNamed(b = 9, a = 10)                        //> Value of a is 10 and value of b is 9 

  //Return-type is not mandatory if function in not recursive
  def fac(n: Int) = {
    var r = 1
    for (i <- 1 to n) r = r * i
    r
  }                                               //> fac: (n: Int)Int

  fac(5);                                         //> res3: Int = 120

  //Uncomment to check if it is true to check recursive functions need return type

  /*
  def newFac(n: Int) =  {
  if (n <= 0) 1 else n * newFac(n - 1)
  }
  */

  //default arugments
  def decorate(value: String, left: String = "[", right: String = "]"): String = {
    left + value + right
  }                                               //> decorate: (value: String, left: String, right: String)String

  decorate("Naga")                                //> res4: String = [Naga]
  decorate("Naga", ">>>[")                        //> res5: String = >>>[Naga]
  decorate("Naga", ">>>{", "}<<<")                //> res6: String = >>>{Naga}<<<

  //variable arugments
  def sum(xargs: Int*): Int = {
    var sum = 0
    for (a <- xargs) sum = sum + a
    sum
  }                                               //> sum: (xargs: Int*)Int
  sum(4, 5, 7, 8, 9)                              //> res7: Int = 33
  sum(4, 5, 7)                                    //> res8: Int = 16
  //passing a list to variable parameter function
  var numList = 1 to 6                            //> numList  : scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5
                                                  //| , 6)
  sum(numList: _*)                                //> res9: Int = 21

  //Recursive functions
  def fact(n: Int): Int = {
    if (n == 1) 1 else n * fact(n - 1)
  }                                               //> fact: (n: Int)Int

  fact(5)                                         //> res10: Int = 120
  // fact(-5)

  //tail recursion function
  //Nested function
  def factorial(a: Int): Int = {
    def fac(acc: Int, x: Int): Int = if (x == 1) acc else fac(acc * x, x - 1)
    fac(1, a)
  }                                               //> factorial: (a: Int)Int
  factorial(5)                                    //> res11: Int = 120

  def sumTailRecur(a:Int, b:Int) :Int = {
  	def sumt(acc: Int, x: Int, y: Int): Int = if (x > y) acc else sumt(acc + x, x + 1, y)
  	sumt(0,a,b);
  	 
  }                                               //> sumTailRecur: (a: Int, b: Int)Int
  
  sumTailRecur(1,6);                              //> res12: Int = 21
  
  //Higher order function, a function that takes function as parameter or returns function
  def highOrderSum(f: Int => Int, x: Int, y: Int): Int = {
    def sumt(acc: Int, x: Int, y: Int): Int = if (x > y) acc else sumt(acc + f(x), x + 1, y)
    sumt(0, x, y)
  }                                               //> highOrderSum: (f: Int => Int, x: Int, y: Int)Int

  //Anonymous functions or functions as literals
  val cube = (x: Int) => { x * x * x}             //> cube  : Int => Int = <function1>
  cube(3)                                         //> res13: Int = 27

  highOrderSum((x: Int) => x, 1, 5)               //> res14: Int = 15
  highOrderSum((x: Int) => x, 5, 1)               //> res15: Int = 0
  highOrderSum(cube, 1, 3)                        //> res16: Int = 36

  //sum of even numbers between a range
  highOrderSum(((x: Int) => if (x % 2 == 0) x else 0), 1, 10)
                                                  //> res17: Int = 30

  //partially applied functions

  val sumOfCubes = highOrderSum(cube, _: Int, _: Int)
                                                  //> sumOfCubes  : (Int, Int) => Int = <function2>
  sumOfCubes(1, 3)                                //> res18: Int = 36

  //function Currying
  def curriedSum(f: Int => Int)(x: Int, y: Int): Int = {
    def sumt(acc: Int, x: Int, y: Int): Int = if (x > y) acc else sumt(acc + f(x), x + 1, y)
    sumt(0, x, y)
  }                                               //> curriedSum: (f: Int => Int)(x: Int, y: Int)Int
  curriedSum(cube)(1, 3)                          //> res19: Int = 36

  //procedure = function without return type, this is equivalent to   def printLog(msg:String):Unit=
  def printLog(msg: String) {
    println(decorate(new Date().toString(), "[", "]>>>>") + msg);
  }                                               //> printLog: (msg: String)Unit
  printLog("Hello Scala")                         //> [Wed Nov 11 12:38:24 GMT+05:30 2015]>>>>Hello Scala

  //lazy valuation, its initialization is deferred until it is accessed for first time

  lazy val lazyGet6 = () => { printf("I am lazy...\n"); 6 }
                                                  //> lazyGet6: => () => Int
  val get6 = () => { printf("I am not lazy...\n"); 6 }
                                                  //> get6  : () => Int = <function0>

  def lazyExplore6(x: Int): Int = {
    if (x == 6) cube(lazyGet6()) else cube(x - 1)
  }                                               //> lazyExplore6: (x: Int)Int

  def explore6(x: Int): Int = {
    if (x == 6) cube(get6()) else cube(x - 1)
  }                                               //> explore6: (x: Int)Int

  lazyExplore6(5)                                 //> res20: Int = 64
  lazyExplore6(6)                                 //> I am lazy...
                                                  //| res21: Int = 216
  explore6(5)                                     //> res22: Int = 64
  explore6(6)                                     //> I am not lazy...
                                                  //| res23: Int = 216
                                                  

}