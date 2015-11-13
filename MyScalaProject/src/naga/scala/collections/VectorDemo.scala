package naga.scala.collections

object VectorDemo {

  //vectors are similar to list but their implementation is like trees with 32 bit arrays
  //They are fast in retrival than lists

  val fruits = Vector("Apple", "Orange", "Banana", "Pear")
  val numbers = Vector(11, 23, 5, 6, 7, 9, 10, -9)

  def main(args: Array[String]): Unit = {
    println(fruits.sortBy(_.charAt(3)))

    println(fruits.exists { x => x.equalsIgnoreCase("Orange") })
    println(fruits.forall { x => x.toLowerCase().contains("a") })
    println(fruits.forall { x => x.toLowerCase().contains("e") })
    val pairs = fruits zip (numbers)
    println(pairs)
    println(pairs.unzip)
    println((fruits flatMap { x => List("fruit:" + x) }))

    var someFruits = List("Tomoto", "Pineapple", "Litchi")
    println(List(fruits, someFruits))
    println(List(fruits, someFruits).flatten) //flatMap = map + flatten

    //Generate pairs 
    println((1 until 5) map (i => (1 until 5) map (j => (i, j))) flatten)

    //Now exercise 
    // 1 <=j < i < n and where i+j is a prime-number
    def isPrime(n: Int): Boolean = {
      !(2 until n).exists(n % _ == 0)
    }
    val n = 10
    println((1 until n) flatMap { i => (1 until i) map (j => (j, i)) } filter (pair => isPrime(pair._1 + pair._2)))

    //Remember for expression and yield, let's try that for this problem
    println ( for {
      i <- 1 until n;
      j <- 1 until i;
      if isPrime(i + j)
    } yield (j, i) )

  }

}