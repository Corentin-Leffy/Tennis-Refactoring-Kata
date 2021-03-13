package score

import ScoreState
import TennisGame1

class Win(override val tennisGame: TennisGame1) : ScoreState {
    override val get: String
        get() = "score.Win for ${tennisGame.leadingPlayer.name}"

    override fun next() { }
}
