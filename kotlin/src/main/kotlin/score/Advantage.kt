package score

import ScoreState
import TennisGame1

class Advantage(override val game: TennisGame1) : ScoreState {
    override fun get(): String = "score.Advantage ${game.leadingPlayer.name}"

    override fun next(): ScoreState = when {
        areScoresEqual -> Equality(game)
        else -> Win(game)
    }
}
