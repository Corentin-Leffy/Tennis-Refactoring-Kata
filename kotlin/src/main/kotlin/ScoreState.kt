import kotlin.math.abs

interface ScoreState {
    val game: TennisGame1
    fun get(): String
    fun next(): ScoreState

    val areScoresEqual: Boolean get() = game.player1.points == game.player2.points

    val isOnePlayerAdvantaged: Boolean get() = game.player1.points >= 4 || game.player2.points >= 4

    val aPlayerIsLeadingByOnePoint get() = abs(game.player1.points - game.player2.points) == 1

    val aPlayerIsLeadingByAtLeastTwoPoints get() = abs(game.player1.points - game.player2.points) >= 2
}
