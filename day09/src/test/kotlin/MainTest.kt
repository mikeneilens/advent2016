import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class MainTest: WordSpec({
    "For part one " should ({
        "a string with no markers returns a tree which is the length of the string" {
            "ADVENT".toTree() shouldBe Leaf("ADVENT")
            "ADVENT".toTree().length() shouldBe 6L
        }
        "a string with one marker prefixed by a string and no string after the marker returns a tree with a marker" {
            val tree = "A(1x5)B".toTree()
            val expectedTree = LastTree("A",Marker(5,Leaf("B")))
            tree shouldBe expectedTree
            tree.length() shouldBe 6L
        }
        "a string with one marker prefixed by a string and a string after the marker returns a tree with a marker and an additional tree" {
            val tree = "A(1x5)BC".toTree()
            val expectedTree = Tree("A",Marker(5,Leaf("B")), Leaf("C"))
            tree shouldBe expectedTree
            tree.length() shouldBe 7L
        }
        "a string with two markers returns" {
            val tree = "A(2x2)BCD(2x2)EFG".toTree()
            val expectedTree = Tree("A", Marker(2, Leaf("BC")), Tree("D", Marker(2, Leaf("EF")), Leaf("G")))
            tree shouldBe expectedTree
            tree.length() shouldBe 11L
        }
        "a string with two markers were one marker is inside the string of another returns a tree with one marker" {
            val tree = "(6x1)(1x3)A".toTree()
            val expectedTree = LastTree("",Marker(1,Leaf("(1x3)A")))
            tree shouldBe expectedTree
            tree.length() shouldBe 6L
        }
        "decompressed length of several compenents built from X(8x2)(3x3)ABCY is 18 "{
            "X(8x2)(3x3)ABCY".toTree().length() shouldBe 18L
        }
        "part one using puzzle input has a decompresesd length of 110346" {
            partOne(puzzleInput) shouldBe 110346L
        }
    })
    "for part two" should ({
        "(3x3)XYZ is a tree of length 9" {
            "(3x3)XYZ".toTree(isPartTwo = true).length() shouldBe 9L
        }
        "X(8x2)(3x3)ABCY is a tree of length 20" {
            "X(8x2)(3x3)ABCY".toTree(isPartTwo = true).length() shouldBe 20L
        }
        "(27x12)(20x12)(13x14)(7x10)(1x12)A creates a tree of length 241920" {
            "(27x12)(20x12)(13x14)(7x10)(1x12)A".toTree(isPartTwo = true).length() shouldBe 241920L
        }
        "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN creates a tree of length 445" {
            "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN".toTree(isPartTwo = true).length() shouldBe 445L
        }
        "part two using puzzle input has a decompresesd length of 10774309173" {
            partTwo(puzzleInput) shouldBe 10774309173L
        }
    })
})
