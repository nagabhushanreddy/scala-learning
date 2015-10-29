package naga.scala.rational.algebra

class Rational(numer: Int, denom: Int) {

  def gcd(a:Int,b:Int):Int={
    if (b == 0)
        a
    else
        gcd(b, a % b)
  }
  
  val g = gcd(numer,denom)
  var numerator = numer/g;
  var denominator = denom/g;

  override def toString(): String = {
    numerator + "/" + denominator
  }
  def add(that: Rational): Rational = {
    new Rational(this.numerator * that.denominator + that.numerator * this.denominator, this.denominator * that.denominator);
  }

  def neg(): Rational = {
    new Rational(this.numerator * -1, this.denominator)
  }

}

object MyObject {

  def main(args: Array[String]): Unit = {

    var rat1 = new Rational(49, 70);
    println(rat1.toString());
    var rat2 = new Rational(2, 3);
    
    println(rat1.add(rat2).toString());

    println(rat1.neg().toString());

    println(rat1.add(rat2.neg()).toString());
    
    println( rat1 add rat2 toString);

  }
}
