import score.Win

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

    internal val leadingPlayer get() = player1.takeIf { player1.points > player2.points } ?: player2

    private fun changeState(newScoreState: ScoreState) {
        score = newScoreState
    }
}


class Default(override val game: TennisGame1) : ScoreState {
    override fun get(): String = "${game.player1.points.toScore()}-${game.player2.points.toScore()}"

    override fun next(): ScoreState = when {
        areScoresEqual -> Equality(game)
        isOnePlayerAdvantaged && aPlayerIsLeadingByOnePoint -> Advantage(game)
        isOnePlayerAdvantaged && aPlayerIsLeadingByAtLeastTwoPoints -> Win(game)
        else -> this
    }

    private fun Int.toScore() = when (this) {
        0 -> "Love"
        1 -> "Fifteen"
        2 -> "Thirty"
        else -> "Forty"
    }
}

class Equality(override val game: TennisGame1) : ScoreState {
    override fun get(): String =
        when (game.player1.points) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }

    override fun next(): ScoreState = when {
        isOnePlayerAdvantaged && aPlayerIsLeadingByOnePoint -> Advantage(game)
        isOnePlayerAdvantaged && aPlayerIsLeadingByAtLeastTwoPoints -> Win(game)
        else -> Default(game)
    }
}

class Advantage(override val game: TennisGame1) : ScoreState {
    override fun get(): String = "Advantage ${game.leadingPlayer.name}"

    override fun next(): ScoreState = when {
        areScoresEqual -> Equality(game)
        else -> Win(game)
    }
}

