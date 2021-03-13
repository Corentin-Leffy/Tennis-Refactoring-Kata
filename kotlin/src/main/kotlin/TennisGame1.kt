import kotlin.math.abs

class TennisGame1(player1Name: String, player2Name: String) : TennisGame {

    private val player1 = Player(player1Name)
    private val player2 = Player(player2Name)

    override fun wonPoint(playerName: String) {
        if (playerName == player1.name) {
            player1.wonPoint()
        } else {
            player2.wonPoint()
        }
    }

    override fun getScore(): String {
        if (scoresAreEqual) return scoreEquality()
        if (aPlayerHasAdvantage()) return endGameScores()
        return basicScore
    }

    private fun aPlayerHasAdvantage() = player1.points >= 4 || player2.points >= 4

    private fun endGameScores(): String {
        if (aPlayerIsLeadingByOnePoint) return "Advantage ${leadingPlayer.name}"
        return "Win for ${leadingPlayer.name}"
    }

    private val aPlayerIsLeadingByOnePoint get() = abs(player1.points - player2.points) == 1

    private val leadingPlayer get() = player1.takeIf { player1.points > player2.points } ?: player2

    private val scoresAreEqual get() = player1.points == player2.points

    private val basicScore get() = "${player1.points.toScore()}-${player2.points.toScore()}"

    private fun scoreEquality() =
        when (player1.points) {
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