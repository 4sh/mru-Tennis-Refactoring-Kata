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
        var score = ""
        var tempScore = 0
        score = if (scorePlayer1 == scorePlayer2) {
            computeEqualities(score)
        } else if (scorePlayer1 >= 4 || scorePlayer2 >= 4) {
            computeEndGame(score)
        } else {
            computeRegularScore(tempScore, score)
        }
        return score
    }

    private fun computeRegularScore(tempScore: Int, score: String): String {
        var tempScore1 = tempScore
        var score1 = score
        for (i in 1..2) {
            if (i == 1) {
                tempScore1 = scorePlayer1
            } else {
                score1 += "-"
                tempScore1 = scorePlayer2
            }
            when (tempScore1) {
                0 -> score1 += LOVE.value
                1 -> score1 += FIFTEEN.value
                2 -> score1 += THIRTY.value
                3 -> score1 += FORTY.value
            }
        }
        return score1
    }

    private fun computeEndGame(score: String): String {
        var score1 = score
        val minusResult = scorePlayer1 - scorePlayer2
        if (minusResult == 1) {
            score1 = "Advantage player1"
        } else if (minusResult == -1) {
            score1 = "Advantage player2"
        } else if (minusResult >= 2) {
            score1 = "Win for player1"
        } else {
            score1 = "Win for player2"
        }
        return score1
    }

    private fun computeEqualities(score: String): String {
        var score1 = score
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
