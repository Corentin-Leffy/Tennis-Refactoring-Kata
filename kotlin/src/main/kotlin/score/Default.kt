package score

import ScoreState
import TennisGame1

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