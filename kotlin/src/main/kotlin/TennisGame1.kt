class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private var pointsForPlayer1: Int = 0
    private var pointsForPlayer2: Int = 0

    override fun wonPoint(playerName: String) {
        if (playerName == player1Name)
            pointsForPlayer1++
        else if (playerName == player2Name)
            pointsForPlayer2++
    }

    override fun getScore(): String {
        if (scoreAreEqual) return scoresEqual
        if (aPlayerHasEnoughPointsToWin) return advantageOrWin
        return outgoingScore
    }

    private val scoreAreEqual
        get() = pointsForPlayer1 == pointsForPlayer2

    private val scoresEqual: String
        get() = ScoresEqual(pointsForPlayer1).score

    private val aPlayerHasEnoughPointsToWin
        get() = pointsForPlayer1 >= 4 || pointsForPlayer2 >= 4

    private val advantageOrWin: String
        get() = AdvantageOrWin(pointsForPlayer1, pointsForPlayer2).score

    private val outgoingScore: String
        get() = OutgoingScore(pointsForPlayer1, pointsForPlayer2).score
}

