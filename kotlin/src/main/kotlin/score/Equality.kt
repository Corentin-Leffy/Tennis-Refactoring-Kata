package score

import Default
import ScoreState
import TennisGame1

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