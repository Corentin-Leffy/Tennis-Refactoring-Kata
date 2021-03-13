interface ScoreState {
    val tennisGame: TennisGame1
    fun get(): String
    fun next(): ScoreState

    val areScoreEqual: Boolean get() = tennisGame.player1.points == tennisGame.player2.points

}
