import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class MainTest: WordSpec ({
    val puzzleInput = "ojvtpuvg"
    "for part one" should ({
        "the integer used in the hash of abc+integer that creates a hash with five leading zeros is 3231929" {
              lowestMD5HashWithPrefix("abc", "00000", validator = ::validExpression) shouldBe 3231929
        }
        "the second integer used in the hash of abc+integer that creates a hash with five leading zeros is 5017308" {
            val firstInteger = lowestMD5HashWithPrefix("abc", "00000", validator = ::validExpression)
            lowestMD5HashWithPrefix("abc", "00000", firstInteger + 1, validator = ::validExpression) shouldBe 5017308
        }
        "sixth character of hash of abc3231929 is 1" {
            sixthDigit("abc", 3231929 ) shouldBe '1'
        }
        "password for seed abc is 18f47a30" {
            findPassword("abc","00000") shouldBe "18f47a30"
        }
        "result for puzzle input is 4543c154" {
            partOne(puzzleInput) shouldBe "4543c154"
        }
    })

    "for part two " should ({
        "digit and position of password character using abc3231929 as the key is 5,1" {
            digitAndPosition("abc",3231929) shouldBe Pair('5',1)
        }
        "password for seed abc is 05ace8e3" {
            findPasswordPartTwo("abc","00000") shouldBe "05ace8e3"
        }
        "result for puzzle input is 1050cbbd" {
            partTwo(puzzleInput) shouldBe "1050cbbd"
        }
    })
})