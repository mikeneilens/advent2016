
data class Tree (val string:String, val marker:Marker? = null, val remainingTree:Tree? = null ) {
    fun length():Long {
        val markerLength = if (marker != null) marker.repeats * marker.tree.length()  else 0L
        val remainingTreeLength = remainingTree?.length() ?: 0L
        return string.length + markerLength + remainingTreeLength
    }
}

fun String.toTree(partTwo:Boolean = false):Tree {
    val (positionOfMarkerStart, positionOfMarkerEnd) = startAndEndOfMarker()
    if (positionOfMarkerStart < 0) return Tree(this)
    val leadingString = take(positionOfMarkerStart)
    val (markerLength, markerRepeats) = markerText(positionOfMarkerStart ,positionOfMarkerEnd).toMarkerRules()
    val marker = if (!partTwo)
        Marker(markerRepeats, Tree(markerString(positionOfMarkerEnd, markerLength)))
    else
        Marker(markerRepeats, markerString(positionOfMarkerEnd, markerLength).toTree(partTwo))
    val remainingString = drop(positionOfMarkerEnd + markerLength + 1 )
    val remainingTree = if (remainingString.isNotEmpty()) remainingString.toTree(partTwo) else null
    return Tree(leadingString, marker, remainingTree)
}

private fun String.startAndEndOfMarker() = Pair(indexOfFirst { it == '(' }, indexOfFirst {it == ')'})

private fun String.markerText(positionOfMarkerStart:Int, positionOfMarkerEnd:Int) = substring((positionOfMarkerStart + 1) until positionOfMarkerEnd)
private fun String.toMarkerRules() = Pair(split("x")[0].toInt(), split("x")[1].toLong() )

private fun String.markerString(indexOfMarkerEnd: Int, markerLength: Int) = substring((indexOfMarkerEnd + 1)..minOf(indexOfMarkerEnd + markerLength, lastIndex))

data class Marker(val repeats:Long, val tree:Tree)

fun partOne(data:String) = data.toTree().length()

fun partTwo(data:String) = data.toTree(partTwo = true).length()