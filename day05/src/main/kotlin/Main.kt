import org.apache.commons.codec.digest.DigestUtils

fun lowestMD5HashWithPrefix(secretKey: String, requiredPrefix:String, start:Long = 0, validator:(String, String) -> Boolean): Long {
    var number = start
    var resultFound = false
    while (!resultFound) {
        val hashedExpression = hashedExpression(secretKey, number)
        if (validator(hashedExpression, requiredPrefix)) resultFound = true
        else number++
    }
    return number
}
fun hashedExpression(secretKey:String, number:Long): String = DigestUtils.md5Hex("$secretKey$number")

fun findPassword(secretKey: String, requiredPrefix: String):String {
    var password = ""
    var number = 0L
    while (password.length < 8) {
        number = lowestMD5HashWithPrefix(secretKey,requiredPrefix,number + 1, ::validExpression)
        password += sixthDigit(secretKey,number)
    }
    return password
}

fun validExpression(expression:String, requiredPrefix:String) = (expression.startsWith(requiredPrefix))

fun sixthDigit(secretKey: String, number: Long ) = hashedExpression(secretKey, number)[5]

fun partOne(secretKey:String) = findPassword(secretKey, "00000")

fun findPasswordPartTwo(secretKey: String, requiredPrefix: String):String {
    val passwordMap = mutableMapOf<Int,Char>()
    var number = 0L
    while (passwordMap.keys != setOf(0,1,2,3,4,5,6,7)) {
        number = lowestMD5HashWithPrefix(secretKey,requiredPrefix,number + 1, ::validExpressionP2)
        val (digit, position) = digitAndPosition(secretKey, number)
        if (position !in passwordMap ) passwordMap[position] = digit
    }
    return passwordMap.toList().sortedBy {(position, _) -> position}.map{it.second}.joinToString("")
}

fun validExpressionP2(expression:String, requiredPrefix:String) = (expression.startsWith(requiredPrefix) && expression[5] in '0'..'7')

fun digitAndPosition(secretKey: String, number: Long) = Pair(hashedExpression(secretKey, number)[6],hashedExpression(secretKey, number)[5].toString().toInt())

fun partTwo(secretKey:String) = findPasswordPartTwo(secretKey, "00000")