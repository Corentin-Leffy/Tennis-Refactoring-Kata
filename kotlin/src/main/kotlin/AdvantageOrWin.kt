class AdvantageOrWin(
        private val pointsForPlayer1: Int,
        private val pointsForPlayer2: Int
) : Score {
    override val score: String
        get() {
            val minusResult = pointsForPlayer1 - pointsForPlayer2
            return when {
                minusResult == 1 -> "Advantage player1"
                minusResult == -1 -> "Advantage player2"
                minusResult >= 2 -> "Win for player1"
                else -> "Win for player2"
            }
        }
}