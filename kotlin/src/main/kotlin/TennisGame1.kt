import kotlin.math.abs

class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private val pointsPlayer1
        get() = player1.points
    private var pointsPlayer2: Int = 0
    private val player1 = Player(player1Name)
    private val player2 = Player(player2Name)

    override fun wonPoint(playerName: String) {
        if (playerName == player1Name) {
            player1.wonPoint()
        } else {
            pointsPlayer2 += 1
            player2.wonPoint()
        }
    }

    override fun getScore(): String {
        if (scoresAreEqual()) return scoreEquality()
        if (aPlayerHasAdvantage()) return endGameScores()
        return basicScore()
    }

    private fun aPlayerHasAdvantage() = pointsPlayer1 >= 4 || pointsPlayer2 >= 4

    private fun endGameScores(): String {
        if (aPlayerIsLeadingByOnePoint()) return "Advantage ${leadingPlayer()}"
        return "Win for ${leadingPlayer()}"
    }

    private fun aPlayerIsLeadingByOnePoint() = abs(pointsPlayer1 - pointsPlayer2) == 1

    private fun leadingPlayer() =
        player1Name.takeIf { pointsPlayer1 > pointsPlayer2 } ?: player2Name

    private fun scoresAreEqual() = pointsPlayer1 == pointsPlayer2

    private fun basicScore() = "${pointsPlayer1.toScore()}-${pointsPlayer2.toScore()}"

    private fun scoreEquality() =
        when (pointsPlayer1) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }

    private fun Int.toScore() = when (this) {
        0 -> "Love"
        1 -> "Fifteen"
        2 -> "Thirty"
        else -> "Forty"
    }
}

data class Player(
    val name: String
) {
    var points = 0
        private set

    fun wonPoint() {
        points++
    }
}