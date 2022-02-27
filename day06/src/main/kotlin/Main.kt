

fun List<Char>.mostPopularCharacter() = groupingBy { it }.eachCount().toList().sortedBy { - it.second }.map{it.first}.first()

fun List<String>.mostPopularCharacterForColumn(column:Int) = map{it[column]}.mostPopularCharacter()

fun List<String>.resultingWord() = (0..first().lastIndex).map{column -> this.mostPopularCharacterForColumn(column)}.joinToString("")

fun partOne(data:List<String>) = data.resultingWord()