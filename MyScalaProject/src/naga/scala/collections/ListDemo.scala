package naga.scala.collections

import java.util.{ Date, Locale }
import math.Ordering

class ListDemo {

  val fruits = List("Apple", "Orange", "Banana", "Pear")
  val numbers = List(11, 23, 5, 6, 7, 9, 10, -9)

  //All list are constructed from empty List Nil

  var fruits2 = Nil.::("Pear").::("Banana").::("Orange").::("Apple")
  var fruits3 = "Apple" :: "Orange" :: "Banana" :: "Pear" :: Nil // convention operators ending with :  are right associated 

  println(fruits + "=" + fruits2 + "=" + fruits3)

  // Last element of List with pattern match 
  def last[T](xs: List[T]): T = xs match {
    case List()  => throw new Error("List is empty")
    case List(x) => x
    case y :: ys => last(ys)
  }

  def init[T](xs: List[T]): List[T] = xs match {
    case List()  => List() //or can throw an error
    case List(x) => List()
    case y :: ys => y :: init(ys)
  }

  def removeAt[T](xs: List[T], idx: Int): List[T] = xs match {
    case List() => xs
    case _      => xs.take(idx) ::: xs.drop(idx + 1) //wild char pattern matching
  }

  //implement insertion-sort on list
  def isort(xs: List[Int]): List[Int] = xs match {
    case List()  => List()
    case y :: ys => insert(y, isort(ys))
  }
  def insert(elem: Int, xs: List[Int]): List[Int] = xs match {
    case List()  => List(elem)
    case y :: ys => if (elem > y) y :: insert(elem, ys) else elem :: xs
  }

  //implement msort and see how list is split into pair

  def msort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case x :: xs => {
      val n = xs.length / 2
      var (first, second) = xs splitAt (n)
      def merge(xs: List[Int], ys: List[Int]): List[Int] = xs match { //See if we can improve merge code with pattern pair pattern match
        case List() => ys
        case x1 :: xs1 => ys match {
          case List()    => xs
          case y1 :: ys1 => if (x1 < y1) x1 :: merge(xs1, ys) else y1 :: merge(xs, ys1)
        }
      }
      merge(msort(first), msort(second))
    }
  }

  //implement generic msort 
  
  
  //pack consecutive same data together 
  def pack[T](xs:List[T]):List[List[T]]=xs match{
    case Nil => Nil
    case y::ys => val(first,rest) = xs span { x => y==x }; first::pack(rest)
  }

}

object ListDemo {
  def main(args: Array[String]): Unit = {
    val ld = new ListDemo()

    println("Head of fruits:" + ld.fruits.head)
    println("Tail of Head of fruits:" + ld.fruits.tail.head)
    println("Last element of fruits is: " + ld.last(ld.fruits))
    println("Init elements of fruits is: " + ld.init(ld.fruits))
    println("Remove 2nd element of fruits and get list: " + ld.removeAt(ld.fruits, 2))
    println("Sorted num list:" + ld.isort(ld.numbers))

    //List utility methods 
    println("Length fruits: " + ld.fruits.length)
    println("Last of fruits: " + ld.fruits.last)
    println("All fruits except last: " + ld.fruits.init)
    println("First 2 fruits: " + ld.fruits.take(2))
    println("Remaining fruits after dropping first 2: " + ld.fruits.drop(2))
    println("Concatinating Lists: " + (ld.fruits ++ List("Mango", "Pine-apple"))) //Alternatively :::
    println("Concatinating Lists: " + (ld.fruits :: List("Mango", "Pine-apple"))) //What happens here?
    println("Reverse fruits : " + ld.fruits.reverse)
    println("Update 3 element in fruits and get fruits : " + ld.fruits.updated(3, "Grape"))

    //Finding if elements exist in List
    println("Position of Orange in fruits:" + ld.fruits.indexOf("Orange"))
    println("Check if fruits contains  Orange:" + ld.fruits.contains("Grape"))

    //Introducing pairs and tuple
    var myPair: (Int, String) = (1200, "John Doe")
    var (id1, person1) = myPair
    var (id: Int, name: String, role: String, degree: String, doj: Date) = (1200, "John Doe", "Student", "Bachelors", new Date)
    println(myPair._1 + "," + person1)
    println(id + "," + doj.toString())

    println("Sorted num list by merge-sort:" + ld.isort(ld.numbers))

    println(ld.fruits.sorted)
    println(ld.fruits.sortBy(_.charAt(3)))
    println(ld.fruits.sortBy(_.length()))
    println(ld.fruits.sortBy(x => (x.length(), x)))
    println(ld.fruits.sortWith((left, right) => if (left.compareToIgnoreCase(right) > 0) true else false))

    // Higher-Order functions with lists
    //A function that takes function as parameter

    println(ld.numbers.map { x => x * x }.sorted)
    println(ld.fruits.map { x => x.toUpperCase() }.sorted)

    println(ld.numbers.filter { x => x > 0 }.sorted)
    println(ld.numbers.filterNot { x => x > 0 }.sorted)
    println(ld.fruits.filter { x => x.contains("a") }.sorted)
    println(ld.numbers.partition { x => x < 0 })
    
    val data = List("a", "a", "a", "b", "c", "c", "d", "d")
    println(data.takeWhile { x => x.equals("a") })
    println(data.dropWhile { x => x.equals("a") })
    println(data.span { x => x.equals("a")})
    println(ld.pack(data))
    
    println((data reduceLeft ((x,y)=>x+y) ))
    println(ld.numbers.reduceLeft(_+_))
    println(ld.numbers.reduceLeft(_*_))
    
    var emptyList:List[Int] = List()
    //println(emptyList.reduceLeft(_+_))
    println(emptyList.foldLeft(0)(_+_))
    println(emptyList.foldLeft(1)(_*_))
    
    //So reduceleft is applied on non-empty List
    //foldLeft can be applied on empty List, this uses the concept of accumulator which is first parameter passed
    
   println((data.foldLeft("start:")((x,y)=>x+y) )) 
   println((data.foldRight("start:")((x,y)=>x+y) )) 
    
    

  }
}

