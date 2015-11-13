package naga.scala.oops

class OopsDemo {
  println("This is OOPs demo..")
}

//Here is abstract class 
abstract class IntSet {
  //Here definition of methods exist but not their implementation 
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def inOrder(): List[Int]
  def preOrder(): List[Int]
  def postOrder(i: Int = 0): List[Int]
  def levelOrder(i: Int = 2): List[Int]
  def union(other: IntSet): IntSet

}

//Let's implement inheritance, here Empty inherits IntSet  
//Empty is Object instead of class, reason?

object Empty extends IntSet {

  //See here IntSet is return-type incl 
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def contains(x: Int): Boolean = false
  override def toString = "."
  def inOrder(): List[Int] = { List() }
  def preOrder(): List[Int] = { List() }
  def postOrder(i: Int = 0): List[Int] = { List() }
  def levelOrder(i: Int = 2): List[Int] = { List() }
  def union(other: IntSet): IntSet = other

}

class NonEmpty(elem: Int, left: IntSet = Empty, right: IntSet = Empty) extends IntSet {

  def contains(x: Int): Boolean = {
    if (x < elem) left contains (x) else if (x > elem) right contains (x) else
      true
  }

  //See here NonEmpty is return-type for incl, how is this allowed?  
  def incl(x: Int): NonEmpty = {
    if (x < elem) new NonEmpty(elem, left incl (x), right) else if (x > elem) new NonEmpty(elem, left, right incl (x)) else
      this
  }
  override def toString = "{" + left + "<--" + elem + "-->" + right + "}"

  def inOrder(): List[Int] = {
    left.inOrder() ::: List(elem) ::: right.inOrder()
  }

  def preOrder(): List[Int] = {
    List(elem) ::: left.preOrder() ::: right.preOrder()
  }
  def postOrder(i: Int = 0): List[Int] = {
    if (i == 0) {
      left.postOrder(1) ::: List(elem) ::: right.postOrder(1)
    } else
      left.postOrder(1) ::: right.postOrder(1) ::: List(elem)
  }

  def levelOrder(i: Int = 2): List[Int] = {
    if (i == 0) {
      List(elem)
    } else if (i == 1) {
      left.levelOrder(0) ::: right.levelOrder(0) ::: left.levelOrder(1) ::: right.levelOrder(1)
    } else {
      List(elem) ::: left.levelOrder(0) ::: right.levelOrder(0) ::: left.levelOrder(1) ::: right.levelOrder(1)
    }
  }

  def union(other: IntSet): IntSet = {
    (left union right) union other incl elem
  }
}

object OopsObject {

  def main(args: Array[String]): Unit = {

    var t1 = Empty
    var t2 = t1 incl (4) incl (2)
    t2 = t2 incl 6
    t2 = t2 incl 5
    t2 = t2 incl 3
    t2 = t2 incl 1
    t2 = t2 incl 6
    t2 = t2 incl 7
    t2 = t2 incl 9

    println(t2)
    println(t1 incl 1 incl 3 incl 4 incl 6 incl 2 incl 5)
    println(t2.contains(2))
    //println(t2.getClass)
    var numList = t2 inOrder

    for (i <- numList)
      println(i)

    print("In-Order --> ")
    println(numList)
    print("Pre-Order --> ")
    println(t2 preOrder)
    print("Post-Order --> ")
    println(t2.postOrder()) // try to put t2.postOrder instead of t2.postOrder() 
    print("Level-Order --> ")
    println(t2 levelOrder (2))

    var t3 = Empty incl 10 incl 20 incl 30
    println(t3 union t2 inOrder)

  }

}