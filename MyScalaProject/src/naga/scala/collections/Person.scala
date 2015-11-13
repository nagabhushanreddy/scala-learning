package naga.scala.collections

class Person(val id:Int, val name:String,val address:String,val gender:String) {
  
 override def toString() = "{"+"id:"+id+","+"name:"+name+","+"address:"+address+","+"gender:"+gender+"}"
  
}

object PersonCollection{
  def main(args:Array[String]):Unit={
    var personList:List[Person] = List()
    
    for (i <-1 to 10){
     personList = personList.::(new Person(i,"Name-"+i,"Address"+i,(if(i%3==0)"F" else"M")))
    }
    var person1 = personList.last
    
    println(  personList map { x => x.name + "-->" + x.gender } )
    val myFilter = (x:Person)=> x.gender.equals("F") || x.address.contains("2") 
    println(  personList filter { myFilter } )
    
  }
}