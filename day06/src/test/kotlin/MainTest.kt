import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class MainTest: WordSpec ({
    val sampleData = """
        eedadn
        drvtee
        eandsr
        raavrd
        atevrs
        tsrnev
        sdttsa
        rasrtv
        nssdts
        ntnada
        svetve
        tesnvt
        vntsnd
        vrdear
        dvrsen
        enarar
    """.trimIndent().split("\n")

    "For part one" should ({
        "find most popular character in a list of characters" {
            listOf('a','b','c','a','d','b','e','b','z').mostPopularCharacter() shouldBe 'b'
        }
        "find most popular character in column 2 of the sample data is 's' " {
            sampleData.mostPopularCharacterForColumn(2) shouldBe 's'
        }
        "resulting word from sample data is easter"{
         sampleData.resultingWord() shouldBe "easter"
        }
        "result for part one using PuzzleInput is asvcbhvg" {
            partOne(puzzleInput) shouldBe "asvcbhvg"
        }
    })
})