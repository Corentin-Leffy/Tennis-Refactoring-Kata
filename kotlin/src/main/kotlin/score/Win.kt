package score

import ScoreState
import TennisGame1

class Win(override val game: TennisGame1) : ScoreState {
    override fun get(): String = "Win for ${game.leadingPlayer.name}"

    override fun next(): ScoreState { return this }
}
