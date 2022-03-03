data class Tree (val string:String, val marker:Marker? = null, val remainingTree:Tree? = null ) {
    fun length():Long {
        val markerLength = if (marker != null) marker.repeats * marker.tree.length()  else 0L
        val remainingTreeLength = remainingTree?.length() ?: 0L
        return string.length + markerLength + remainingTreeLength
    }
}

data class Marker(val repeats:Long, val tree:Tree)

fun String.toTree(isPartTwo:Boolean = false):Tree {
    val (positionOfMarkerStart, positionOfMarkerEnd) = startAndEndOfMarker()
    if (positionOfMarkerStart < 0) return Tree(this)
    val leadingString = take(positionOfMarkerStart)
    val (markerLength, markerRepeats) = toMarkerText(positionOfMarkerStart ,positionOfMarkerEnd).toMarkerRules()
    val marker = if (!isPartTwo)
        Marker(markerRepeats, Tree(stringForMarker(positionOfMarkerEnd, markerLength)))
    else
        Marker(markerRepeats, stringForMarker(positionOfMarkerEnd, markerLength).toTree(isPartTwo))
    val remainingString = drop(positionOfMarkerEnd + markerLength + 1 )
    val remainingTree = if (remainingString.isNotEmpty()) remainingString.toTree(isPartTwo) else null
    return Tree(leadingString, marker, remainingTree)
}

private fun String.startAndEndOfMarker() = Pair(indexOfFirst { it == '(' }, indexOfFirst {it == ')'})

private fun String.toMarkerText(positionOfMarkerStart:Int, positionOfMarkerEnd:Int) = substring((positionOfMarkerStart + 1) until positionOfMarkerEnd)
private fun String.toMarkerRules() = Pair(split("x")[0].toInt(), split("x")[1].toLong() )

private fun String.stringForMarker(indexOfMarkerEnd: Int, markerLength: Int) = substring((indexOfMarkerEnd + 1)..minOf(indexOfMarkerEnd + markerLength, lastIndex))

fun partOne(data:String) = data.toTree().length()

fun partTwo(data:String) = data.toTree(isPartTwo = true).length()