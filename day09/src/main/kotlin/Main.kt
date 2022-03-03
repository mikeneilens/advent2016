interface HasLength {
    fun length():Long
}

data class Tree (val string:String, val marker:Marker, val remainingTree:HasLength  ):HasLength {
    override fun length():Long {
        val remainingTreeLength = remainingTree.length()
        return string.length + marker.repeats * marker.tree.length() + remainingTreeLength
    }
}

data class LastTree(val string:String, val marker:Marker):HasLength {
    override fun length() = string.length.toLong() + marker.repeats * marker.tree.length()
}

data class Leaf(val string:String):HasLength {
    override fun length() = string.length.toLong()
}

data class Marker(val repeats:Long, val tree:HasLength)

fun String.toTree(isPartTwo:Boolean = false):HasLength {
    val (positionOfMarkerStart, positionOfMarkerEnd) = startAndEndOfMarker()
    if (positionOfMarkerStart < 0) return Leaf(this)
    val leadingString = take(positionOfMarkerStart)
    val (markerLength, markerRepeats) = toMarkerText(positionOfMarkerStart ,positionOfMarkerEnd).toMarkerRules()
    val marker = if (!isPartTwo)
        Marker(markerRepeats, Leaf(stringForMarker(positionOfMarkerEnd, markerLength)))
    else
        Marker(markerRepeats, stringForMarker(positionOfMarkerEnd, markerLength).toTree(isPartTwo))
    val remainingString = drop(positionOfMarkerEnd + markerLength + 1 )
    if (remainingString.isEmpty()) return LastTree(leadingString, marker)
    val remainingTree = remainingString.toTree(isPartTwo)
    return Tree(leadingString, marker, remainingTree)
}

private fun String.startAndEndOfMarker() = Pair(indexOfFirst { it == '(' }, indexOfFirst {it == ')'})

private fun String.toMarkerText(positionOfMarkerStart:Int, positionOfMarkerEnd:Int) = substring((positionOfMarkerStart + 1) until positionOfMarkerEnd)
private fun String.toMarkerRules() = Pair(split("x")[0].toInt(), split("x")[1].toLong() )

private fun String.stringForMarker(indexOfMarkerEnd: Int, markerLength: Int) = substring((indexOfMarkerEnd + 1)..minOf(indexOfMarkerEnd + markerLength, lastIndex))

fun partOne(data:String) = data.toTree().length()

fun partTwo(data:String) = data.toTree(isPartTwo = true).length()