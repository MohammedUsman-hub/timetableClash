package Clash_Detection

data class KotlinClashDetection2(val id: Int, val day: String, val hour: Int, val year: Int, val term: Int, val compulsory:Boolean) {
    fun clash(other: KotlinClashDetection2): Boolean {
        return ((this != other) &&
                (this.day == other.day) && (this.hour == other.hour)
                && (this.id == other.id) && (this.term == other.term))
    }



    fun clashfree(other: KotlinClashDetection2): Boolean {
        return this != other &&
                this.day == other.day && this.hour == other.hour
                && this.year == other.year && this.term == other.term
    }

}