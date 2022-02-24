
val keypadP1 = """
        123
        456
        789
    """.trimIndent().split("\n")

val moves = mapOf('U' to Position(0, -1), 'D' to Position(0, +1), 'L' to Position(-1, 0), 'R' to Position(+1, 0))
fun Char.toMoves() = moves.getValue(this)

data class Position( val x:Int, val y:Int, val keypad:List<String> = keypadP1) {
    operator fun plus(other:Position):Position =
        if (copy(x = x + other.x, y = y + other.y).button != ' ') copy(x = x + other.x, y = y + other.y) else this

    val button = if (y in keypad.indices && x in 0 until keypad[y].length) keypad[y][x] else ' '
}

fun String.calcButton(start:Position = Position(1,1, keypadP1)) = map(Char::toMoves).fold(start){ position, move -> position + move}

fun partOne(data:List<String>, position:Position = Position(1,1, keypadP1)) =
      data.fold(listOf(position)) { positions, instructionText -> positions + instructionText.calcButton(positions.last()) }
          .map(Position::button)
          .joinToString("")
          .drop(1)

val keypadP2 = """
       ..1..
       .234.
       56789
       .ABC.
       ..D..
    """.trimIndent().replace('.',' ').split("\n")

fun partTwo(data:List<String>) = partOne(data ,  Position(0,2, keypadP2))





