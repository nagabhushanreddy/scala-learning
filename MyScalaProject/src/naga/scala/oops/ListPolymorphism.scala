package naga.scala.oops

class ListPolymorphism {

}

//[T] is type parameter 
trait myList[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: myList[T]
  
  def nth(n: Int): T = {
    if (n == 0) head
    else
      tail nth (n - 1)
  }

  def add[U >: T] (elem: U):myList[U] = {
    if(isEmpty){
     new Cons(elem, new Nil)  
    }else{
     new Cons(head, tail.add(elem))
    }
  }
  
   def prepend[U >: T] (elem: U):myList[U] = {
     new Cons(elem, this)
  }
   
 /* def last[T](xs:myList[T]):T = xs match {
   // case myList() => throw new Error("List is empty")
    //case myList(x) => x
    //case y :: ys => last(ys)
  }
  
  */
  
  override def toString = {
    if(isEmpty){
      "."
    }else{
      head.toString() + "->" + tail toString();
    }
    
  }

}

class Cons[+T](val head: T, val tail: myList[T]) extends myList[T] {

  def isEmpty = false

}

class Nil[T]() extends myList[T] {
  def isEmpty = true
  def head:Nothing = throw new NoSuchElementException("Nil.head")
  def tail:Nothing = throw new NoSuchElementException("Nil.tail")
}

object ListByPolymorphism {

  def main(args: Array[String]): Unit = {

    val nil = new Nil();

    var numList1 = new Cons[Int](1, nil) 
    println("numList1 is:" + numList1.add(2).toString())
    println("numList1 1st element:" + numList1.nth(0))
    var num2 = numList1 add 7 add 8 add 9 
    println("num2 is: "+num2)
    
    var strngList1= new Cons("Naga", new Nil)
    println("strng1 is:" + strngList1.add("A").toString())
    
    //Here it is demonstrated List is implemented for Integers and Strings achieving polymorphism 
    
  }

}