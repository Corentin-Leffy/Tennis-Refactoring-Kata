class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private var score1: Int = 0
    private var score2: Int = 0

    override fun wonPoint(playerName: String) {
        if (playerName === "player1")
            score1 += 1
        else
            score2 += 1
    }

    override fun getScore(): String {
        var score = ""
        var tempScore: Int
        if (scoresAreEqual()) {
            return scoreEquality(score)
        }
        if (score1 >= 4 || score2 >= 4) {
            return endGameScores(score)
        }
        for (i in 1..2) {
            if (i == 1)
                tempScore = score1
            else {
                score += "-"
                tempScore = score2
            }
            when (tempScore) {
                0 -> score += "Love"
                1 -> score += "Fifteen"
                2 -> score += "Thirty"
                3 -> score += "Forty"
            }
        }

        return score
    }

    private fun endGameScores(score: String): String {
        var score3 = score
        val minusResult = score1 - score2
        score3 = when {
            minusResult == 1 -> "Advantage player1"
            minusResult == -1 -> "Advantage player2"
            minusResult >= 2 -> "Win for player1"
            else -> "Win for player2"
        }
        return score3
    }

    private fun scoresAreEqual() = score1 == score2

    private fun scoreEquality(score: String): String {
        var score3 = score
        score3 = when (score1) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }
        return score3
    }
}
