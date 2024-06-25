import Scores.*

class TennisGame1(
    private val player1Name: String,
    private val player2Name: String,
) : TennisGame {
    private var scorePlayer1: Int = 0
    private var scorePlayer2: Int = 0

    override fun wonPoint(playerName: String) {
        if (playerName === "player1") {
            scorePlayer1 += 1
        } else {
            scorePlayer2 += 1
        }
    }

    override fun getScore(): String {
        return if (scorePlayer1 == scorePlayer2) {
            computeEqualities()
        } else if (scorePlayer1 >= 4 || scorePlayer2 >= 4) {
            computeEndGame()
        } else {
            computeRegularScore()
        }
    }

    private fun computeRegularScore(): String = "${scorePlayer1.toRegularScore()}-${scorePlayer2.toRegularScore()}"

    private fun Int.toRegularScore() = when (this) {
        0 -> LOVE.value
        1 -> FIFTEEN.value
        2 -> THIRTY.value
        3 -> FORTY.value
        else -> throw IllegalArgumentException()
    }

    private fun computeEndGame(): String {
        val scoreGap = scorePlayer1 - scorePlayer2
        return when {
            scoreGap == 1 -> buildAdvantage(player1Name)
            scoreGap == -1 -> buildAdvantage(player2Name)
            scoreGap >= 2 -> buildWin(player1Name)
            else -> buildWin(player2Name)
        }
    }

    private fun buildAdvantage(playerName: String) = "Advantage $playerName"
    private fun buildWin(playerName: String) = "Win for $playerName"

    private fun computeEqualities(): String {
        return when (scorePlayer1) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }
    }
}

enum class Scores(
    val value: String,
) {
    LOVE("Love"),
    FIFTEEN("Fifteen"),
    THIRTY("Thirty"),
    FORTY("Forty"),
}
