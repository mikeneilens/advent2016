fun String.isAbbaSequence() =  length == 4 && first() == last() && this[1] == this[2] && first() != this[1]

fun String.containsAbbaSequence() = windowed(4,1).any(String::isAbbaSequence)

fun String.abaSequences() =
    this.windowed(3,1).fold(listOf<String>()){sequences, string ->
        if (string.length == 3 && string[0] == string[2] && string[0] != string[1]) sequences + string else sequences }

fun String.toBABSequence():String = "${this[1]}${this[0]}${this[1]}"

data class TLSInfo(val strings:List<String> = listOf(), val hyperNetStrings: List<String> = listOf(), private val addToHyperNet:Boolean = false) {
    fun add(s:Char) = if (addToHyperNet) addToHypernet(s) else addToString(s)

    fun toggleOutput() = if (addToHyperNet) TLSInfo(strings + "", hyperNetStrings, false) else  TLSInfo(strings, hyperNetStrings + "", true)

    private fun addToHypernet(char:Char) = copy(hyperNetStrings = hyperNetStrings.addToLastString(char))
    private fun addToString(char:Char) = copy(strings = strings.addToLastString(char))

    fun supportsTls() = strings.any(String::containsAbbaSequence) && hyperNetStrings.none(String::containsAbbaSequence)

    fun supportsSSL():Boolean {
        val abaSequences = strings.flatMap(String::abaSequences)
        return hyperNetStrings.any{hypernetString -> abaSequences.any{ sequence -> sequence.toBABSequence() in hypernetString} }
    }
}

fun List<String>.addToLastString(char:Char) = if (isEmpty()) listOf("$char") else dropLast(1) + (last() + char)

fun String.toTLSInfo() = fold(TLSInfo()){tlsInfo, char ->
    if (char in listOf( '[',']') ) tlsInfo.toggleOutput() else tlsInfo.add(char)
}

fun String.supportsTLS():Boolean = toTLSInfo().supportsTls()

fun partOne(data:List<String>) = data.count(String::supportsTLS)

fun String.supportsSSL() = toTLSInfo().supportsSSL()

fun partTwo(data:List<String>) = data.count(String::supportsSSL)