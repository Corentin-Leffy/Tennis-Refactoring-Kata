import score.Equality

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


