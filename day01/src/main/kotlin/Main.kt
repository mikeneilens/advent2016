import kotlin.math.abs

enum class Orientation { North, South, East, West;
    fun rotateRight() = when(this) {
        North -> East
        East -> South
        South -> West
        West -> North
    }
    fun rotateLeft() = rotateRight().rotateRight().rotateRight()
}

data class Location(val x:Int = 0, val y:Int = 0, val orientation:Orientation = Orientation.North) {

    fun turn(direction:String) = if (direction == "R") turnRight() else turnLeft()
    fun turnRight() = copy(orientation = orientation.rotateRight())
    fun turnLeft() = copy(orientation = orientation.rotateLeft())

    fun move(distance:Int) = when(orientation) {
        Orientation.North -> copy(y = y - distance)
        Orientation.East -> copy (x = x + distance)
        Orientation.South -> copy(y = y + distance)
        Orientation.West -> copy(x = x - distance)
    }

    fun manhattenDistance() =  Pair(x, y).manhattenDistance()
}

fun Pair<Int,Int>.manhattenDistance() = abs(first) + abs(second)

data class Instruction(val direction:String, val distance:Int)

fun String.toInstruction() = Instruction(first().toString(), drop(1).toInt())

fun turnAndMove(locations:List<Location>, instruction:Instruction) = locations + locations.last().turn(instruction.direction).move(instruction.distance)

fun List<Instruction>.calculateBlocks(locations:List<Location> = listOf(Location())) = fold(locations, ::turnAndMove)

fun partOne(data:String) = data.split(", ").map(String::toInstruction).calculateBlocks().last().manhattenDistance()

data class Journey(val start:Location, val end:Location){
    fun xRange() = if (start.x == end.x) start.x..end.x else rangeOfValues(start.x, end.x)
    fun yRange() = if (start.y == end.y) start.y..end.y else rangeOfValues(start.y, end.y)
}

fun rangeOfValues(start:Int, end:Int) = if (start < end) (start + 1)..end else (start - 1) downTo end

fun toJourney(locations: List<Location>) = Journey(locations.first(), locations.last()) 

fun partTwo(data:String):Int {
    val locations = data.split(", ").map(String::toInstruction).calculateBlocks(listOf(Location()))
    val visitedLocations = mutableSetOf<Pair<Int, Int>>()
    locations.windowed(2,1).map(::toJourney).forEach { journey ->
        val locationAlreadyVisited = visitLocations(journey, visitedLocations)
        if (locationAlreadyVisited != null) return locationAlreadyVisited.manhattenDistance()
    }
    return 0
}

fun visitLocations(journey:Journey, visitedLocations:MutableSet<Pair<Int, Int>>):Pair<Int, Int>? {
    for (x in journey.xRange()) {
        for (y in journey.yRange()) {
            if (Pair(x, y) in visitedLocations) return Pair(x, y)
            visitedLocations.add(Pair(x, y))
        }
    }
    return null
}
