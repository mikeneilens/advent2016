fun String.isAbbaSequence() =  length == 4 && first() == last() && this[1] == this[2] && first() != this[1]

fun String.containsAbbaSequence() = windowed(4,1).any(String::isAbbaSequence)

data class TLSInfo(val strings:List<String> = listOf(""), val hyperNetStrings: List<String> = listOf(),val addToHyperNet:Boolean = false) {
    fun addToStringsOrHypernet(s:Char) = if (addToHyperNet) addToHypernet(s) else addToString(s)

    fun toggleOutput() = if (addToHyperNet) TLSInfo(strings + "", hyperNetStrings, false) else  TLSInfo(strings, hyperNetStrings + "", true)

    private fun addToHypernet(s:Char) = copy(hyperNetStrings = hyperNetStrings.addToLastElement(s))
    private fun addToString(s:Char) = copy(strings = strings.addToLastElement(s))

    fun supportsTls() = strings.any(String::containsAbbaSequence) && hyperNetStrings.none(String::containsAbbaSequence)
}

fun List<String>.addToLastElement(s:Char) = dropLast(1) + (last() + s)

fun parse(data:String, tlsInfo:TLSInfo = TLSInfo() ):TLSInfo {
    if (data.isEmpty()) return tlsInfo
    return when (val firstCharacter = data.first()) {
        '[',']' -> parse(data.drop(1), tlsInfo.toggleOutput() )
        else -> parse(data.drop(1), tlsInfo.addToStringsOrHypernet(firstCharacter) )
    }
}

fun String.supportsTLS():Boolean = parse(this).supportsTls()

fun partOne(data:List<String>) = data.count(String::supportsTLS)