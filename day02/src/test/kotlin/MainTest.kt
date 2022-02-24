import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class MainTest: WordSpec ({
    val sampleData = """
        ULL
        RRDDD
        LURDL
        UUUUD
    """.trimIndent().split("\n")

    "When trying part one " should ({
        "when moving in the grid don't go outside of boundaries" {
            Position(1,1) + Position(1, 0) shouldBe Position(2,1)
            Position(1,1) + Position(-1, 0) shouldBe Position(0,1)
            Position(1,1) + Position(2, 0) shouldBe Position(1,1)
            Position(1,1) + Position(-2, 0) shouldBe Position(1,1)
            Position(1,1) + Position(0, 1) shouldBe Position(1,2)
            Position(1,1) + Position(0, -1) shouldBe Position(1,0)
            Position(1,1) + Position(0, 2) shouldBe Position(1,1)
            Position(1,1) + Position(0, -2) shouldBe Position(1,1)
        }
        "button represented by each position" {
            Position(1,1, keypadP1).button shouldBe '5'
            Position(0,0, keypadP1).button shouldBe '1'
            Position(2,2, keypadP1).button shouldBe '9'
        }
        "Sequence ULL leads to button 1" {
            "ULL".calcButton().button shouldBe '1'
        }
        "the sample data leads to number 1985" {
            partOne(sampleData) shouldBe "1985"
        }
        "using the puzzle input" {
            partOne(puzzleInput) shouldBe "12578"
        }
    })
    "when trying part two " should {
        "button represented by each position"{
            Position(2,0, keypad).button shouldBe '1'
            Position(0,2, keypad).button shouldBe '5'
            Position(2,2, keypad).button shouldBe '7'
            Position(2,4, keypad).button shouldBe 'D'
        }
        "the sample data leads to number 5DB3" {
            partTwo(sampleData) shouldBe "5DB3"
        }
        "using the puzzle input" {
            partTwo(puzzleInput) shouldBe "516DD"
        }
    }
})