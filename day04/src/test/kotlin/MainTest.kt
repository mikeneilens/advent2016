import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class MainTest: WordSpec({

    val sampleData = """
        aaaaa-bbb-z-y-x-123[abxyz]
        a-b-c-d-e-f-g-h-987[abcde]
        not-a-real-room-404[oarel]
        totally-real-room-200[decoy]
    """.trimIndent().split("\n")

    "For part one" should ({
        "count each letter in a string" {
            "aaaaa-bbb-z-y-x-".countLetters()['a'] shouldBe 5
            "aaaaa-bbb-z-y-x-".countLetters()['b'] shouldBe 3
            "aaaaa-bbb-z-y-x-".countLetters()['z'] shouldBe 1
        }
        "find five most popular letters ina map" {
            val map = mapOf('a' to 5, 'b' to 3, 'z' to 1, 'y' to 1 , 'x' to 1)
            map.fiveMostPopularLetters() shouldBe "abxyz"
        }
        "encrypted name of aaaaa-bbb-z-y-x-123[abxyz] is aaaaa-bbb-z-y-x-" {
            "aaaaa-bbb-z-y-x-123[abxyz]".encryptedName() shouldBe "aaaaa-bbb-z-y-x-"
        }
        "sector id of aaaaa-bbb-z-y-x-123[abxyz] is 123" {
            "aaaaa-bbb-z-y-x-123[abxyz]".sectorId() shouldBe 123
        }
        "checksum of aaaaa-bbb-z-y-x-123[abxyz] is abxyz" {
            "aaaaa-bbb-z-y-x-123[abxyz]".checksum() shouldBe "abxyz"
        }
        "aaaaa-bbb-z-y-x-123[abxyz] has a sector value of 123 because it is a real room" {
            "aaaaa-bbb-z-y-x-123[abxyz]".sectorValue() shouldBe 123
        }
        "totally-real-room-200[decoy] has a sector value of 0 because it is a real room" {
            "totally-real-room-200[decoy]".sectorValue() shouldBe 0
        }
        "result using sample data is 1514" {
            partOne(sampleData) shouldBe 1514
        }
        "result using puzzle input" {
            partOne(puzzleInput) shouldBe 409147
        }

    })

    "for part two" should ({
        "next char after a is b" {
            'a'.next() shouldBe 'b'
        }
        "next char after z is a" {
            'z'.next() shouldBe 'a'
        }
        "letter for each offset of c is [c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,a,b]" {
           'c'.lettersAtEachOffset() shouldBe listOf('c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b')
        }
        "rotating letter q 343 times give letter v" {
            'q'.rotateLetter(343) shouldBe 'v'
        }
        "decoding 'qzmt-zixmtkozy-ivhz-343' is 'very encrypted name'" {
            "qzmt-zixmtkozy-ivhz-343[abxyz]".decoded().trim() shouldBe "very encrypted name"
        }
        "result using puzzle input is 991" {
            partTwo(puzzleInput) shouldBe 991
        }

    })
})