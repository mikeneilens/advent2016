import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class MainTest: WordSpec( {

    "for part one" should {
        val bots = mutableMapOf<Int,Bot>()
        val bins = mutableMapOf<Int,Bin>()

        val sampleData = """
            value 5 goes to bot 2
            bot 2 gives low to bot 1 and high to bot 0
            value 3 goes to bot 1
            bot 1 gives low to output 1 and high to bot 0
            bot 0 gives low to output 2 and high to output 0
            value 2 goes to bot 2
        """.trimIndent().split("\n")

        "convert a bot instruction into a bot when both receivers are bots" {
            val bot = "bot 135 gives low to bot 27 and high to bot 166".createBot(bots, bins)
            bot.lowReceiver.type shouldBe ReceiverType.Bot
            bot.lowReceiver.number shouldBe 27
            bot.highReceiver.type shouldBe ReceiverType.Bot
            bot.highReceiver.number shouldBe 166
        }
        "convert a bot instruction into a bot when both receivers are bins" {
            val bot = "bot 207 gives low to output 16 and high to output 8".createBot(bots, bins)
            bot.lowReceiver.type shouldBe ReceiverType.Bin
            bot.lowReceiver.number shouldBe 16
            bot.highReceiver.type shouldBe ReceiverType.Bin
            bot.highReceiver.number shouldBe 8
        }
        "convert a bot instruction into a bot when low receiver is a bot high receiver is a bin" {
            val bot = "bot 208 gives low to bot 16 and high to output 8".createBot(bots, bins)
            bot.lowReceiver.type shouldBe ReceiverType.Bot
            bot.lowReceiver.number shouldBe 16
            bot.highReceiver.type shouldBe ReceiverType.Bin
            bot.highReceiver.number shouldBe 8
        }
        "convert a bot instruction into a bot when low receiver is a bin high receiver is a bot" {
            val bot = "bot 209 gives low to output 16 and high to bin 8".createBot(bots, bins)
            bot.lowReceiver.type shouldBe ReceiverType.Bin
            bot.lowReceiver.number shouldBe 16
            bot.highReceiver.type shouldBe ReceiverType.Bot
            bot.highReceiver.number shouldBe 8
        }
        "convert sample instructions into bots" {
            sampleData.createBots(bots, bins)
            bots[2] shouldBe Bot(mutableListOf(),Receiver(ReceiverType.Bot,1), Receiver(ReceiverType.Bot,0),bots, bins)
            bots[1] shouldBe Bot(mutableListOf(),Receiver(ReceiverType.Bin,1), Receiver(ReceiverType.Bot,0),bots, bins)
            bots[0] shouldBe Bot(mutableListOf(),Receiver(ReceiverType.Bin,2), Receiver(ReceiverType.Bin,0),bots, bins)
        }
        "process instructions using sample data" {
            val (resultingBots, resultingBins) = processInstructions(sampleData)
            resultingBins[0]?.microchips?.first() shouldBe 5
            resultingBins[1]?.microchips?.first() shouldBe 2
            resultingBins[2]?.microchips?.first() shouldBe 3
            resultingBots[2]?.microchips shouldBe listOf(5,2)
        }
        "find part one answer using sample data is 2" {
            partOne(sampleData, 2, 5) shouldBe 2
        }
        "find part one answer using puzzleInput is 93 " {
            partOne(puzzleInput, 17, 61) shouldBe 93
        }
    }
    "for part two" should ({
        "find answer using puzzle Input is 47101" {
            partTwo(puzzleInput) shouldBe 47101
        }
    })
})