import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class MainTest: WordSpec( {
    "When trying part one " should ({
        "converting R2 to an Instruction" {
            "R2".toInstruction() shouldBe Instruction("R", 2)
        }
        "starting at Location(1,1) facing North then instruction R2 results in Location(3,1) facing East."{
            val start = listOf(Location(1, 1, Orientation.North))
             turnAndMove(start, Instruction("R",2)).last() shouldBe Location(3, 1, Orientation.East)
        }
        "starting at Location(1,1) facing North then instruction L2 results in Location(-2,1) facing West."{
            val start = listOf(Location(1,1, Orientation.North))
             turnAndMove(start, Instruction("R",2)).last() shouldBe Location(3, 1, Orientation.East)
        }
        "Following R2, L3 leaves you 2 blocks East and 3 blocks North, or 5 blocks away." {
            listOf(
                Instruction("R",2),
                Instruction("L",3))
                .calculateBlocks(listOf(Location())).last().manhattenDistance() shouldBe 5
        }
        "R2, R2, R2 leaves you 2 blocks due South of your starting position, which is 2 blocks away." {
            listOf(
                Instruction("R",2),
                Instruction("R",2),
                Instruction("R",2) )
                .calculateBlocks(listOf(Location())).last().manhattenDistance() shouldBe 2
        }
        "R5, L5, R5, R3 leaves you 12 blocks away." {
            listOf(
                Instruction("R",5),
                Instruction("L",5),
                Instruction("R",5),
                Instruction("R",3) )
                .calculateBlocks(listOf(Location())).last().manhattenDistance() shouldBe 12
        }
        "part one using puzzle input" {
            partOne(puzzleInput) shouldBe 242
        }
    })
    "when trying part two" should({
        "calculating a range of values when start is before end"{
            rangeOfValues(2,8) shouldBe (3..8)
        }
        "calculating a range of values when end is before start"{
            rangeOfValues(8,2) shouldBe (7 downTo 2)
        }

        "using sample data" {
            partTwo("R8, R4, R4, R8") shouldBe 4
        }
        "using puzzle input" {
            partTwo(puzzleInput) shouldBe 150
        }
    })
})
