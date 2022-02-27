
fun List<Char>.mostOrLeastPopularCharacter(mostPopular:Boolean) =
    groupingBy { it }
        .eachCount().toList()
        .sortedBy { if (mostPopular) - it.second else it.second }
        .map{it.first}.first()

fun List<String>.mostOrLeastPopularCharacterForColumn(column:Int, mostPopular:Boolean) =
    map{it[column]}
        .mostOrLeastPopularCharacter(mostPopular)

fun List<String>.resultingWord(mostPopular:Boolean) =
    (0..first().lastIndex).map{column -> this.mostOrLeastPopularCharacterForColumn(column, mostPopular)}
        .joinToString("")

fun partOne(data:List<String>) = data.resultingWord(mostPopular = true)

fun partTwo(data:List<String>) = data.resultingWord(mostPopular = false)