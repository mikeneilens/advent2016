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
    if (!contains("(")) return Leaf(this)
    val (positionOfMarkerStart, positionOfMarkerEnd) = startAndEndOfMarker()
    val (marker, markerLength) = getMarkerAndLength(positionOfMarkerStart, positionOfMarkerEnd, isPartTwo)
    val leadingString = take(positionOfMarkerStart)
    if (positionOfMarkerEnd + markerLength + 1 >= length) return LastTree(leadingString, marker)
    val remainingTree = drop(positionOfMarkerEnd + markerLength + 1 ).toTree(isPartTwo)
    return Tree(leadingString, marker, remainingTree)
}

private fun String.getMarkerAndLength(positionOfMarkerStart: Int, positionOfMarkerEnd: Int, isPartTwo: Boolean):Pair<Marker, Int> {
    val (markerLength, markerRepeats) = toMarkerText(positionOfMarkerStart, positionOfMarkerEnd).toMarkerRules()
    return if (!isPartTwo)
        Pair(Marker(markerRepeats, Leaf(stringForMarker(positionOfMarkerEnd, markerLength))), markerLength)
    else
        Pair(Marker(markerRepeats, stringForMarker(positionOfMarkerEnd, markerLength).toTree(isPartTwo)), markerLength)
}

private fun String.startAndEndOfMarker() = Pair(indexOfFirst { it == '(' }, indexOfFirst {it == ')'})

private fun String.toMarkerText(positionOfMarkerStart:Int, positionOfMarkerEnd:Int) = substring((positionOfMarkerStart + 1) until positionOfMarkerEnd)
private fun String.toMarkerRules() = Pair(split("x")[0].toInt(), split("x")[1].toLong() )

private fun String.stringForMarker(indexOfMarkerEnd: Int, markerLength: Int) = substring((indexOfMarkerEnd + 1)..minOf(indexOfMarkerEnd + markerLength, lastIndex))

fun partOne(data:String) = data.toTree().length()

fun partTwo(data:String) = data.toTree(isPartTwo = true).length()