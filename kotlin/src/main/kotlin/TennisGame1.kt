import score.Win
import kotlin.math.abs

class TennisGame1(player1Name: String, player2Name: String) : TennisGame {

    internal val player1 = Player(player1Name)
    internal val player2 = Player(player2Name)
    private var score: ScoreState = Equality(this)

    override fun wonPoint(playerName: String) {
        playerCalled(playerName).wonPoint()
        changeState(score.next())
    }

    override fun getScore(): String = score.get()

    private fun playerCalled(name: String) = player1.takeIf { it.isCalled(name) } ?: player2

    internal val scoresAreEqual get() = player1.points == player2.points

    internal val aPlayerHasAdvantage get() = player1.points >= 4 || player2.points >= 4

    internal val aPlayerIsLeadingByOnePoint get() = abs(player1.points - player2.points) == 1

    internal val leadingPlayer get() = player1.takeIf { player1.points > player2.points } ?: player2

    private fun changeState(newScoreState: ScoreState) {
        score = newScoreState
    }
}


class Default(override val tennisGame: TennisGame1) : ScoreState {
    override fun get(): String =
        "${tennisGame.player1.points.toScore()}-${tennisGame.player2.points.toScore()}"

    override fun next(): ScoreState = when {
        tennisGame.scoresAreEqual -> Equality(tennisGame)
        tennisGame.aPlayerHasAdvantage && tennisGame.aPlayerIsLeadingByOnePoint -> Advantage(
            tennisGame
        )
        tennisGame.aPlayerHasAdvantage -> Win(tennisGame)
        else -> this
    }

    private fun Int.toScore() = when (this) {
        0 -> "Love"
        1 -> "Fifteen"
        2 -> "Thirty"
        else -> "Forty"
    }
}

class Equality(override val tennisGame: TennisGame1) : ScoreState {
    override fun get(): String =
        when (tennisGame.player1.points) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }

    override fun next(): ScoreState = when {
        !tennisGame.aPlayerHasAdvantage -> Default(tennisGame)
        tennisGame.aPlayerHasAdvantage && tennisGame.aPlayerIsLeadingByOnePoint -> Advantage(
            tennisGame
        )
        tennisGame.aPlayerHasAdvantage -> Win(tennisGame)
        else -> this
    }
}

class Advantage(override val tennisGame: TennisGame1) : ScoreState {
    override fun get(): String = "Advantage ${tennisGame.leadingPlayer.name}"

    override fun next(): ScoreState {
        return when {
            tennisGame.scoresAreEqual -> Equality(tennisGame)
            else -> Win(tennisGame)
        }
    }
}

