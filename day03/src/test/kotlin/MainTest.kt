import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class MainTest:WordSpec( {
    "with part one" should({
        "obtain number in each column of the string containing numbers" {
            "541  588  421".numberInColumn(0) shouldBe 541
            "541  588  421".numberInColumn(1) shouldBe 588
            "541  588  421".numberInColumn(2) shouldBe 421
        }
        "convert string containing 541  588  421 into an ascending list of numbers" {
            "541  588  421".toSides() shouldBe listOf(421, 541, 588)
        }
        "list of 421, 541, 588 is a valid triangle" {
            isValidTriangle(listOf(421, 541, 588)) shouldBe true
        }
        "list of 5, 10, 25 is not a valid triangle" {
            isValidTriangle(listOf(5, 10, 25)) shouldBe false
        }
        "test with puzzle input " {
            partOne(puzzleInput) shouldBe 993
        }
    })
    "with part two" should({
        "convert vertical numbers into a list of numbers for each column" {
            val sampleData = """
                101  301  501
                102  302  502
                103  303  503
                201  401  601
                202  402  602
                203  403  603
            """.trimIndent().split("\n")
            sampleData.toInts(0) shouldBe listOf(101, 102, 103, 201, 202, 203)
            sampleData.toInts(1) shouldBe listOf(301, 302, 303, 401, 402, 403)
            sampleData.toInts(2) shouldBe listOf(501, 502, 503, 601, 602, 603)
        }
        "test with puzzle input " {
            partTwo(puzzleInput) shouldBe 1849
        }
    })
})