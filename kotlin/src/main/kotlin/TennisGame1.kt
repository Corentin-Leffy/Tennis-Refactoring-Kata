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
        if (scoresAreEqual()) {
            return scoreEquality()
        }
        if (score1 >= 4 || score2 >= 4) {
            return endGameScores()
        }
        var score = ""
        var tempScore: Int
        for (i in 1..2) {
            if (i == 1)
                tempScore = score1
            else {
                score += "-"
                tempScore = score2
            }
            score += scoreOf(tempScore)
        }

        return score
    }

    private fun scoreOf(tempScore: Int) = when (tempScore) {
        0 -> "Love"
        1 -> "Fifteen"
        2 -> "Thirty"
        else -> "Forty"
    }

    private fun endGameScores(): String {
        val minusResult = score1 - score2
        return when {
            minusResult == 1 -> "Advantage player1"
            minusResult == -1 -> "Advantage player2"
            minusResult >= 2 -> "Win for player1"
            else -> "Win for player2"
        }
    }

    private fun scoresAreEqual() = score1 == score2

    private fun scoreEquality() =
        when (score1) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }
}
