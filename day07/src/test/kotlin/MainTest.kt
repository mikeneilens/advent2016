import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class MainTest: WordSpec({
    "for part one" should ({
        "determine if 4 character string is an ABBA sequence" {
            "abba".isAbbaSequence() shouldBe true
            "abbc".isAbbaSequence() shouldBe false
            "acba".isAbbaSequence() shouldBe false
            "aaaa".isAbbaSequence() shouldBe false
        }
        "determine if a string of any length contaisn ABBA sequence" {
             "abcdxyyx".containsAbbaSequence() shouldBe true
             "abcdxyx".containsAbbaSequence() shouldBe false
        }
        "hypernet sequence is a string contained between two score brackets" {
            val (_, hypernetSequence) = parse("abcd[bddb]xyyx")
            hypernetSequence shouldBe listOf("bddb")
        }
        "string abcd[bddb]xyyx with hypernetSequence removed is [abcd,xyyx]"() {
            val (strings, _) = parse("abcd[bddb]xyyx")
            strings shouldBe listOf("abcd", "xyyx")
        }
        "abba[mnop]qrst supports TLS" {
            "abba[mnop]qrst".supportsTLS() shouldBe true
        }
        "abcd[bddb]xyyx does not support TLS" {
            "abcd[bddb]xyyx".supportsTLS() shouldBe false
        }
        "aaaa[qwer]tyui does not support TLS" {
            "aaaa[qwer]tyui".supportsTLS() shouldBe false
        }
        "ioxxoj[asdfgh]zxcvbn supports TLS" {
            "ioxxoj[asdfgh]zxcvbn".supportsTLS() shouldBe true
        }
        "result using puzzle input is 118"{
            partOne(puzzleInput) shouldBe  118
        }
    })
})