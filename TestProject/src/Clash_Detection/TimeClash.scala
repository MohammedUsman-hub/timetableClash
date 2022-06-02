package Clash_Detection

case class TimeClash(id: Int,day:String,hour:Int,year:Int,term: String) {


  def clash(other: TimeClash): Boolean = {
    return ((this != other) &&
      (this.day == other.day) && (this.hour == other.hour)
      && (this.id == other.id) && (this.term == other.term))
  }

  def clash_free(other: TimeClash): Boolean = {
    return ((this != other) &&
      (this.day == other.day) && (this.hour == other.hour)
      && (this.year != other.year) && (this.term == other.term))

  }
}







