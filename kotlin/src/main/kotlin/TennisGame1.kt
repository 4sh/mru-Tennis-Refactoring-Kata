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
        return if (scoreGap == 1) {
            "Advantage player1"
        } else if (scoreGap == -1) {
            "Advantage player2"
        } else if (scoreGap >= 2) {
            "Win for player1"
        } else {
            "Win for player2"
        }
    }

    private fun computeEqualities(): String {
        var score1 = ""
        when (scorePlayer1) {
            0 -> score1 = "Love-All"
            1 -> score1 = "Fifteen-All"
            2 -> score1 = "Thirty-All"
            else -> score1 = "Deuce"
        }
        return score1
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
