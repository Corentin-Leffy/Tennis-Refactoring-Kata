import kotlin.math.abs

class TennisGame1(player1Name: String, player2Name: String) : TennisGame {

    val player1 = Player(player1Name)
    val player2 = Player(player2Name)
    private var scoreState: ScoreState = Equality(this)

    override fun wonPoint(playerName: String) {
        playerCalled(playerName).wonPoint()
        scoreState.next()
    }

    override fun getScore(): String {
        val s = when {
            scoresAreEqual -> equalScore
            aPlayerHasAdvantage -> endGameScore
            else -> basicScore
        }
        println("${scoreState.score()} == $s")
        return s
    }

    private fun playerCalled(name: String) = player1.takeIf { it.isCalled(name) } ?: player2

    internal val scoresAreEqual get() = player1.points == player2.points

    private val equalScore
        get() = when (player1.points) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }

    internal val aPlayerHasAdvantage get() = player1.points >= 4 || player2.points >= 4

    private val endGameScore: String
        get() {
            if (aPlayerIsLeadingByOnePoint) return "Advantage ${leadingPlayer.name}"
            return "Win for ${leadingPlayer.name}"
        }

    internal val aPlayerIsLeadingByOnePoint get() = abs(player1.points - player2.points) == 1

    internal val aPlayerIsLeadingByTwoPoints get() = abs(player1.points - player2.points) >= 2

    internal val leadingPlayer get() = player1.takeIf { player1.points > player2.points } ?: player2

    private val basicScore get() = "${player1.points.toScore()}-${player2.points.toScore()}"

    private fun Int.toScore() = when (this) {
        0 -> "Love"
        1 -> "Fifteen"
        2 -> "Thirty"
        else -> "Forty"
    }

    internal fun changeState(newScoreState: ScoreState) {
        scoreState = newScoreState
    }

}

data class Player(val name: String) {
    var points = 0
        private set

    fun wonPoint() {
        points++
    }

    fun isCalled(name: String): Boolean = this.name == name
}


interface ScoreState {
    val tennisGame: TennisGame1
    fun score(): String
    fun next()
}

class Default(override val tennisGame: TennisGame1) : ScoreState {
    override fun score(): String =
        "${tennisGame.player1.points.toScore()}-${tennisGame.player2.points.toScore()}"

    override fun next() {
        val newScoreState = when {
            tennisGame.scoresAreEqual -> Equality(tennisGame)
            tennisGame.aPlayerHasAdvantage && tennisGame.aPlayerIsLeadingByOnePoint -> Advantage(tennisGame)
            tennisGame.aPlayerHasAdvantage && tennisGame.aPlayerIsLeadingByTwoPoints -> Win(tennisGame)
            else -> Default(tennisGame)
        }
        tennisGame.changeState(newScoreState)
    }

    private fun Int.toScore() = when (this) {
        0 -> "Love"
        1 -> "Fifteen"
        2 -> "Thirty"
        else -> "Forty"
    }
}

class Equality(override val tennisGame: TennisGame1) : ScoreState {
    override fun score(): String =
        when (tennisGame.player1.points) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }

    override fun next() {
        val newScoreState = when {
            tennisGame.aPlayerHasAdvantage && tennisGame.aPlayerIsLeadingByOnePoint -> Advantage(tennisGame)
            tennisGame.aPlayerHasAdvantage && tennisGame.aPlayerIsLeadingByTwoPoints -> Win(tennisGame)
            else -> Default(tennisGame)
        }
        tennisGame.changeState(newScoreState)
    }
}

class Advantage(override val tennisGame: TennisGame1) : ScoreState {
    override fun score(): String = "Advantage ${tennisGame.leadingPlayer.name}"

    override fun next() {
        val newScoreState = when {
            tennisGame.scoresAreEqual -> Equality(tennisGame)
            tennisGame.aPlayerHasAdvantage && tennisGame.aPlayerIsLeadingByOnePoint -> Advantage(tennisGame)
            tennisGame.aPlayerHasAdvantage && tennisGame.aPlayerIsLeadingByTwoPoints -> Win(tennisGame)
            else -> Default(tennisGame)
        }
        tennisGame.changeState(newScoreState)
    }
}

class Win(override val tennisGame: TennisGame1) : ScoreState {
    override fun score(): String = "Win for ${tennisGame.leadingPlayer.name}"

    override fun next() { }
}
