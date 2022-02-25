fun String.countLetters():Map<Char, Int> =
    ('a'..'z').associateWith { letter -> count { letterInString -> letterInString == letter } }

fun Map<Char, Int>.fiveMostPopularLetters() =  toList().sortedWith(compareBy({- it.second}, {it.first})).map{it.first}.take(5).joinToString("")

fun String.sectorValue() = if (encryptedName().countLetters().fiveMostPopularLetters() == checksum()) sectorId() else 0

fun String.encryptedName() = dropLast(10)
fun String.sectorId() = takeLast(10).take(3).toInt()
fun String.checksum() = takeLast(6).take(5)

fun partOne(data:List<String>) = data.sumOf(String::sectorValue)

fun Char.next() = when(this) {
    'z' -> 'a'
    '-' -> ' '
    ' ' -> ' '
    else -> (code + 1).toChar()
}

//As an example letterRotationMap['c'] returns [c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,a,b] which can be used to look up rotated values for c.
val letterRotationMap = (('a'..'z').toList() + '-').associateWith { letter -> letter.lettersAtEachOffset() }

//for c this would create a list of [c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,a,b]
fun Char.lettersAtEachOffset() = (0..24).fold(listOf(this)){list, _ -> list + list.last().next()}

fun Char.rotateLetter(sectorId:Int) = letterRotationMap.getValue(this)[sectorId % 26]

fun String.decoded():String {
    val sectorId = sectorId()
    return encryptedName().fold(""){result, char -> result + char.rotateLetter(sectorId) }
}

fun partTwo(data:List<String>):Int =
    data.first{string -> string.decoded().trim() == "northpole object storage" }.sectorId()
