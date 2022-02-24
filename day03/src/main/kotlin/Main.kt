fun String.numberInColumn(column:Int) = substring((column * 5)..(column * 5 + 2)).trim().toInt()

fun String.toSides() = (0..2).map {column -> numberInColumn(column)}.sorted()

fun isValidTriangle(sides:List<Int>) = sides.size == 3 && sides[0] + sides[1] > sides[2]

fun partOne(data:List<String>) = data.map(String::toSides).count(::isValidTriangle)

fun List<String>.toInts(column:Int) = map{ it.substring((column * 5)..(column * 5 + 2) ).trim().toInt()}

fun partTwo(data:List<String>) = (0..2).flatMap{column -> data.map{row -> row.numberInColumn(column)} }
    .chunked(3)
    .map{sides -> sides.sorted()}
    .count(::isValidTriangle)