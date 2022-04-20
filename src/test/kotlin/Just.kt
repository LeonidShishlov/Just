const val SECOND_IN_MINUTE = 60
const val SECOND_IN_HOUR = 60 * 60
const val SECOND_IN_DAY = 24 * 60 * 60
const val SECOND_IN_2_DAY = 2 * 24 * 60 * 60
const val SECOND_IN_3_DAY = 3 * 24 * 60 * 60
fun main() {
    print("Введите, сколько секунд назад вы были в сети: ")
    val input = readLine()?.toInt() ?: return
    val wholeTime = when {
        (input / 60 < 1) -> input
        (input / 60 < 60) -> input / SECOND_IN_MINUTE
        (input / 60 < 60 * 24) -> input / SECOND_IN_HOUR
        else -> input / SECOND_IN_DAY
    }
    println(agoToText(input, wholeTime))
}

fun agoToText(input: Int, wholeTime: Int): String {
    return when {
        input < SECOND_IN_MINUTE -> "Только что"
        input < SECOND_IN_HOUR -> "$wholeTime ${declination("минуту", "минуты", "минут", wholeTime)} назад"
        input < SECOND_IN_DAY -> "$wholeTime ${declination("час", "часа", "часов", wholeTime)} назад"
        input < SECOND_IN_2_DAY -> "Сегодня"
        input < SECOND_IN_3_DAY -> "Вчера"
        input > SECOND_IN_3_DAY -> "Давно"

        else -> error("Неправильный ввод")
    }
}

fun declination(onePiece: String, twoPiece: String, default: String, wholeTime: Int): String {
    val upshot = when {
        wholeTime == 1 || wholeTime % 10 == 1 && wholeTime != 11 -> onePiece
        wholeTime == 2 || wholeTime % 10 == 2 && wholeTime != 12
                || wholeTime == 3 || wholeTime % 10 == 3 && wholeTime != 13
                || wholeTime == 4 || wholeTime % 10 == 4 && wholeTime != 14 -> twoPiece
        else -> default
    }
    return upshot
}