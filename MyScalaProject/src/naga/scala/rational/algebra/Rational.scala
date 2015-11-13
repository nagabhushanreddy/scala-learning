package naga.scala.rational.algebra

class Rational(numer: Int, denom: Int) {

  //Adding constraints, uncomment to check  
  require(denom != 0, "Denominator must be greater than zero")

  //constructor overloading
  def this(numer: Int) = this(numer, 1)
  def this(numer: Int, denom: Int, f: Int => Int) = this(f(numer), f(denom))

  def gcd(a: Int, b: Int): Int = {
    if (b == 0)
      a
    else
      gcd(b, a % b)
  }
  
  def denominatorCheck() = {
    if(denom == 0) error ("Denominator must be greater than zero")
  }
  
  def error(msg:String) = throw new Error(msg)
  
  denominatorCheck()
  val g = gcd(numer, denom)
  var numerator = numer / g;
  var denominator = denom / g;

  override def toString(): String = {
    numerator.toString() + "/" + denominator
  }
  def add(that: Rational): Rational = {
    new Rational(this.numerator * that.denominator + that.numerator * this.denominator, this.denominator * that.denominator);
  }

  def neg(): Rational = {
    new Rational((this.numerator * -1), this.denominator)
  }
  
  def sub(that: Rational):Rational = {
    this.add(that.neg())
  }

}

object Rational {

  def main(args: Array[String]): Unit = {

    var rat1 = new Rational(49, 70);
    println(rat1.toString());
    println(rat1.numerator);
    println(rat1.denominator);
    
    var rat2 = new Rational(2, 3);

    println(rat1.add(rat2).toString());
    println(rat1.neg().toString());
    println(rat1.add(rat2.neg()).toString());
    println(rat1.sub(rat2).toString());
    println(rat1 add rat2 toString);
    
    //Uncomment below and see it throws error
    try{
      var ratE = new Rational(2, 0);  
    }catch {
      case t: Throwable => t.printStackTrace(); // TODO: handle error
                           println("Error occurred")    
    }
    
    
    //Applies default denominator 
    var rat3 = new Rational(2);
    println(rat3.toString());

    var rat4 = new Rational(2, 5, (x: Int) => x * x);
    println(rat4.toString());
    
    //Below throws an error as there is no matching constructor  
    //var rat5 = new Rational();
    
    //Applying partial logic 
    var rat6 = new Rational(3,_:Int);
    println(rat6(5).toString());

  }
}
