
typealias Bots = MutableMap<Int, Bot>

fun Bots.bot(number:Int) = getValue(number)

typealias Bins = MutableMap<Int, Bin>

fun Bins.bin(number:Int) =
    if (contains(number)) getValue(number)
    else {
        val newBin = Bin(mutableListOf())
        this[number] = newBin
        newBin}

enum class ReceiverType{Bot, Bin}

data class Receiver(val type:ReceiverType, val number:Int)

class Bot(val microchips:MutableList<Int>, val lowReceiver:Receiver, val highReceiver:Receiver, bots: Bots, bins: Bins  ) {

    val addChip = { chip:Int -> addChip(chip, bots, bins)  }

    private fun addChip(chip: Int, bots: Bots, bins: Bins) {
        microchips.add(chip)
        if (microchips.size == 2) {
            addChipToBotOrBin(microchips.minOf { it },lowReceiver, bots, bins)
            addChipToBotOrBin(microchips.maxOf { it },highReceiver, bots, bins)
        }
    }

    private fun addChipToBotOrBin(chip:Int, receiver:Receiver, bots: Bots, bins: Bins) {
        if (receiver.type == ReceiverType.Bot)
            bots.bot(receiver.number).addChip(chip)
        else
            bins.bin(receiver.number).addChip(chip)
    }

    override fun toString(): String = "microchips=$microchips lowTo = $lowReceiver highTo = $highReceiver"

    override fun equals(other: Any?): Boolean = other is Bot && microchips == other.microchips && lowReceiver == other.lowReceiver && highReceiver == other.highReceiver
}

data class Bin(val microchips:MutableList<Int>) {
    fun addChip (chip: Int) = microchips.add(chip)
}

fun List<String>.createBots(bots: Bots, bins: Bins) {
    forEach { string ->
        if (string.startsWith("bot ")) {
            val botNumber = string.removePrefix("bot ").split(" ")[0].toInt()
            val bot = string.createBot(bots, bins)
            bots[botNumber] = bot
        }
    }
}

fun List<String>.assignValues(bots: Bots) {
    forEach { string ->
        if (string.startsWith("value")) {
            val microchip = string.split(" ")[1].toInt()
            val botNumber = string.split(" ")[5].toInt()
            bots.bot(botNumber).addChip(microchip)
        }
    }
}

fun String.createBot(bots: Bots, bins: Bins )= Bot(mutableListOf(), createReceiver(5), createReceiver(10), bots, bins)

fun String.createReceiver(offset:Int):Receiver {
    val receiverType = if (split(" ")[offset] == "output") ReceiverType.Bin else ReceiverType.Bot
    val number = split(" ")[offset +1 ].toInt()
    return Receiver(receiverType, number)
}

fun processInstructions(data:List<String>):Pair<Bots, Bins> {
    val bots = mutableMapOf<Int, Bot>()
    val bins = mutableMapOf<Int, Bin>()
    data.createBots(bots, bins)
    data.assignValues(bots)
    return Pair(bots, bins)
}

fun partOne(data:List<String>, val1:Int, val2:Int):Int {
    val (bots, _) = processInstructions(data)
    return bots.toList().first { it.second.microchips.sorted() == listOf(val1, val2) }.first
}

fun partTwo(data:List<String>):Int {
    val (_, bins) = processInstructions(data)
    return bins.bin(0).microchips.first() * bins.bin(1).microchips.first() * bins.bin(2).microchips.first()
}