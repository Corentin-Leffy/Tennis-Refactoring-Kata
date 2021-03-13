class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private var pointsPlayer1: Int = 0
    private var pointsPlayer2: Int = 0

    override fun wonPoint(playerName: String) {
        if (playerName === "player1")
            pointsPlayer1 += 1
        else
            pointsPlayer2 += 1
    }

    override fun getScore(): String {
        if (scoresAreEqual()) {
            return scoreEquality()
        }
        if (pointsPlayer1 >= 4 || pointsPlayer2 >= 4) {
            return endGameScores()
        }
        var score = ""
        var tempScore: Int
        for (i in 1..2) {
            if (i == 1)
                tempScore = pointsPlayer1
            else {
                score += "-"
                tempScore = pointsPlayer2
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
        val minusResult = pointsPlayer1 - pointsPlayer2
        return when {
            minusResult == 1 -> "Advantage player1"
            minusResult == -1 -> "Advantage player2"
            minusResult >= 2 -> "Win for player1"
            else -> "Win for player2"
        }
    }

    private fun scoresAreEqual() = pointsPlayer1 == pointsPlayer2

    private fun scoreEquality() =
        when (pointsPlayer1) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }
}
